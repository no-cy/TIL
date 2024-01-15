# 서블릿 필터와 스프링 빈
* 서블릿 필터는 서블릿 컨테이너 수준에서 작동되어 스프링 컨텍스트와는 독립적이였음.
* 스프링 빈으로도 등록이 불가능하였으며, 빈을 주입받을 수 없었음.
* 그러나 필터에서도 DI와 같은 스프링 기술을 필요로 하는 상황이 발생
* 필터도 스프링 빈을 주입받을 수 있도록 **DelegatingFilterProxy**가 등장하면서 스프링 빈으로 등록이 가능해졌다.

**DelegatingFilterProxy**
* DelegatingFilterProxy는 서블릿 컨테이너에서 관리되는 프록시용 필터로써 우리가 만든 필터를 가지고 있다
* 개발자가 만든 필터는 스프링 컨테이너의 빈으로 등록되는데, **요청이 오면 DelegatingFilterProxy가 요청을 받아서 우리가 만든 필터(스프링 빈)에게 요청을 위임한다.**

**동작원리**
1. Filter 구현체가 스프링 빈으로 등록됨
2. ServletContext가 Filter 구현체를 갖는 DelegatingFilterProxy를 생성함
3. ServletContext가 DelegatingFilterProxy를 서블릿 컨테이너에 필터로 등록함
4. 요청이 오면 DelegatingFilterProxy가 필터 구현체에게 요청을 위임하여 필터 처리를 진행함

**정리**
* DelegatingFilterProxy가 등장하면서 Filter를 스프링 빈으로 관리할 수 있게 되었다. (DI 기술이 필요한 상황 발생)
* ServletContext가 DelegatingFilterProx를 서블릿 컨테이너에 필터로 등록하여 http 요청이오면 DelegatingFilterProxy가 필터 구현체에 요청을 위임하여 필터 처리를 진행한다.
---
## Spring Boot 등장 이후
* DelegatingFilterProxy는 **스프링**이기 때문에 필요한 것이지, **스프링 부트를 사용한다면 DelegatingFilterProxy도 필요없어진다.**
* Spring Boot가 내장 웹 서버를 지원하면서 톰캣과 같은 서블릿 컨테이너까지 Spring Boot가 제어 가능하기 때문이다.
* Spring Boot가 서블릿 필터의 구현체를 찾으면 DelegatingFilterProxy 없이 바로 **필터 체인(Filter Chain)** 에 필터를 등록해 준다.
