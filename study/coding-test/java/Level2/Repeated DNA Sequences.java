class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        int strlen = s.length();
        int windowSize = 10;
        
        if (strlen < windowSize) return new ArrayList<>();

        Set<String> window = new HashSet<>();
        Set<String> repeateds = new HashSet<>();

        for (int i = 0; i <= strlen - windowSize; i++) {
            String subStr = s.substring(i, i + windowSize);

            if (!window.add(subStr)) repeateds.add(subStr);

        }
        
        return new ArrayList<>(repeateds);
    }
}
