# HashMap 동작 원리
### **HashMap 이란?**
- Map 인터페이스 구현체
- key 와 value를 쌍으로 가진다 (Map 특성)
- 내부적으로 Array를 사용한다. 버킷이라고도 한다.
- key를 해싱하여 인덱스를 생성한다.
- 인덱스를 통해 데이터에 접근할 수 있다. (시간복잡도: **O(1)**)

### **HashMap 충돌**
- Key는 문자부터 숫자까지 무한대에 가까운 값을 생성할 수 있지만, 인덱스는 작은 정수형으로 한정되어 있어 인덱스 충돌이 불가피하다.
- 서로 다른 key 들이 동일한 인덱스를 부여받는 현상이 생긴다.

Java에서는 HashMap 충돌을 2가지 방안으로 관리한다.

### **버킷 사이즈 자동 증가**
- **버킷에 75% 정도의 데이터가 저장될 경우 자동으로 버킷 사이즈를 증가한다.**
- threshold(임계점)에 도달하면 버킷 사이즈를 **2배** 늘린다. (늘리기 보다는 크기가 2배가 더 큰 버킷을 생성한다)
- oldThr(전 임계점) 에서 << 1 시프트 연산을 하여 newThr(새로운 임계점)을 생성한다. 전 임계점에서 2배가 되는 값으로 지정된다.

```java
final Node<K, V>[] resize() {
    Node<K, V>[] oldTab = this.table;
    int oldCap = oldTab == null ? 0 : oldTab.length;
    int oldThr = this.threshold;
    int newThr = 0;
    int newCap;
    if (oldCap > 0) {
        if (oldCap >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return oldTab;
        }

        if ((newCap = oldCap << 1) < 1073741824 && oldCap >= 16) {
            newThr = oldThr << 1;           	// 새로운 임계점 생성
        }
    } else if (oldThr > 0) {
    	    newCap = oldThr;
    } else {
        newCap = 16;
        newThr = 12;
    }


	if (newThr == 0) {
    	float ft = (float)newCap * this.loadFactor;
    	newThr = newCap < 1073741824 && ft < 1.07374182E9F ? (int)ft : Integer.MAX_VALUE;
	}

	this.threshold = newThr;
	Node<K, V>[] newTab = new Node[newCap];   // 사이즈 갱신된 버킷 생성

	...
```

### **Red-Black Tree**
- 초기에 인덱스가 충돌하면 LinkedList 방식으로 충돌을 제어한다.
- 버킷이 가리키고 있는 Node 객체는 next 필드를 가지고 있는데, 생성된 인덱스가 충돌할 때마다 Next가 가르키는 노드가 많아져 효율이 많이 떨어진다.  
  -> **시간복잡도가 O(n)이 된다.**
- 충돌횟수가 임계점(TREEIFY-THRESHOLD)를 넘게되면 데이터 저장방식이 변경되는데, 이것이 바로 **Red-Black Tree**이다.  
  -> **treeifyBin 메서드를 호출한다.**

```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
    Node[] tab;
    int n;
    if ((tab = this.table) == null || (n = tab.length) == 0) {
        n = (tab = this.resize()).length;
    }

    Object p;
    int i;
    if ((p = tab[i = n - 1 & hash]) == null) {
        tab[i] = this.newNode(hash, key, value, (Node)null);
    } else {
        Object e;
        Object k;
        if (((Node)p).hash == hash && ((k = ((Node)p).key) == key || key != null && key.equals(k))) {
            e = p;
        } else if (p instanceof TreeNode) {
            e = ((TreeNode)p).putTreeVal(this, tab, hash, key, value);
        } else {
            int binCount = 0;

            while(true) {
                if ((e = ((Node)p).next) == null) {
                    ((Node)p).next = this.newNode(hash, key, value, (Node)null);
                    if (binCount >= 7) {
			// 노드 생성 임계점을 넘어 Red-Black Tree 저장방식으로 변경
                        this.treeifyBin(tab, hash);
                    }
                    break;
                }
		
		...
```

- 트리화가 완료되면, 데이터를 탐색하는데 걸리는 **시간 복잡도는 O(log n)** 이 된다.

### **Red-Black Tree - Node -> TreeNode 객체 변경**
- **Red-Black Tree로 저장방식이 변경**되면, 단순 Node 객체를 TreeNode 객체로 변경해 주어야한다. (Node 객체에 여러개 기능이 추가된 형태이다)
- TreeNode 객체를 생성할 때, 생성자의 매개변수로 Node 객체의 hash, key, value 값을 받는다. 이는 TreeNode  클래스의 부모클래스인 Node 클래스의 객체를 생성할 때 사용될 실인수다.
- treeify 메소드에서 parent, left, right, red 필드에 Red-black Tree 로직을 위한 적절한 값을 넣어주는 작업을 한다.
- replacementTreeNode 메소드로 Node 객체를 TreeNode 객체로 전환하고 treeify 메소드를 통해 Red-Black Tree 로직을 위한 적절한 데이터를 TreeNode 필드 안에 채우면 트리화는 완료된다.

```java
final void treeifyBin(Node<K, V>[] tab, int hash) {
    int n;
    if (tab != null && (n = tab.length) >= 64) {
        int index;
        Node e;
        if ((e = tab[index = n - 1 & hash]) != null) {
            TreeNode<K, V> hd = null;
            TreeNode<K, V> tl = null;

            do {
		// Node 객체를 TreeNode로 전환
                TreeNode<K, V> p = this.replacementTreeNode(e, (Node)null);
                if (tl == null) {
                    hd = p;
                } else {
                    p.prev = tl;
                    tl.next = p;
                }

                tl = p;
            } while((e = e.next) != null);

            if ((tab[index] = hd) != null) {
		// Red-Black Tree 로직을 위한 적절한 데이터를 TreeNode 필드 안에 채움
                hd.treeify(tab);
            }
        }
    } else {
        this.resize();
    }

}
```



