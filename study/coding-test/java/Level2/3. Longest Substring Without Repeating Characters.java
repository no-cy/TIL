//
// 슬라이딩 윈도우를 사용한 후의 코드
//
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            // s.charAt(right)의 중복 문자가 제거될 때까지 left의 범위를 증가시킴
            // 중복 문자가 없는 상태에서 최대 문자열의 길이를 구해야 하므로, 추가된 문자 순서로 삭제하여 중복 제거
            // 중복이 해결될 경우 다시 right 범위를 증가시킴
            while (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left));
                left++;
            }

            window.add(s.charAt(right));

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}


//
// 슬라이딩 윈도우를 사용하기 전 코드
//
class Solution {
    public int lengthOfLongestSubstring(String s) {
        String answer = new String();
        String extractString = new String();
        int left = 0;
        
        if (s.length() == 0 || s.length() == 1) return s.length();

        while (left < s.length() - 1)
        {
            int right = left + 1;

            // 연속된 문자일 경우 left 위치 조정
            if (left > 0 && s.charAt(left) == s.charAt(right)) {
                left++;
                continue;
            }

            while (right < s.length()) {
                String temp = s.substring(left, right);
                if (temp.contains(String.valueOf(s.charAt(right)))) {
                    break;
                }

                right++;
            }
            
            extractString = s.substring(left, right);
            if (extractString.length() > answer.length()) {
                answer = extractString;
            }

            left++;
        }

        return answer.length();
    }
}
