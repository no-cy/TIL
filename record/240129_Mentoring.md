# [TIL] 2024.01.29 🧑🏻‍🏫

### 1. 프로젝트 API 설계 리뷰 (장바구니, 주문)
**장바구니(cart)**
* 장바구니 테이블 구조 변경 리뷰 &rarr; 멘티가 구조 변경 리뷰 진행
  * AS-IS : cart
  * TO-BE : cart, **caritem(장바구니 상품 목록)**
  * 설계 시나리오는 멤버가 하나의 장바구니만을 가지도록 설계하였지만, AS-IS는 장바구니 담기를 할 때마다 장바구니가 새로 생성됨 (cart_id가 1씩 증가함)
  * 각 멤버당 고유한 장바구니를 가질 수 있도록 하고, 그 장바구니에 담겨있는 상품들은 장바구니 상품 목록 테이블에 삽입될 수 있도록 ERD를 변경하였음
  * 장바구니 생성은 멤버 회원가입 시 생성되도록 트리거를 사용할 예정
  <br>
  
  ```SQL
  --
  -- AS-IS
  --
  
  create table cart (
    cart_id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint NOT NULL,
    product_id bigint NOT NULL,
    quantity int NOT NULL,
    added_at date NULL,
    PRIMARY KEY (cart_id),
    UNIQUE KEY (member_id, product_id)
  );

  --
  -- TO-BE
  --
  
  create table cart (
    cart_id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint NOT NULL,
    added_at date NULL,
    PRIMARY KEY (cart_id),
    UNIQUE KEY (member_id)
  );

  create table cartitem (
    cart_item_id bigint NOT NULL AUTO_INCREMENT,
    cart_id bigint NOT NULL,
    product_id bigint NOT NULL,
    quantity int NOT NULL,
    added_at date NULL,
    PRIMARY KEY (cart_item_id),
    UNIQUE KEY (cart_id, product_id)
  );
  ```
* TO-BE로 변경하였을 때, 특별한 장점이 보이는 부분이 없어 멘토님께서 직장 동료 중 장바구니 팀에 근무하시는 분께 의견을 물어 피드백을 주신다고 하셨음
* 장바구니 조회 API 리소스 검토 필요
    * 예를 들어, cartitem에 product_id를 위주로 사용한다라고 하면, API 리소스를 /v1/me/cart/products로 수정할 수 있음
    * 위와 같이 어떤 컬럼을 활용하냐에 따라서 API 리소스를 수정할 필요가 있음
 
**주문(order)**
* 주문하기 API 리소스 변경
    * /v1/orders &rarr; /v1/me/orders
* 주문하기를 수행하면 cartitem(장바구니 상품 목록) 테이블에 있는 데이터를 order(주문) 테이블에 데이터가 삽입되도록 구현 진행
    * **장바구니에 있는 모든 상품들을 주문 처리하도록 할 것인지, 장바구니에 있는 상품들을 선택하여 부분 주문할 수 있도록 할 것인지 설계 검토 필요**
* 주문취소 수행 시 전체 주문이 취소되도록 하고, 이에 따라 결제도 환불이 진행될 수 있도록 구현
---

### 2. 다음 멘토링까지 진행해야 할 리스트
* 장바구니, 주문 API 설계 검토
    * cart(장바구니)에 담긴 데이터들이 order(주문) 테이블로 이동하였을 때, cart_id가 변경없이 유지되어도 되는지 확인 필요
    * 예를 들어, 장바구니에 있는 상품들을 주문한다고 하였을 때마다 새로운 cart_id를 생성한다고 가정해 보자
    * 이럴 경우 멤버가 여러 개의 장바구니를 가질 수 있으므로 각 장바구니가 현재 어떠한 상태인지 cart(장바구니) 테이블에 status 컬럼을 추가해 현재 장바구니가 어떠한 상태인지(주문 중, 주문 완료 등) 표시될 수 있도록 컬럼 추가 여부를 판단하여야 함
* 주문하기를 하였을 때 결제 테이블을 어떻게 활용할 것인지에 대한 검토
> 검토해야 할 두 사항(장바구니, 주문)들 모두 시나리오를 작성하면서 검토
* f-lab 스크립트 00:19:45 검토
    * 의미가 이해가 되지 않을 경우 멘토님께 문의해보자
