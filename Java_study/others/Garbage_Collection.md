# 가비지 컬렉션(Garbage Collection)

### 가비지 컬렉션(Garbage Collection) 이란?
* JVM에서 가비지 컬렉터(Garbage Collector)가 주기적으로 사용되지 않는 메모리를 탐색하여 자동으로 회수하는 기능
* Heap 영역에서 작동

### Young 영역과 Old 영역
* 객체의 생존 기간에 따라 물리적인 Heap 영역을 나누었고, Young 영역과 Old 영역으로 나누어짐 (perm 영역도 있었지만 java8 버전부터 삭제됨)
* **Young 영역**
  * 새롭게 생성된 객체가 할당(Allocation)되는 영역
  * 대부분의 객체가 금방 Unreachable(접근, 도달할 수 없는) 상태가 되기 때문에, 많은 객체가 Young 영역에 생성되었다가 사라짐
  * Young영역에 대한 가비지 컬렉션(Garbage Collection)을 **Minor GC**라고 한다
* **Old 영역**
  * Young영역에서 Reachable(접근, 도달할 수 있는) 상태를 유지하여 살아남은 객체가 복사되는 영역
  * Young영역보다 크게 할당되며, 영역의 크기가 큰 만큼 가비지는 적게 발생한다
  * old영역에 대한 가비지 컬렉션(Garbage Collection)을 **Major GC**라고 한다
> **Old 영역이 Young 영역보다 크게 할당되는 이유는** Young 영역의 수명이 짧은 객체들은 큰 공간을 필요로 하지 않으며 큰 객체들은 Young 영역이 아니라 바로 Old 영역에 할당되기 때문이다.
* **카드 테이블(Card Table)**
  * old영역에 있는 객체가 young 영역에 있는 객체를 참조할 때마다 그에 대한 정보가 표시되는 곳
  * young 영역에서 가비지 컬렉션이 진행될 때 카드 테이블을 조회하여 GC 대상인지 식별함
  * **카드 테이블이 필요한 이유는** young 영역에서 Minor GC가 실행될 때 old 영역에 있는 객체를 모두 검사하여 young 영역의 객체를 참조하고 있는지 검사하는게 너무 비 호율적이기 때문 

### 가비지 컬렉션(Garbage Collection) 동작 방식
* 
