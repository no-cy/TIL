# [TIL] 2024.01.24 🧑🏻‍🏫

### 1. 트랜잭션 전파속성(Propagation) 시연
* REQUIRES_NEW, NESTED 시연 진행
* 외부 트랜잭션에서 try ~ catch로 내부 트랜잭션의 RuntiemException을 캐치했을 경우 우리가 아는 각 트랜잭션 전파속성 특징에 맞게 동작한다.
* **하지만, 외부 트랜잭션에서 try ~ catch문을 사용하지 않고 내부 트랜잭션에서 RuntimeException이 발생했을 경우 전파속성 관계 없이 외부 트랜잭션에 영향을 준다.**
* [(참고 소스) click👆]()

