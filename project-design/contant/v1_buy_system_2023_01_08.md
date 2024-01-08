# 구매 시스템 및 결제 시스템 설계

**구체화 필요** 
API 영역
1. URL
2. method 
3. request parameter 
   - 쿼리 파라미터 (URI?username=nocy)
   - Body (Post 방식에서 body {username : nocy}
   - path variable **(URI/{nocy})**
4. response data

DB 영역 (MySQL)

필수 : 12개 
**회원** (user) 			
* 회원가입 **(필수)** - POST
* 로그인 **(필수)** - POST
* 아이디 / 비밀번호 찾기 (Optional)
* 회원정보 조회 **(필수)** - GET
* 회원정보 변경 (비밀번호, 주소 등) **(필수)** - PUT
* 회원탈퇴 **(필수)** - DELETE

**상품** (product)
* 상품 리스트 조회 **(필수)** - GET
* 검색 **(필수)** - GET 

**장바구니** (cart)
* 장바구니 담기 **(필수)** - POST
* 장바구니 리스트 **(필수)**
  * 조회 - GET
  * 삭제 - DELETE

**주문** (order)
* 주문하기 **(필수)** - POST
* 주문 내역 조회 **(필수)** - GET
* 다양한 결제 방법 (계좌이체, 신용카드, 온라인 뱅킹 등)
* 취소 **(필수)** - PUT, PATCH

**환불 / 교환**
* 교환 및 반품 처리

