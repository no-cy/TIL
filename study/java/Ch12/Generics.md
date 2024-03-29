# 제네릭(Generics)

* 제네릭은 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에 컴파일 시의 타입체크(compile - time type check)를 해주는 기능이다.  
* 객체의 타입을 컴파일 시에 체크하기 때문에 객체의 "타입 안정성"을 높이고 형변환의 번거로움이 줄어든다.  
* "타입 안정성"을 높인다는 것은 의도하지 않은 타입의 객체가 저장되는 것을 막고, 저장된 객체를 꺼내올 때 원래의 타입과 다른 타입으로 잘못 형변환되어 발생할 수 있는 오류를 줄여준다는 뜻.

### 제네릭의 장점
* 타입 안정성을 제공한다.
* 타입체크와 형변환을 생략할 수 있으므로 코드가 간결해 진다.
* 한 줄 요약하자면, 다룰 객체의 타입을 미리 명시해줌으로써 번거로운 형변환을 줄여준다.

### 제네릭 선언
```java
class Box<T> {    // 지네릭 타입 T를 선언
  T item;

  void setItem(T item) { this.item = item; }
  T getTime() { return item; }
}
```
* Box<T>에서 T를 '타입 변수(type variable)'라고 하며, 'Type'의 첫 글자에서 따온 것이다.
* 타입 변수 E는 'Element(요소)'의 첫 글자를 따온 것이다.
* 타입 변수가 여러 개인 경우에는 Map(K,V)와 같이 콤마','를 구분자로 나열하면 된다. (K는 Key, V는 Value)
* 상황에 맞게 의미있는 문자를 선택해서 사용하는 것이 좋다.
* 위는 기호의 종류만 다를 뿐 '임의의 창조형 타입'을 의미한다는 것은 모두 같다.
