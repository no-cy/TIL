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
---
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
---
### 4. ACID, Isolation Level (Wrap-up)
* 준비한 내용으로 wrap-up을 잘 진행한 것 같다. (?)
* ACID에 원자성(Atomicity)에 대해 조금 설명이 부족했던게 있었다.
  * **추가 : 모두 커밋되거나 모두 롤백되어야 한다는 특성이다.**
* ACID 정리 : [ACID](../DB/acid.md)
* Isolation Level 정리 : [Isolation Level](../isolation_level.md)
---
### 5. git flow Q&A (실제로 이렇게 대화하지 않았다.. 참고 부탁 🙏)
```
# Q:멘티, A:멘토님

Q. feature 생성 시 Gitlab 이슈 번호에 맞게 생성하는지?
A. 맞다.
Gitlab으로 이슈를 관리하게 될 경우 이슈 번호에 맞게 feauter도 생성해 주는 것이 좋다.
이슈 번호만으로도 커밋 히스토리를 tracking(추적) 할 수 있어 좋다.
JIRA를 사용할 경우 티켓의 네임으로 feature를 생성하기도 한다.

Q. 자사는 이슈를 레드마인으로 관리한다. 레드마인으로 이슈를 등록하고 또 Gitlab으로 이슈 등록을 해야하는데 효율적으로 변경할 방법이 없는지?
A. 굳이 두 개로 관리해야되나 싶다. 일이 하나 더 늘어나는 것 같다.
Gitlab에 엔지니어들을 초대해서 이슈 등록에 대한 권한만 부여해서 Gitlab으로 이슈 관리하는 방법을 생각해 볼 수 있다.
기존과 동일하게 레드마인으로 관리한다라고하면 Gitlab 이슈 등록을 할 때에 레드마인 링크와 같이 작성하여 등록하는 방법도 있겠다.

Q. release 브랜치는 리더급이 관리하는지?
A. 그렇지않다.
우리 회사는 각 팀원들끼리 돌아가면서 relase 브랜치를 생성하고 관리한다.

Q. squash and merge 기능을 로컬 feature 브랜치에서 사용하는지?
A. 꼭 그렇지만은 않다.
팀끼리 논의해서 로컬 feature 브랜치에서 squash and merge 후 origin에 push를 할지,
아니면 upstream/feature를 develop에 merge할 때에 squash and merge를 할지에 대한 방향은 팀에서 정해야 한다.
squash and merge 기능 커밋 히스토리를 깔끔하게 정리되므로 사용하는 것을 추천한다.
suqash merge 기능을 어디 부분에서 사용할지만 결정하면 되겠다.
```
---
### 6. 다음 멘토링 스터디 랩업에 대한 준비
* 빈 라이프사이클
* 빈 스코프
* 필터를 스프링 빈에 등록할 수 있는 이유와 스프링 기능을 사용 가능하게 된 이유
* 스프링 트랜잭션 전파속성(Spring Transaction Propagation)
* **스크립트 참고 후 추가 사항 있으면 작성 필요**
