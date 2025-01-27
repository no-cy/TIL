class Solution {
    public int lengthOfLongestSubstring(String s) {
        String answer = new String();
        String extractString = new String();
        int left = 0;

        // abcabcbb
        while (left < s.length())
        {
            int right = left + 1;

            // 연속된 문자일 경우 left 위치 조정
            if (left > 0 && s.charAt(left) == s.charAt(right)) {
                left++;
                continue;
            }

            // qwwkew
            while (right < s.length()) {
                // rigth가 연속된 문자일 경우 반복문 중지
                if (s.charAt(right) == s.charAt(right - 1)) break;
                
                String temp = s.substring(left, right);
                if (temp.contains(String.valueOf(s.charAt(right)))) break;

                if (answer.equals(temp)) break;
                System.out.println("left: " + left + " right: " + right);
                System.out.println("left: " + s.charAt(left) + " right: " + s.charAt(right));
                right++;
            } 

            extractString = s.substring(left, right);
            if (extractString.length() > answer.length()) {
                answer = extractString;
                System.out.println("answer: " + answer);
            }

            left = right;
        }

        return answer.length();
    }
}
