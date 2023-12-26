# 빈 스코프

## * 빈 스코프 : **빈이 존재할 수 있는 범위**  
스프링 빈이 스프링 컨테이너의 시작과 함께 생성되어서 스프링 컨테이너가 종료될 때까지 유지되는데, 이것은 **스프링 빈이 기본적으로 싱글톤 스코프로 생성되기 때문이다.**

스프링은 다음과 같은 다양한 스코프를 지원한다.
* 싱글톤 : 기본 스코프, 스프링 컨테이너의 시작과 종료까지 유지되는 가장 넓은 범위의 스코프이다.
* 프로토타입 : 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 매우 짧은 범위의 스코프이다.
* 웹 관련 스코프
  * request : 웹 요청이 들어오고 나갈때 까지 유지되는 스코프이다.
  * session : 웹 세션이 생성되고 종료될 때 까지 유지되는 스코프이다.
  * application : 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프이다.

빈 스코프는 다음과 같이 지정할 수 있다.

**컴포넌트 스캔 자동 등록**
```java
@Scope("prototype")
@Component
public class HelloBean {}
```
**수동 등록**
```java
@Scope("prototype")
@Bean
PrototypeBean HelloBean() {
  return new HelloBean();
}
```

## 프로토타입 스코프
싱글톤 스코프의 빈을 조회하면 스프링 컨테이너는 항상 같은 인스턴스의 스프링 빈을 반환한다.  
**반면에 프로토타입 스코프를 스프링 컨테이너에 조회하면 스프링 컨테이너는 항상 새로운 인스턴스를 생성해서 반환한다.**

* 스프링 컨테이너는 **프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리한다.**  
* 클라이언트에 빈을 반환하고, 이후 스프링 컨테이너는 생성된 프로토타입 빈을 관리하지 않는다.  
* 프로토 타입 빈을 관리할 책임은 프로토타입 빈을 받은 클라이언트에 있다.  
* 그래서 `@PreDestroy` 같은 종료 **메서드가 호출되지 않는다.**

## 프로토타입 스코프 - 싱글톤 빈과 함께 사용시 문제점
* 싱글톤 빈에서 프로토타입 빈을 내부 필드에 보관하면(정확히는 참조값을 보관), 싱글톤 빈을 스프링 컨테이너에 요청할 때 마다 새롭게 생성된 프로토타입 빈이 아닌 이전에 생성된 프로토타입 빈이 호출된다.  
* 싱글톤 빈이 내부에 가지고 있는 프로토타입 빈은 이미 과거에 주입이 끝난 빈이다.  
* 주입 시점에 스프링 컨테이너에 요청해서 프로토타입 빈이 생성된 것이지, 사용할 때마다 새로 생성되는 것은 아니다.  
* 스프링은 일반적으로 싱글톤 빈을 사용하므로, 싱글톤 빈이 프로토타입 빈을 사용한다.  
* 싱글톤 빈은 생성시점에만 의존관계 주입을 받기 때문에, 프로토타입 빈이 생성되기는 하지만, 싱글톤빈과 함께 계속 유지되는 것이 문제다.  
* 사용할 때마다 새로 생성해서 프로토타입 빈을 반환받기 위해서는 `Provider`를 사용해야 한다.

## ObjectProvider
지정한 빈을 컨테이너에서 대신 찾아주는 DL(Dependency Lookup) 서비스를 제공하는 것이 ObjectProvider이다. 
> DL(Dependency Lookup) : 의존관계를 외부에서 주입(DI)받는게 아니라 직접 필요한 의존관계를 찾는 것을 DL 또는 의존관계 조회(탐색)라 한다.
```java
@Autowired
 // 스프링 컨테이너에서 PrototypeBean을 찾기 위해 ObjectProvider<PrototypeBean> 타입 객체를 선언 
 private ObjectProvider<PrototypeBean> prototypeBeanProvider;

 public int logic() {
     // getObject() 메소드를 호출하여 스프링 컨테이너에 prototypeBean을 찾아 새로운 프로토타입 빈을 반환함.(DL)
     PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
     prototypeBean.addCount();
     int count = prototypeBean.getCount();
     return count;
}
```
스프링에서 제공하는 기능을 사용하지만, 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기는 훨씬 쉬워진다.

## JSR-330 Provider
`jakarta.inject:jakarta.inject-api:2.0.1` 라이브러리를 gradle에 추가해야 한다.

사용 방법은 ObjectProvider와 동일하다.
다만, `getObject()`가 아닌 `get()` 메서드를 사용한다.
```java
@Autowired
 private Provider<PrototypeBean> provider;

 public int logic() {
     PrototypeBean prototypeBean = provider.get();
     prototypeBean.addCount();
     int count = prototypeBean.getCount();
     return count;
}
```
* 자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용할 수 있다
* 단위 테스트에 매우 용이하다
* 다만, 라이브러리와 의존관계가 추가되어야 한다

### 스프링에서만 사용한다면 ObjectProvider를,  
### 스프링이 아닌 다른 컨테이너에서도 사용되어야 한다면 JSR-330 Provider를 사용하자

## 웹 스코프
* 웹 스코프는 웹 환경에서만 동작한다. &rarr; **테스트를 진행하기 위해서는 Spring Web 라이브러리를 추가하여아한다.**  
* **프로토타입과 다르게 스프링이 해당 스코프의 종료시점까지 관리한다.** 따라서 종료 메서드가 호출된다.

**웹 스코프 종류**
* request : HTTP 요청 하나가 들어오고 나갈 때 까지 유지되는 스코프, 각각의 HTTP 요청마다 별도의 빈 인스턴스가 생성되고, 관리된다.
* session : HTTP Session과 동일한 생명주기를 가지는 스코프
* application : 서블릿 컨텍스트(`ServletContext`)와 동일한 생명주기를 가지는 스코프
* websocket : 웹 소켓과 동일한 생명주기를 가지는 스코프

> **참고** : 스프링 부트는 웹 라이브러리가 없으면 우리가 지금까지 학습한 `AnnotationConfigApplicationContext` 을 기반으로 애플리케이션을 구동한다.  웹 라이브러리가 추가되
면 웹과 관련된 추가 설정과 환경들이 필요하므로 `AnnotationConfigServletWebServerApplicationContext` 를 기반으로 애플리케이션을 구동한다.
   
### request
* `@Scope(value = "request)"` 를 사용해서 request 스코프 빈을 지정할 수 있음
* HTTP 요청 하나가 들어오고 나갈 때 까지 유지됨
> **참고** : request scope를 사용하지 않고 파라미터로 이 모든 정보를 서비스 계층에 넘긴다면, 파라미터가 많아서 지저분해진다. 더 문제는 requestURL 같은 웹과 관련된 정보가 웹과 관련없는 서비스 계층까지 넘어가게 된다. **웹과 관련된 부분은 컨트롤러까지만 사용해야 한다.** 서비스 계층은 웹 기술에 종속되지 않고, 가급적 순수하게 유지하는 것이 유지보수 관점에서 좋다.

**request scope를 사용할 때 주의해야하는 점**
* request scope인 빈을 사용할 경우 스프링 애플리케이션이 실행될 때 에러가 발생한다.
* 그 이유는, request scope 빈은 HTTP 요청이 올 때만 생성이되므로 HTTP 요청이 없는 경우 생성되지 않는 빈이다.
* 에러가 발생하지 않도록 하는 방법은 총 두 가지 방법이 있다.

**앞서 배운 ObjectProvider를 사용하는 것**
* ObjectProvider를 사용함으로써 ObjectProvider.getObject()를 호출하는 시점까지 request **빈의 생성을 지연**할 수 있다.

**프록시 방식**
* Scope 어노테이션에 `proxyMode = ScopedProxyMode.TARGET_CLASS` 옵션을 추가
```java
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
}
```
* 적용 대상이 클래스면 `TARGET_CLASS`를 선택
* 적용 대상이 인터페이스면 `INTERFACES`를 선택
* 위와 같이 `proxyMode`를 적용하면 MyLogger의 가짜 프록시 클래스를 만들어두고 HTTP request와 상관 없이 가짜 프록시 클래스를 다른 빈에 미리 주입해 둘 수 있다.
* 프록시를 사용하게 되면 코드 수정 부분이 **Scope에 proxyMode를 추가하는 것 말고는 없다.**

## 웹 스코프와 프록시 동작 원리
* **CGLIB라는 라이브러리로 내 클래스를 상속 받은 가짜 프록시 객체를 만들어서 주입한다.**
* CGLIB라는 바이트코드를 조작하는 라이브러리를 사용해서, MyLogger를 상속받은 가짜 프록시 객체를 생성한다.
* 스프링 컨테이너에 "MyLogger"라는 이름으로 진짜 대신에 가짜 프록시 객체를 등록한다.
* `ac.getBean("myLogger", MyLogger.class)` 로 조회해도 프록시 객체가 조회되는 것을 확인할 수 있다.
* 의존관계 주입도 가짜 프록시 객체가 주입된다.

**가짜 프록시 객체는 요청이 오면 그때 내부에서 진짜 빈을 요청하는 위임 로직이 들어있다.**
* 이 가짜 프록시 객체는 실제 요청이 오면 그때 내부에서 실제 빈을 요청하는 위임 로직이 들어있다.
* 가짜 프록시 객체는 실제 request scope와는 관계가 없다. 그냥 가짜이고, 내부에 단순한 위임 로직만 있고, 싱글톤처럼 동작한다.

### 특징 정리
* 프록시 객체 덕분에 클라이언트는 마치 싱글톤 빈을 사용하듯이 편리하게 request scope를 사용할 수 있다.
* 사실 Provider를 사용하든, 프록시를 사용하든 핵심 아이디어는 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점이다.
* 단지 애노테이션 설정 변경만으로 원본 객체를 프록시 객체로 대체할 수 있다. 이것이 바로 다형성과 DI 컨테이너가 가진 큰 강점이다.
* 꼭 웹 스코프가 아니어도 프록시는 사용할 수 있다.

### 주의점
* 마치 싱글톤을 사용하는 것 같지만 다르게 동작하기 때문에 결국 주의해서 사용해야 한다.
* 이런 특별한 scope는 꼭 필요한 곳에만 최소화해서 사용하자, 무분별하게 사용하면 유지보수하기 어려워진다.
