// 카데인 알고리즘
// - 각 위치에서 끝나는 최대 부분 배열 합을 계산하는 것
// - 만약, 이전 위치의 최대 값과 현재 인덱스 값을 더했을 때, 현재 인덱스 값이 더 클 경우 부분 배열 시작 주소를 현재 인덱스로 지정
class Solution {
    public int maxSubArray(int[] nums) {
        int max = 0;
        int result = Integer.MIN_VALUE;
        
        if (nums == null | nums.length <= 0) return 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max + nums[i]) {
                max = nums[i];
            } else {
                max = max + nums[i];
            }

            if (result < max) {
                result = max;
            }
        }

        return result;
    }
}

// 분할 정복(Divide and Conquer)
// - 문제를 더 작은 문제로 나누고, 각각의 작은 문제를 해결한 다음, 이 해결책들을 합쳐 전체 문제의 해결책을 얻는 접근 방식
// - 분할 정복 방식으로 최대 부분 배열 합을 구하는 방법
//    1. 배열을 반으로 나눕니다.
//    2. 왼쪽 부분 배열에서 최대 부분 배열 합을 찾습니다.
//    3. 오른쪽 부분 배열에서 최대 부분 배열 합을 찾습니다.
//    4. 두 부분 배열의 경계에 걸쳐 있는 부분 배열에서 최대 합을 찾습니다.
//    5. 이 세 값 중 최대값이 전체 배열의 최대 부분 배열 합이 됩니다.

// .. 풀이 진행 중
