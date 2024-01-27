# [TIL] 2024.01.24 🧑🏻‍🏫

### 1. 트랜잭션 전파속성(Propagation) 시연
* `REQUIRES_NEW`, `NESTED` 시연 진행
* `REQUIRED` 속성과 달리 `RuntimeException` 발생 시 rollback marking이 되지 않는다.
* **`try ~ catch`문 사용 시 내부 트랜잭션에서 `RuntimeException`이 발생하여 외부 트랜잭션에 전파되어도 `rollback`이 되지 않는다.**
> **참고:** `try ~ catch`문 특성 상 예외를 외부 메서드(호출한 메서드)에 전파하지 않는다.
```java
// 내부 트랜잭션
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TxRequiresNewService { 
...
  public void executeInNewTransaction(int value) {
          txRepository.save(value);
          throw new RuntimeException("executeInNewTransaction 런타임 에러 발생");
  }
}
```
```java
// 외부 트랜잭션
@Service
@Transactional
public class TxService { 
...
  public void callTxRequiresNewMethod(int value) {
        try {
            txRequiresNewService.executeInNewTransaction(value);
        } catch (RuntimeException e) {
            // RuntimeException이 잡혀 @Transactional에 rollback 여부가 전파되지 않는다.
            System.out.println("callTxRequiresNewMethod 런타임 에러 발생.");
        }
  }
}
```
* 다만, **내부 트랜잭션 전파 속성이 `Required`일 경우 외부 트랜잭션에서 `try ~ catch`로 예외를 잡아도 `rollback`이 일어난다.**
> **참고:** [https://techblog.woowahan.com/2606/]
* 한 클래스에 여러 개의 메서드에서 @Transaction 전파 속성이 정의되어 있다고 가정할 때, 선언적 트랜잭션(`@Transaction`)이 정상적으로 동작하지 않는다.
```java
@Service
public class TxService {
...
  // 트랜잭션 기본 속성 Required
  @Transactional
  public void callTxRequiredMethod(int value) {
        try {
            txRequiredService.executeInRequiredTransaction(value);
        } catch (RuntimeException e) {
            System.out.println("callTxRequiresNewMethod 런타임 에러 발생.");
        }
  }

  @Trasactional(propagation = Propagation.NESTED)
  public void callTxNestedNewMethod(int value) {
        try {
            txNestedService.executeInNestedTransaction(value);
        } catch (RuntimeException e) {
            System.out.println("callTxNestedNewMethod 런타임 에러 발생.");
        }
  } 
}
```
> **참고:** [https://itjava.tistory.com/33]
---
### 2. 프로젝트 ERD & REST API 설계 검토
* REST API 설계안을 바탕으로 주문 시스템 실행 시나리오를 생각하였을 때, 수정되어야 하는 부분이 없는지 검토가 필요함.
```
예를 들어, cart(장바구니) 테이블의 cart_id는 primary key이며, 속성은 AUTO_INCREMENT로 정의되어 있음.
사용자는 고유의 cart_id를 가지고 있으며, cart 테이블에 상품을 담을 때 각 사용자의 고유 cart_id가 삽입되어야 한다는 생각을 가지고 설계를 진행하였음.
하지만, AUTO_INCREMENT는 sequnce처럼 데이터가 삽일될 때마다 1씩 증가하는 속성이므로, 개발자의 의도와 다르게 프로그램이 실행됨.
```
* 장바구니만이 아니라 다른 항목의 REST API를 검토하였을 때 위와 같은 사항이 없는지 검토가 필요함.
---
### 3. 다음 멘토링까지 준비해야 할 리스트
* 실습
  * 우아한형제들 - 응? 이게 왜 롤백이 되는거지? [(바로가기)](https://techblog.woowahan.com/2606/)
* [Spring - Transaction(트랜잭션)의 모든 것](https://itjava.tistory.com/33) 정독
* 프로젝트 ERD & REST API 최종 검토
* 스프링 강의 - 스프링 핵심 원리 고급편 완강
