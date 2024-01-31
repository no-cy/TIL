# [TIL] 2024.01.29 🧑🏻‍🏫

### 1. 프로젝트 API 설계 리뷰 (장바구니, 주문)
**장바구니(cart)**
* 장바구니 테이블 구조 변경 리뷰 &rarr; 멘티가 구조 변경 진행
  * AS-IS : cart
  * TO-BE : cart, **caritem(장바구니 상품 목록)**
  * 설계 시나리오는 멤버가 하나의 장바구니만을 가지도록 설계하였지만, AS-IS는 장바구니 담기를 할 때마다 장바구니가 새로 생성됨 (cart_id가 1씩 증가함)
  * 각 멤버당 고유한 장바구니를 가질 수 있도록 하고, 그 장바구니에 담겨있는 상품들은 장바구니 상품 목록 테이블에 삽입될 수 있도록 ERD를 변경하였음
  ```SQL
  -- AS-IS
  
  create table cart (
    cart_id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint NOT NULL,
    product_id bigint NOT NULL,
    quantity int NOT NULL,
    added_at date NULL,
    PRIMARY KEY (cart_id),
    UNIQUE KEY (member_id, product_id)
  );

  -- TO-BE
  
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
* 
