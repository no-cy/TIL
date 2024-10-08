/*
  -- 추가 테스트 케이스에서 통과하지 못해 코드 수정 필요
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
            char word = s.charAt(i);
        
            for (int j = i + 1; j < s.length(); j++) {
                System.out.println("word: " + word + ", compare: " + s.charAt(j));

                if (word != s.charAt(j)) {
                    continue;
                }

                System.out.println("i: " + i + ", j: " + j);
                String forwardString = s.substring(i, j + 1);
                String backwardString = new StringBuilder(forwardString).reverse().toString();

                if (forwardString.equals(backwardString)) {
                    if (forwardString.length() > currentAnswer.length()) {
                        currentAnswer = forwardString;
                        i += j;
                        break;
                    }
                }
                
                // System.out.println("forwardString: " + forwardString);    
                // System.out.println("backwardString: " + backwardString);
            }
        }

        if (currentAnswer.length() == 0) {
            currentAnswer = s.charAt(0) + "";
        }

        return currentAnswer;  
    }
}
