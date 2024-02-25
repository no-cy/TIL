# MySQL InnoDB 스토리지 엔진 Lock 종류

### 1. 레코드 락(Record Lock)
- **정의 :** 
  - 데이터베이스 테이블의 특정 행에 설정되는 락
  - 다른 트랜잭션이 해당 행을 수정하지 못하도록 방지
- **용도 :**
  - 주로 데이터를 변경하거나 조회할 때 사용
  - 특정 행을 업데이트하거나 삭제하기 전에 해당 행에 레코드 락을 설정하여 다른 트랜잭션이 동시에 같은 행을 변경하는 것을 방지

### 2. 갭 락(Gap Lock)
- **정의 :**
  - 테이블의 레코드 사이의 공간, 즉 '갭'에 대한 락
  - 특정 범위에 새로운 행이 삽입되는 것을 방지
  - 트랜잭션이 동일한 갭에 대해 동시에 변경을 시도하는 것을 막는다
- **용도 :**
  - 주로 트랜잭션이 범위 쿼리를 수행할 때 사용되며, 특정 범위 내에서 팬텀 리드를 방지하는데 도움 됨
  > **팬텀 리드(Phantom Reads)** 는 한 트랜잭션 내에서 동일한 쿼리를 두 번 실행 했을 때, 첫 번째 쿼리 이후에 새로 삽입된 행 때문에 두 번째 쿼리 결과가 달라지는 현상

### 3. 넥스트-키 락(Next-Key Lock)
- **정의 :**
  - 레코드 락과 갭 락을 결합한 형태의 락
  - 특정 행과 그 행 바로 앞의 갭에 대한 락을 동시에 설정
- **용도 :**
  - InnoDB에서 트랜잭션의 격리 수준이 '반복 가능한 읽기(Repeatable Read)' 일 때 기본적으로 샤용되고, 팬텀 리드를 방지한다
  - 트랜잭션이 범위 쿼리를 수행할 때 해당 범위 내의 모든 행과 갭을 락으로 보호한다
  - 쿼리가 실행되는 동안 새로운 행의 삽입이나 기존 행의 변경을 방지한다

### 4. 