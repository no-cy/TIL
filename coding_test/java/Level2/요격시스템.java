import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, Comparator.comparingInt(o -> o[1]));     // 미사일 종료 지점을 기준으로 정렬

        int interceptCount = 0;
        int lastInterception = -1;

        for (int[] target : targets) {
            int start = target[0];
            int end = target[1];

            if (start + 0.1 > lastInterception) {                      // 한 개의 폭격 미사일 끝 지점보다 시작지점이 더 높은 좌표의 미사일이 있을 경우
                interceptCount++;                                      // 요격 미사일 추가
                lastInterception = end;                                // 끝 지점 업데이트
            }
        }

        answer = interceptCount;

        return answer;
    }
}
