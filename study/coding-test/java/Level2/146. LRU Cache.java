/*
------------------------
         개선 후
LinkedHashMap 사용
Runtime: 45ms
------------------------
*/
class LRUCache {
    private LinkedHashMap<Integer, Integer> map;
    private int capacity;

    public LRUCache(int capacity) {
        // true는 accessOrder를 의미하여, 최근 사용한 순서대로 정렬.
        this.map = new LinkedHashMap<>(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        if (map.size() == capacity && !map.containsKey(key)) {
            // 가장 오래된 항목을 제거
            // keySet(): Map에 저장된 모든 키들을 Set으로 반환.
            // iterator(): 이 Set의 요소들을 순차적으로 접근할 수 있는 반복자 객체를 생성.
            // next(): Iterator의 첫 번째 요소(즉, 가장 오래된 키)를 반환.
            Integer oldestKey = map.keySet().iterator().next();
            map.remove(oldestKey);
        }
        map.put(key, value);
    }
}

/*
------------------------
         개선 전
HashMap, LinkedList 사용
Runtime: 1059ms
orderList.remove((Object)key) 부분 O(n)
------------------------
*/
class LRUCache {
    private HashMap<Integer, Integer> map;
    private LinkedList<Integer> orderList; 
    private int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>(capacity);
        this.orderList = new LinkedList<>();
        this.capacity = capacity;
    }
    
    // 최근에 접근한 key 일 경우 리스트 맨 앞 쪽에 위치할 수 있도록 함.
    // 배열 인덱스로 치면 0번 인덱스에 위치하도록 함.
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            orderList.remove((Object)key);
            orderList.addFirst(key);
            return map.get(key);
        }
    }
    
    // map의 현재 사이즈가 capacity와 같으면 오랫동안 사용되지 않은 요소를 제거한 후 새로운 요소를 추가
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            orderList.remove((Object)key);
        } else if (map.size() == capacity) {
            int removeKey = orderList.removeLast();
            map.remove(removeKey);
        } 

        map.put(key, value);
        orderList.addFirst(key);

        return;
    }
}
