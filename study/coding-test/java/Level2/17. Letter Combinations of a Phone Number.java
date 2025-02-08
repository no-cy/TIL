class Solution {
    private static final Map<Character, String> map = new HashMap<>();
    static {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<>();

        List<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        wordCombination(digits, sb, 0, words);

        return words;
    }

    void wordCombination(String digits, StringBuilder sb, int idx, List<String> words) {
        if (idx == digits.length()) {
            words.add(sb.toString());
            return;
        }

        char key = digits.charAt(idx);
        String value = map.get(key);

        for (char ch : value.toCharArray()) {
            sb.append(ch);
            wordCombination(digits, sb, idx + 1, words);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
