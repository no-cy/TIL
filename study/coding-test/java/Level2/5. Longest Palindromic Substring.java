/*
- 중심 확장 알고리즘 사용
- 중심이 되는 문자에서 왼쪽과 오른쪽이 대칭 구조이여야기 때문에 중심 확장 알고리즘을 사용하였음
*/
class Solution {
    private String currentAnswer = "";

    public String longestPalindrome(String s) {
        // 문자열 검사를 하였을 때, 제일 긴 회문을 찾아야 함.
        // 회문: 앞에서 읽든 뒤에서 읽든 같은 문자열을 말함.
        if (s.length() == 0) {
            return null;
        }
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 홀수 중심 찾기: bab 인 경우
            expandAroundCenter(s, i, i);
            // 짝수 중심 찾기: baab 인 경우도 있을 수 있으니 짝수도 검사해주어야 함
            expandAroundCenter(s, i, i + 1);
        }

        return currentAnswer;  
    }

    // 중심을 잡고 왼쪽과 오른쪽으로 확장하여 문자열이 회문(대칭)인지 확인
    public void expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            String extractString = s.substring(left, right + 1);
            if (extractString.length() > currentAnswer.length()) {
                currentAnswer = extractString;
            }

            left--;
            right++;
        }
    }
}
