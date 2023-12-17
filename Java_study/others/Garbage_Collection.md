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
* **STW(Stop The World)**
 * 가비지 컬렉션을 실행하기 위해 JVM이 애플리케이션 실행을 멈추는 작업이다.
 * GC가 실행될 때, GC가 실행하는 쓰레드를 제외한 모든 쓰레드의 작업이 중단됨. GC 작업이 종료되면 모든 쓰레드의 작업이 재개됨. 
* **MAS(Mark And Sweep)
 * Mark : 사용되는 메모리와 사용되지 않는 메모리를 식별하는 작업
 * Sweep : Mark 단계에서 사용되지 않음으로 식별된 메모리를 해제하는 작업
 * STW를 통해 모든 작업을 중단시키면, GC는 스택의 모든 변수 또는 Reachable 객체를 스캔하면서 각각이 어떤 객체를 참고하고 있는지 탐색함
 * 그리고 사용되고 있는 메모리를 식별하게 되는데, 이 과정을 **Mark라고 함**
 * Mark가 되지 않은 객체들을 메모리에서 제거하는 것을 **Sweep**이라고 함
