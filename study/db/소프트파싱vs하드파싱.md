# 소프트 파싱과 하드 파싱

소프트 파싱과 하드 파싱을 알아보기 전에 먼저 라이브러리 캐시가 무엇인지 알아보자.
### 라이브러리 캐시
- SGA(System Global Area)의 구성요소이며, 여기서 SGA는 서버 프로세스와 백그라운드 프로세스가 공통으로 액세스하는 데이터와 제어 구조를 캐싱하는 메모리 공간
- 라이브러리 캐시 위치는 SGA -> Shared Pool -> **Library Cache** 이다.

### **SQL 실행 과정**
1. syntax(문법, 구문) 에러 체크
2. semantic(권한, 존재여부) 체크
3. 라이브러리 캐시 영역으로 가서 저장되어 있는 정보가 있는지 확인
   

### 소프트 파싱
라이브러리 캐시에 동일한 프로시저를 찾으면 **곧바로 실행 단계로 넘어가는 것**

### 하드 파싱
라이브러리 캐시에 동일한 프로시저를 찾지 못해 **최적화, 로우 소스 생성 단계를 거쳐 실행하는 것**
