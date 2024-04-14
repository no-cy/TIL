# Graceful Shutdown
* graceful: 단아한, 우아한, 품격있는 등
스프링 부트 서버를 종료할 때에 현재 진행 중인 모든 작업을 안전하게 마무리하고, 새로운 요청은 받지 않으면서 기존의 요청을 정상적으로 완료시키는 과정

### **graceful shutdown 활성화 방법**
```yml
server:
	shutdown: graceful // 기본 값은 immediate
```

### **서버(프로세스) 종료 방식(linux kill)**
서버(프로세스)를 종료시키기 위해서는 주로 linux의 **kill 커맨드**를 사용한다.
```shell
$ kill -l
 1) SIGHUP	 2) SIGINT	 3) SIGQUIT	 4) SIGILL	 5) SIGTRAP
 6) SIGABRT	 7) SIGBUS	 8) SIGFPE	 9) SIGKILL	10) SIGUSR1
11) SIGSEGV	12) SIGUSR2	13) SIGPIPE	14) SIGALRM	15) SIGTERM
16) SIGSTKFLT	17) SIGCHLD	18) SIGCONT	19) SIGSTOP	20) SIGTSTP
21) SIGTTIN	22) SIGTTOU	23) SIGURG	24) SIGXCPU	25) SIGXFSZ
26) SIGVTALRM	27) SIGPROF	28) SIGWINCH	29) SIGIO	30) SIGPWR
31) SIGSYS	34) SIGRTMIN	35) SIGRTMIN+1	36) SIGRTMIN+2	37) SIGRTMIN+3
38) SIGRTMIN+4	39) SIGRTMIN+5	40) SIGRTMIN+6	41) SIGRTMIN+7	42) SIGRTMIN+8
43) SIGRTMIN+9	44) SIGRTMIN+10	45) SIGRTMIN+11	46) SIGRTMIN+12	47) SIGRTMIN+13
48) SIGRTMIN+14	49) SIGRTMIN+15	50) SIGRTMAX-14	51) SIGRTMAX-13	52) SIGRTMAX-12
53) SIGRTMAX-11	54) SIGRTMAX-10	55) SIGRTMAX-9	56) SIGRTMAX-8	57) SIGRTMAX-7
58) SIGRTMAX-6	59) SIGRTMAX-5	60) SIGRTMAX-4	61) SIGRTMAX-3	62) SIGRTMAX-2
```

위 옵션 중 많이 사용하는 옵션은 `9) SIGKILL` 와 `15) SIGTERM` 이다
- **SIGKILL(-9)**: 프로세스를 즉시 종료. 따라서, 처리중이던 작업들의 유무에 관계 없이 즉시 종료된다.
- **SIGTERM(-15)**: 프로세스를 정상적으로 종료. 프로세스에서 종료하라는 시그널을 전달

따라서, 프로세스를 정상적으로 종료시켜줘야 Graceful-Shutdown을 사용할 수 있다.  
kill -15 명령어가 실행된다면 Spring의 SpringApplicationShutdownHook 이라는 객체를 통해 Spring을 종료시키기 시작한다.

만약, Shutdown 실행 중 무한루프를 돌거나 데드락이 걸렸을 경우 어떻게 해야할까?

### Graceful shutdown의 timeout 설정
```yaml
spring:
	lifecycle:
		timeout-per-shutdown-phase: 5s
```

위와 같이 timeout-per-shutdown-phase 옵션을 주면 shutdown 최대 대기 시간을 지정할 수 있다.

```java
final class GracefulShutdown {

	// ...

	void abort() {
		this.aborted = true;
	}
}
```

Graceful shutdown이 5초가 넘어간다면 GracefulShutdown 객체의 `abort()` 메서드를 통해 Graceful shutdown aborted 값을 true로 변경시키고, 아래 코드의 `doShutdown()` 메서드의 while 문 안에 aborted 조건문이 true가 되어 Graceful shutdown이 종료된다.

```java
final class GracefulShutdown {

	// ...

	void shutDownGracefully(GracefulShutdownCallback callback) {
		logger.info("Commencing graceful shutdown. Waiting for active requests to complete");
		new Thread(() -> doShutdown(callback), "tomcat-shutdown").start();
	}

	private void doShutdown(GracefulShutdownCallback callback) {
		List<Connector> connectors = getConnectors();
		connectors.forEach(this::close);
		try {
			for (Container host : this.tomcat.getEngine().findChildren()) {
				for (Container context : host.findChildren()) {
					while (isActive(context)) {
						if (this.aborted) { // true로 변경되어서 Graceful Shutdown이 종료됨
							logger.info("Graceful shutdown aborted with one or more requests still active");
							callback.shutdownComplete(GracefulShutdownResult.REQUESTS_ACTIVE);
							return;
						}
						Thread.sleep(50);
					}
				}
			}

		}
		catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		logger.info("Graceful shutdown complete");
		callback.shutdownComplete(GracefulShutdownResult.IDLE);
	}
```
