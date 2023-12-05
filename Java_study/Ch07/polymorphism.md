# 다형성(polymorphism)

### 다형성(polymorphism)이란?
* 객체지향개념에서는 '여러 가지 형태를 가질 수 있는 능력'을 의미.
* 자바에서는 한 타입의 참조변수로 여러 타입의 객체를 참조할 수 있도록 함.
* 조상클래스 타입의 참조변수로 자손클래스의 인스턴스를 참조할 수 있도록 함.
  ```java
  class Tv {
      boolean power;   // 전원상태(on/off)
      int channel;     // 채널

      void power()      { power = !power; }
      void channelUp()  {    ++channel;   }
      void channelDown  {    --channel;   }
  }

  class CaptionTv extends Tv {
      String text;     // 캡션을 보여 주기 위한 문자열
      void cation()     { /* 내용 생략 */}
  }

  CaptionTv c = new CaptionTv();
  Tv        t = new CaptionTv();   // 조상클래스 타입의 참조변수로 자손클래스의 인스턴스를 참조
  ```
* 실제 인스턴스가 CaptionTv 타입이라도 참조 변수 t로는 CaptionTv인스턴스의 모든 멤버 변수를 사용할 수 없다.
* Tv타입의 참조변수로는 CaptionTv인스턴스 중에 Tv 클래스의 멤버들만 사용할 수 있다.
* 둘 다 같은 인스턴스이지만 참조변수의 타입에 따라 사용 할 수 있는 멤버가 다르다.
```java
CaptionTv c = new Tv();   // 에러 발생 !
```
* 반대로 자손 타입의 참조 변수에 조상 타입의 인스턴스를 참조 할 수 없다.
> 그 이유는 CaptionTv 인스턴스가 Tv 인스턴스보다 사용할 수 있는 멤버가 더 많기 때문이다.
> CaptionTv 타입 참조 변수에서 Tv 인스턴스를 참조하고 있는데, Tv 인스턴스는 CaptionTv 멤버를 사용할 수가 없기 때문에 위 소스는 에러가 발생한다.

### 참조 변수 형변환
* 서로 상속 관계에 있는 클래스사이에서만 가능.
* 자손타입 -> 조상타입(Up-casting)   : 형변환 생략 가능
* 조상타입 -> 자손타입(Down-casting) : 형변환 생략 불가능
> 자손타입에서 조상타입으로 형 변환을 할 경우 사용할 수 있는 멤버가 더 적을 것이므로 문제 발생 여지가 없어 형 변환 생략이 가능하다.
>
> (중요) 위에서 언급했듯이 참조 변수의 타입보다 참조하는 인스턴스의 멤버가 더 적을 경우 문제가 발생한다.
* 그래서 자손 타입으로의 형변환은 생략할 수 없으므로 수행하기 전에 instanceof 연산자를 사용하여 참조 변수가 참조 하고 있는 실제 인스턴스의 타입을 확인하는 것이 중요하다.
> 형변환은 참조변수의 타입을 변환하는 것이지 인스턴스를 변환하는 것은 아니기 때문에 참조변수의 형 변환은 인스턴스에 아무런 영향을 미치지 않는다.
>
> 단지 참조변수의 형변환을 통해서, 참조하고 있는 인스턴스에서 사용할 수 있는 멤버의 범위(개수)를 조절하는 것 뿐이다.
```java
class Car {
    String color;
    int door;
    void drive() {                // 운전하는 기능
        System.out.println("driver, Brrrr~");
    }
    void stop() {                 // 멈추는 기능
        System.out.println("stop!!!");
    }
}

class FireEngine extends Car {    // 소방차
    void water() {                // 물 뿌리는 기능
        System.out.println("water!!!");
    }
}

class Ambulance extends Car {     // 구급차
    void siren() {                // 사이렌을 울리는 기능
        System.out.println("siren~~~");
    }
}
```
```java
class CastingTest {
  public static void main(String args[]) {
    Car car = new Car();
    Car car2 = null;
    FireEngine fe = null;

    car.drive();
    fe = (FireEngine)car;          // 컴파일은 OK. 실행 시 에러가 발생
    fe.drive();
    car2 = fe;
    car2.drive();
  }
}
```
* 조상타입의 참조변수를 자손타입의 참조변수로 형봔환 것이기 때문에 컴파일은 문제 없음.
* 하지만, 참조변수 Car 참조하고 있는 인스턴스가 Car 타입이기에 실행시 에러가 발생함.
* (중요) 다시 한번 말하지만 조상타입의 인스턴스를 자손타입의 참조변수로 참조하는 것은 허용되지 않는다.
* 그래서 참조변수가 가리키는 인스턴스의 타입이 무엇인지 확인하는 것이 중요하다.

### instanceof 연산자
* 참조변수가 참조하고 있는 인스턴스의 실제 타입을 알 수 있음.
* 주로 조건문에 사용되며 instanceof의 왼쪽에는 참조 변수를 오른쪽에는 타입(클래스명)이 피연산자로 위치한다.
* 연산의 결과는 boolean값인 true와 false 중의 하나를 반환한다.
> 값이 null인 참조 변수에 대해 instanceof연산을 수행하면 false를 결과로 얻는다.
```java
void doWork(Car c) {
    if (c instanceof FireEngine) {
        FireEngine fe = (FireEngine)c;
        fe.water();
        ...
    } else if (c instanceof Ambulance) {
        Ambulance a = (Ambulance)c;
        a.siren();
        ....
    }
    ....
}
```
* instanceof 연산자를 사용하는 이유는 메서드 내에 변수 c가 어느 인스턴스를 참조하고 있는지 알 수가 없어 c가 가리키고 있는 인스턴스의 타입을 체크하고 적절한 형변환한 다음에 작업을 해주기 위함이다.
* 만약, 매개 변수가 FireEngine 타입으로 선언되어 있고, Object클래스, Car클래스에 대한 instanceof 연산을 수행하면 true 가 반환된다.
* 그 이유는 Object클래스와 Car클래스는 조상클래스이므로 조상의 멤버들을 상속 받았기 때문에, FireEngine인스턴스는 Object인스턴스와 Car인스턴스를 포함하고 있는 셈이기 때문이다. (매개변수 다형성)

### 참조변수와 인스턴스의 연결
* 조상 클래스에 선언된 멤버변수와 같은 이름의 인스턴스변수를 자손 클래스에서 중복으로 정의된 경우,
  조상타입의 참조변수로 자손 인스턴스를 참조하는 경우와 자손타입의 참조변수로 자손 인스턴스를 참조하는 경우는 서로 다른 결과를 얻는다.
```java
class BindingTest {
  public static void main(String[] args) {
    Parent p = new Child();
    Child  c = new Child();

    System.out.println("p.x = " + p.x);
    p.method();

    System.out.println("c.x = " + c.x);
    c.method();
}

class Parent {
  int x = 100;

  void method() {
      System.out.println("Parent Method");
  }
}

class Child extends Parent {
  int x = 200;

  void method() {
      System.out.println("Child Method");
  }
}

실행결과
--------------
p.x = 100
Child Method
c.x = 200
Child Method
--------------
```
* 소스에서 확인할 수 있듯이 Parent 타입의 c 참조변수는 Child 인스턴스를 참조하고 있지만 중복 정의된 x 멤버변수의 값을 호출했을 때, Parent 타입의 멤버변수가 사용되었다.
* 오버라이딩된 method() 호출 시에는 참조하고 있는 Child 인스턴스의 멤버 메서드를 호출한다.
* 이러한 경우는 조상 클래스의 멤버변수와 자손 클래스의 멤버변수가 중복된 정의된 경우에만 해당되고, 중복되지 않은 경우에는 해당되지 않는다.
* 이러한 상황을 방지하기 위해서는 각 클래스의 정의된 멤버변수들을 private으로 정의하고 메서드를 통해서만 멤버변수를 접근하게 하면 된다.

### 매개변수의 다형성
```java
class Product {
  int price;                  // 제품의 가격
  int bonusPoint;             // 제품구매 시 제공하는 보너스 점수
}

class Tv       extends Product { }
class Computer extends Product { }
class Audio    extends Product { }

class Buyer {                  // 고객, 물건을 사는 사람
  int money = 1000;            // 소유금액
  int bonusPoint = 0;          // 보너스점수
}

void buy(Product p) {
  money -= p.price;             // Buyer가 가진 돈(money)에서 제품의 가격(p.price)만큼 뺀다.
  bonusPoint += p.bonusPoint;   // Buyer의 보너스점수(bonusPoint)에 제품의 보너스점수(p.bonusPoint)를 더한다.
}
```
* void Buy(Porduct p)를 보면 매개변수를 조상 클래스 타입으로 하였다.
* 매개변수의 타입을 조상 클래스 타입으로 할 경우 조상 클래스를 상속받은 자손 타입의 참조변수면 어느 것이나 매개변수로 받아들일 수 있다.
* 만약 매개변수를 Object 클래스 타입으로 하게 되면 모든 타입의 참조변수를 매개변수로 받아들일 수 있다.
> Object 클래스는 모든 클래스의 조상이므로 모든 타입의 참조변수들을 매개변수로 받아들일 수 있다.

### 여러 종류의 객체를 배열로 다루기
* 조상 타입의 참조변수 배열을 사용하면, 공통의 조상을 가진 서로 다른 종류의 객체를 배열로 묶어서 다룰 수 있다.
* 또는, 묶어서 다루고싶은 객체들의 상속관계를 따져서 가장 가까운 공통조상 클래스 타입의 참조변수 배열을 생성해서 객체들을 저장하면 된다.
```java
class Product {
  int price;                          // 제품의 가격
  int bonusPoint;                     // 제품구매 시 제공하는 보너스 점수
}

class Tv       extends Product { }
class Computer extends Product { }
class Audio    extends Product { }

class Buyer {                          // 고객, 물건을 사는 사람
  int money = 1000;                    // 소유금액
  int bonusPoint = 0;                  // 보너스점수
  Proudct[] item = new Product[10];    // 구입한 제품을 저장하기 위한 배열
  int i = 0;                           // Product배열 item에 사용할 index
}

void buy(Product p) {
  if(money < p.price) {
    System.out.println("잔액이 부족하여 물건을 살수 없습니다.");
    return;
  }

  money -= p.price;                     // Buyer가 가진 돈(money)에서 제품의 가격(p.price)만큼 뺀다.
  bonusPoint += p.bonusPoint;           // Buyer의 보너스점수(bonusPoint)에 제품의 보너스점수(p.bonusPoint)를 더한다.
  item[i++] = p;                        // 제품을 Product[] item에 저장한다.
  System.out.println(p + "을/를 구입하셨습니다.");
}
```
* buy메서드에 'item[i++] = p;' 문장을 추가함으로써 물건을 구입하면, 배열 item에 저장되도록 한다.
* 이로써 모든 제품클래스의 조상인 Product클래스 타입의 배열을 사용함으로써 구입한 제품을 하나의 배열로 간단하게 다룰 수 있게 된다.
* Product배열로 구입한 제품들을 저장할 수 있도록 했지만, 배열의 크기를 10으로 했기 때문에 11개 이상의 제품을 구입할 수 없다.
* 10개 이상의 배열의 크기를 알아서 관리해주길 원하면 Vector 클래스를 사용하면 된다.
```java
public class Vecotr extends AbastractList
              implements List, Cloneable, java.io.Serializable {
      protected Object elementData[];
}
```
* Vector 클래스는 내부적으로 Object타입의 배열을 가지고 있어서, 이 배열에 객체를 추가하거나 제거할 수 있게 작성되어 있다.
> Object타입의 배열을 가지고 있으므로 모든 참조변수 타입들을 담을 수 있다.
* Vector의 주요 메서드로는 add(), remove(), get() 등이 있다.
> boolean add(Object o)     : Vector에 객체를 추가한다. 추가에 성공하면 결과값으로 true, 실패하면 false를 반환한다.
>
> boolean remove(Object o)  : Vector에 저장되어 있는 객체를 제거한다. 제거에 성공하면 결과값으로 true, 실패하면 false를 반환한다.
>
> Object  get(int index)    : 지정된 위치(index)의 객체를 반환한다. 반환타입이 Object타입이므로 적절한 타입으로의 형변환이 필요하다.
* get() 메서드를 호출하여 적절한 타입의 형변환을 한 후 instanceof 연산자를 사용하여 꼭 인스턴스의 실제 타입을 확인하여야 한다. 
