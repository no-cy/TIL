# [TIL] 2023.12.25 🧑🏻‍🏫

## 차주 멘토링 전까지 공부해야할 내용
1. Filter &rarr; **스프링 사용여부**
2. REST API
3. 싱글톤 패턴 **수도코드** (인텔리제이)
4. 스프링 빈 생명주기, 빈 스코프
5. 프로젝트 1차 설계 방안 &rarr; **구매 및 결제 시스템**


## 메모리 단편화 💡
메모리 공간이 충분함에도 불구하고, 연속적이고 충분한 크기의 메모리 영역을 할당할 수 없는 상태

* 내부 단편화 : 메모리 공간을 할당한 후 할당된 블록내에서 사용하지 않는 공간이 발생하는 것
* 외부 단편화 : 메모리 공간이 충분함에도 불구하고, 이 공간이 작은 조각들로 나뉘어져 연속된 메모리 공간을 할당받지 못하는 상태

## Filter, interceptor, AOP 💡
**Filter**
* 서블릿 필터로서, 서블릿 컨테이너 수준에서 동작
* 스프링 컨텍스트와는 독립적으로 작동 &rarr; **`스프링 컨텍스트와 독립적이여도 스프링을 사용할 수 있다(?) 찾아보자..`**
* HTTP 수준에서의 다양한 공통 작업을 처리

**Interceptor**
* 스프링 MVC의 컨트롤러에 도달하기 전/후로 실행
* 스프링 컨텍스트의 모든 빈에 접근이 가능
* 컨트롤러의 실행과정을 좀 더 세밀하게 관리하고, 스프링이 관리하는 자원들을 활용

**AOP**
* 메서드 호출 전/후, 예외 발생 시점 등 다양한 시점에 advice(실행될 로직)를 삽입하여 작업을 수행
* 스프링 프레임워크와 강하게 통합되어있어, 스프링의 다른 요소들과의 연동이 매우 용이
* 선언적 트랜잭션 관리, 로깅, 보안 등의 크로스컷팅 관심사를 처리해야하는데 매우 효과적

### Q. 컨트롤러 시점에서 Interceptor와 AOP의 차이점은 무엇일까 ?
* AOP는 Proxy라는 기술을 사용하여 크로스 컷팅 관심사를 처리함.
* **파라미터에 차이가 있음.**
  * Interceptor는 HttpServletRequest, HttpServletResponse를 파라미터로 사용함.
  * AOP는 JoinPoint, ProceedingJoinPoint를 파라미터로 사용
* **주요 처리 관심사**
  * Interceptor는 HTTP 요청의 전처리와 후처리, 특히 웹 애플리케이션의 요청 수준에 작동함.
  * AOP는 애플리케이션의 비즈니스 로직 자체에 더 집중되어 있으며, 메서드 수준에서의 관심사를 중앙 집중화하여 관리함.



## 스프링 빈 생명주기
데이터베이스 커넥션 풀이나, 네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고, 애플리케이션 종료 시점에 연결을 모두 종료하는 작업을 진행하려면, 
객체의 초기화와 종료 작업이 필요하다.

### **`스프링 빈의 이벤트 라이프사이클`**

**스프링 컨테이너 생성** &rarr; **스프링 빈 생성** &rarr; **의존관계 주입** &rarr; **초기화 콜백** &rarr; **사용** &rarr; **소멸전 콜백** &rarr; **스프링 종료**

> **초기화 콜백**: 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
>
> **소멸전 콜백**: 빈이 소멸되기 직전에 호출

최근 현업에서는 초기화 콜백과 소멸전 콜백은 **`@PostConstruct, @PreDestroy`** 어노테이션을 사용
* 최근 스프링에서 가장 권장하는 방법
* 어노테이션 하나만 붙이면 되므로 매우 편리하다
* 스프링에 종속된 기술이 아닌 JSR-250라는 자바 표준임. 따라서 스프링이 아닌 다른 컨테이너에서도 동작함
* 컴포넌트 스캔과 잘 어울린다
* 유일한 단점은 외부 라이브러리에는 적용하지 못한다는 것
* 외부 라이브러리를 초기화, 종료해야하면 `@Bean` 기능(옵션) 사용을 권장  
&rarr; `@Bean(initMethod = "init", destroyMethod = "close")`

> **참고: 객체의 생성과 초기화를 분리하자.**
> 
> 생성자는 필수 정보(파라미터)를 받고, 메모리를 할당해서 객체를 생성하는 책임을 가진다. 반면에 초기화는 이렇게 생성된 값들을 활용해서 외부 커넥션을 연결하는등 무거운 동작을 수행한다.  
> 따라서 생성자 안에서 무거운 초기화 작업을 함께 하는 것 보다는 객체를 생성하는 부분과 초기화하는 부분을 명확하게 나누는 것이 유지보수 관점에서 좋다. 물론 초기화 작업이 내부 값들만 약간 변경하는 정도로 단순한 경우에는 생성자에서 한번에 다 처리하는게 더 나을 수 있다.

> **참고:** 싱글톤 빈들은 스프링 컨테이너가 종료될 때 싱글톤 빈들도 함께 종료되기 때문에 스프링 컨테이너가 종료되기 직전에 소멸전 콜백이 일어난다.  싱글톤 처럼 컨테이너의 시작과 종료까지 생존하는 빈도 있지만, 생명주기가 짧은 빈들도 있는데 이 빈들은 컨테이너와 무관하게 해당 빈이 종료되기 직전에 소멸전 콜백이 일어난다.
