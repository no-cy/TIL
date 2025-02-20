# 슬라이딩 윈도우(Sliding Window)
### 1. 윈도우(Window)의 정의:
  - 윈도우는 배열이나 문자열에서 연속적인 범위를 나타냄
  - 윈도우의 시작과 끝을 나타내기 위해 2개의 포인터를 사용
    - **왼쪽 포인터(left)**: 윈도우의 시작 위치를 조정
    - **오른쪽 포인터(right)**: 윈도우의 끝 위치를 조정

### 2. 윈도우의 이동
  - 조건에 따라 윈도우를 오른쪽으로 **확장**하거나, **축소**하여 최적 값을 찾음
  - **확장**: 새로운 요소를 윈도우에 추가하기 위해 `right`를 오른쪽으로 이동
  - **축소**: 조건을 만족하지 않을 경우, `left`를 오른쪽으로 이동

### 3. 슬라이딩 윈도우와 2포인터의 차이
**1. 공통점**  
  - 두 개의 포인터를 사용해 문제를 해결한다는 점에서 유사.
    
**2. 차이점**
  - **2포인터:** 두 포인터가 서로 독립적으로 움직이며 특정 조건을 만족하는 두 위치를 찾는데 주로 사용.
    - 예: 두 수의 합이 특정 값을 만드는 문제.
  - **슬라이딩 윈도우:** 두 포인터가 연속된 구간을 유지하며 조건을 만족하는 구간을 찾거나 최적화 
