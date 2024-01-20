# [TIL] 2024.01.18 🧑🏻‍🏫

‼️ **멘토님 휴가 일정 : 2월 29일 ~ 3월 13일 (3월 4일, 11일 멘토링 연기)** ‼️

---
### 1. API 설계
✏️ **설계 보완 필요**
* 현재는 URI만 설계되어 있는데, **`http header`, `request`, `response`** 등 추가 설계 항목이 필요함
* 참고 자료는 **금융결제원, 네이버 커머스** 등 오픈 API 가이드를 참고하여 작성 진행 필요
* 보통 에러메세지는 규격화 되어 있어 가져다 쓰면 됨
* 모든 API들을 RESTful하기에는 한계가 있음  
> 예를 들어, 네이버 커머스 오픈 API **상품 주문 상세 조회 내역** API를 보자.

**API 명세서**
```
상품 주문 상세 조회

URI : POST /v1/pay-order/seller/product-orders/query
Authorization : Client-Credentials
Content-type : application/json

REQUEST BODY SCHEMA: application/json
required

String 배열
-| productOrderIds : Array of strings (arrayOfString.pay-order-seller)
```
**Example)**
```
content-type : application/json
body :
```
```json
{
 "productOrderIds": [
  "2022030221117100",
  "2009030221117100"
 ]
}
```
> * 여러 개의 주문 데이터를 한번에 가져올 수 있도록 벌크성을 가진 API임 (벌크 조회)
> * 보통 조회라고하면 GET을 사용하는데, GET에는 request body에 내용을 넣지 않음
> * GET은 쿼리 파라미터를 사용하여 어떤 데이터를 가져올 것인지 URI에 작성해주어야 하는데, 그렇게 되면 URL이 너무 길어져서 제약에 걸리게 됨
> * 그래서 어쩔 수 없이 request body에 넣기 위해서 POST method를 사용하여야 함
> * **POST URL을 확인했을 때 이게 조회인지 알 수 없어 URL 맨 끝에 action(query)을 입력해 준 것을 볼 수 있음**

* 실제 현업에서도 `RESTful`하게 다 설계하기에는 다소 어려움이 있음  
* 그렇지만 **최대한 커뮤니케이션 비용을 최대한 낮추기 위해 누가 봐도 이거는 포스트지만 조회 API구나 느낄 수 있게 이해할 수 있게 전달하는 것이 목표**가 되어야 함.

🧑🏻‍💼 **면접 관련** 
* 면접에서 API에 대해서 엄청 많이 물어보는 편임
  * RESTful api에 대해서와 RESTful한 api가 무엇인지
  * 실제 RESTful하게 설계할 수 없었을텐데 어떻게 해결해 나갔는지에 대한 답변
  * http Method에 대해서 설명
  * HTTP Response 상태 코드 100부터 500까지에 대해서 설명하고, 어느 상황일 때 상태 코드를 전달하는지
```
예를 들면, 100, 200, 300, 400, 500 번대의 각 특징을 말하고,
400번대는 보통 클라이언트가 잘못 요청한 경우, 500번대는 서버에서의 이슈로 인해 발생한 에러코드 등 이런식으로 답변을 하면 됨
유명한 애들인 401, 403, 404 등 이런 상태코드는 자세히 알아두면 좋음
```
---
### 2. 다음 멘토링까지 준비해야 할 리스트
1. 인증, 인가 진행 절차 스터디 (API gw(gateway))
2. 스프링 트랜잭션 REQUIRES_NEW, NESTED propagation(전파 속성) 실습
3. 우아한형제들 - 기술 블로그 [(응? 이게 왜 롤백되는거지? (click👆))](https://techblog.woowahan.com/2606/) 실습
4. API 설계 보완
