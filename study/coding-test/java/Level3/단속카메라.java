import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        int camera = 0;
        int lastInterception = Integer.MIN_VALUE;                    // routes 인덱스의 최소 값이 "-30000" 이므로 int 최소 값으로 초기화
        
        Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));     // routes[][i] 기준으로 오름차순으로 정렬
        
        for(int[] route : routes) {
            int in = route[0];
            int out = route[1];
            
            if (in > lastInterception) {                              // 끝 지점보다 시작 지점이 클 경우
                camera++;                                             // 카메라 추가
                lastInterception = out;                               // 끝 지점 업데이트
            }
        }
        
        answer = camera;
        
        return camera;
    }
}
