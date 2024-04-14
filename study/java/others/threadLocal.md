# ThreadLocal

- Thread에 대한 로컬 변수를 제공한다.
- 각각의 Thread가 변수에 대해서 독립적으로 접근할 수 있다.
한마디로 각 Thread가 독립적으로 사용할 수 있는 로컬 변수다.

### ThreadLocal의 주된 기능 메서드들
```java
// Integer 타입을 가질 수 있는 ThreadLocal
ThreadLocal<Integer> threadLocalValue = new ThreadLocal<>();
```

선언하는 방법은 위처럼 하되 다른 타입에 대해서도 적용이 가능하다.
```java
ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
    table = new Entry[INITIAL_CAPACITY];
    int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
    table[i] = new Entry(firstKey, firstValue);
    size = 1;
    setThreshold(INITIAL_CAPACITY);
}

static class Entry extends WeakReference<ThreadLocal<?>> {
    /** The value associated with this ThreadLocal. */
    Object value;
    Entry(ThreadLocal<?> k, Object v) {
        super(k);
        value = v;
    }
}
```

ThreadLocal은 기본적으로 Thread의 정보를 Key 값으로 하여 값을 저장하는 Map의 구조(ThreadLocalMap)를 가지고 있다.  
내부구조를 살펴보면 Entry라는 타입의 배열 형태로 구성하면 그거을 확인할 수 있다.

### **get() & set()**
```java
public T get() {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    return setInitialValue();
}

public void set(T value) {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        map.set(this, value);
    } else {
        createMap(t, value);
    }
}
```

데이터의 호출과 저장을 위해 get()과 set()을 사용하고 있다.
두 메소드 모두 현재 진행 중인 Thread를 Key 값으로 getMap()을 사용해 ThreadLocalMap을 불러온다.  
**get()**
- Map이 null이 아니라면 ThreadLocalMap의 내부에 있는 Entry를 불러오고 ThreadLocal에 저장한 값을 불러온다.
- 내부에 있는 `@SuppressWarnings(“unchecked”)`는 검증되지 않은 연산자 관련 경고를 제외하는 용도로 사용 중이다.  
**set()**
- ThreadLocal 객체를 key로 사용해 ThreadLocalMap에 값을 주입한다.
- 내부적으로 현재 ThreadLocal이 가진 hash code를 사용한다.

### **getMap() & createMap()**
```java
class Thread implements Runnable {
    ThreadLocal.ThreadLocalMap threadLocals = null; 
    (생략)
}
```
Thread 클래스 내부를 살펴보면 위와 같은 코드가 있다.  
여기서 ThreadLocal의 ThreadLocalMap으로 참조하는 것으로 작성되어 있다.

```java
ThreadLocalMap getMap(Thread t) {
    return t.threadLocals;
}

void createMap(Thread t, T firstValue) {
    t.threadLocals = new ThreadLocalMap(this, firstValue);
}
```

**getMap()**
- Thread를 받아와 해당 Thread에서 사용될 참조할 ThreadLocalMap 값을 반환한다.

**createMap()**
- Thread 내부 ThreadLocalMap을 새로 생성한다.

### remove()
```java
public void remove() {
    ThreadLocalMap m = getMap(Thread.currentThread());
    if (m != null) {
        m.remove(this);
    }
}
```

현재 수행 중인 Thread를 기준으로 getMap()시켜 내부 ThreadLocalMap을 가져온다.
그리고 안에 있던 값을 제거한다.

### withInitial()
```java
public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
    return new SuppliedThreadLocal<>(supplier);
}

// 예시
ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
```

1.8 버전 이후에 생겨난 메소드로 로컬 변수를 생성하면서 특정 값으로 초기화하도록 한다.
위 예시코드는 초깃값을 0으로 설정한다.

> 참고: https://dev.gmarket.com/62
