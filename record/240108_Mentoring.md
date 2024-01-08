# [TIL] 2023.01.08 🧑🏻‍🏫

### 1. 프로젝트 1차 설계 [(@v1_buy_system)](../project-design/contant/v1_buy_system_2023_01_08.md)
* 작성한 ERD 토대로 리뷰 진행 [(@ERD)](../project-design/erd/v1_2023_01_08.svg) &rarr; ERD 이미지가 너무 작다.. 수정 필요
* 상품(proudct) 테이블에 sale_start_date, sale_end_date date 타입 컬럼 추가
---
### 2. 프로젝트 REST API 설계 진행 필요
* 멘토님께서 화면 공유로 직접 작성해 보라고 요청주셨지만 내가 제대로 작성하지 못해 멘토님께서 수정해 주셨다 🙇‍♂️ 
* v1_buy_system_2023_01_08.md 파일을 기준으로 각 REST API 설계 진행해야 함
```
ex)
전체 상품 조회 : GET /products
특정 상품 조회 : GET /products/{product_id} (Path Variable)
              GET /products?product_id=1 (Query Parameter)

장바구니 조회 : GET /v1/me/carts
장바구니 삭제 : DELETE /v1/me/carts/{cart_id}
장바구니 담기 : POST /v1/me/carts/products/{product_id}
```
* 웹 서치를 통해 REST API 설계 양식 참고

### 3. 싱글톤 패턴 수도 코드 작성
* 수도 코드가 맞는지 모르겠지만.. 인텔리제이로 멘토님께 싱글톤 패턴에 대한 코드를 공유드렸다.
```java
public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    private SingletonService() {
    }

    public static SingletonService getInstance() {
        return instance;
    }
}
```

### 4. 
