# 생성자(Constructor)

### 생성자(Constructor)란?
* 생성자는 인스턴스가 생성될 때 호출되는 '인스턴스 초기화 메서드'이다.
* 인스턴스 변수의 초기화 작업에 주로 사용되며, 인스턴스 생성 시에 실행되어야 할 작업을 위해서도 사용된다.
* 메서드처럼 클래스 내에 선언되어야 한다.

### 생성자(Constructor) 특징
* 생성자의 이름은 클래스의 이름과 같아야 한다.
* 생성자는 리턴 값이 없다.
* 생성자도 메서드이기 때문에 리턴 값이 없다는 의미의 void를 붙여야 하지만, 모든 생성자가 리턴값이 없으므로 void를 생략할 수 있다.

### 기본 생성자(default constructor)
* 모든 클래스에서는 반드시 하나 이상의 생성자가 정의되어 있어야 함.
* 클래스에 생성자를 정의하지 않고도 인스턴스를 생성할 수 있었던 이유는, 소스파일(*.java)의 클래스에 생성자가 하나도 정의되지 않은 경우 
  컴파일러에 의해 자동으로 생성되는 '기본 생성자(default constructor)' 덕분이다.
* 클래스의 '접근 제어자(Access Modifier)가 public 인 경우에는 기본 생성자로 'public 클래스이름() {}'이 추가 된다.
* 컴파일러가 자동적으로 기본 생성자를 추가해주는 경우는 '클래스에 정의된 생성자가 하나도 없을 때'이다.

### 매개 변수가 있는 생성자
* 매개 변수가 있는 생성자를 사용한다면 인스턴스를 생성하는 동시에 원하는 값으로 초기화 할 수 있다.

### 생성자에서 다른 생성자 호출하기 - this(), this
* 생성자 간에도 서로 호출이 가능하다. 단, 다음의 두 조건을 만족하여야 한다.
  * 생성자의 이름으로 클래스 이름 대신 this를 사용한다.
  * 한 생성자에서 다른 생성자를 호출할 때는 반드시 첫 줄에서만 호출이 가능하다.
    > 생성자 내에서 초기화 작업 도중에 다른 생성자를 호출하게 되면, 호출된 다른 생성자 내에서도 멤버변수들의 값을 초기화를 할 것이므로 다른 생성자를 호출하기 이전의 초기화 작업이 무의미해 짐.
* 만약, 생성자의 매개변수로 선언된 변수의 이름이 인스턴스 변수와 같을 경우 this를 사용하여 구별하도록 한다.
* 'this'는 참조변수로 인스턴스 자신을 가리킨다. 'this'로 인스턴스 변수에 접근할 수 있다.
  > this : 인스턴스 자신을 가리키는 참조변수, 인스턴스의 주소가 저장되어 있다.
           모든 인스턴스메서드에 지역변수로 숨겨진 채로 존재한다.
  
  > this(), this(매개변수) : 생성자, 같은 클래스의 다른 생성자를 호출할 때 사용한다.
