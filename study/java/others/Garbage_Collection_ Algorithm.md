# 가비지 컬렉션 알고리즘(Garbage Collection Algorithm)

JVM이 메모리를 자동으로 관리해주는 것은 개발자의 입장에서 상당한 메리트임
하지만 문제는 GC를 수행하기 위해 Stop The World에 의해 애플리케이션이 중지되는 것
Heap의 사이즈가 커지면서 애플리케이션의 지연(Suspend) 현상이 두드러지게 되었고, 이를 막기 위해 다양한 Garbage Collection(가비지 컬렉션) 알고리즘을 지원하고 있음

* Serial GC
  > 하나의 CPU로 Young 영역과 Old 영역을 연속적으로 처리하는 방식.
  > GC가 수행될 때 STW(Stop The World)가 발생.
* Parallel GC
  > Parallel GC의 목표는 다른 CPU가 GC의 진행시간 동안 대기 상태로 남아 있는 것을 최소화 하는 것.    
  > Serial GC의 Young 영역에서 진행하는 방식을 병렬로 처리하여 부하를 줄인다.
* Parallel Old GC
  > Parallel GC에서 사용하던 Mark Sweep Compation 대신 개선 버전인 Mark Summary Compaction 알고리즘을 사용.
  > Parallel GC는 Minor GC에 대해서 멀티 스레딩으로 수행하는 것에 반해, Parallel Old GC는 Major GC에 대해서도 멀티 스레딩으로 수행한다.
* Concurrent Mark-Sweep(CMS) GC
  > Application의 Thread와 GC Thread가 동시에 실행되어 STW를 최소화 하는 GC이다.    
  > Parallel GC와 가장 큰 차이점은 Compaction 작업 유무로 구분될 수 있다.    
  > Compaction은 메모리 공간에서 사용하지 않는 빈 공간이 없도록 옮겨서 메모리 분산을 제거하는 작업을 의미.     
  > CMS GC는 Compacton을 지원하지 않는다.
* G1(Garbage First) GC
  > G1 GC는 CMS GC를 대체하기 위해 새롭게 등장하였으며, 대용량의 메모리가 있는 멀티 프로세서 시스템을 위해 제작되었다.    
  > 빠른 처리 속도를 지원하면서 STW를 최소화    
  > CMS GC보다 효율적으로 동시에 Application과 GC를 진행할 수 있고, 메모리 Compaction 과정까지 지원하고 있다.     
  > Java 9 버전부터 기본 GC 방식으로 채택
