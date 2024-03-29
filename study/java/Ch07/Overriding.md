# 오버라이딩(Overriding)

### 오버라이딩(Overriding)이란?
* 조상 클래스로부터 상속 받은 메서드를 재 정의하는 것
* 자손 클래스에서 자신에 맞게 메서드를 변경해야하는 경우 조상 클래스의 메서드를 '오버라이딩'한다.

### 오버라이딩의 조건
* 조상 클래스와 자손 클래스의 메서드 선언부가 완전히 일치하여야 한다. (이름, 매개변수, 반환타입)
> JDK 1.5 버전부터 '공변 반환타입(convariant return type)'이 추가되어, 반환타입을 자손 클래스의 타입으로 변경하는 것은 가능하도록 조건이 완화되었다.
* 다만, 접근 제어자(access modifier)와 예외(exeception)는 제한된 조건 하에서만 다르게 변경할 수 있다.

### 오버라이딩에서 접근 제어자(access modifier)와 예외(exeception)의 제한된 조건
* 자손 클래스에서 접근 제어자는 조상 클래스의 메서드보다 좁은 범위로 변경할 수 없다.
  * 조상 클래스에 정의된 메서드의 접근 제어자가 protected라면, 이를 오버라이딩하는 자손 클래스의 메서드는 접근 제어자가 protected나 public이어야 한다.
  > 접근 제어자의 접근 범위 (넓은 것에서 좁은 것 순)
  >
  > public > protected > (default) > private
* 조상 클래스의 메서드보다 많은 수의 예외를 선언할 수 없다.
  * 예를 들어, 조상 클래스에 A()라는 메서드에 예외가 IOException, SQLException이 선언되어 있고, 자손 클래스에 A()라는 메서드에 Exception 예외를 선언하면 에러가 발생하게 된다.
  * Exception은 모든 예외의 최고 조상이므로 가장 많은 개수의 예외를 던질 수 있도록 선언한 것이므로 조상 클래스의 메서드보다 많은 수의 예외를 선언하였으므로 에러가 발생한다.
* 인스턴스 메서드를 static 메서드로 또는 그 반대로 변경할 수 없다.

### statis 메서드 오버라이딩
* 조상 클래스에서 정의된 static 메서드를 자손 클래스에서 똑같은 이름의 static 메서드로 정의할 수 있을까??
  * 가능하다. 다만, static 메서드는 각 클래스에 별개로 정의된 메서드이므로 오버라이딩이라고 할 수 없다. static 멤버들은 자신들이 정의된 클래스에 묶여있기 때문이다.   
