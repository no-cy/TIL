// 
// chatgpt 개선 코드
//
// 차이점: 
//   1. 결과 값 반환용 변수를 사용하지 않음
//      - map에 Value를 List<String>으로 선언함
//      - map.values() 메서드를 사용하여 map의 전체 value를 반환함 
//   2. Set 대신 map.computeIfAbsent() 메서드를 사용하여 key 중복 검사 
//      - map에 key가 존재하면 ArrayList 객체를 생성하지 않고 str을 추가
//      - map에 key가 존재하지 않으면 ArrayList 객체 생성 후 str을 추가
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray); // 정렬된 문자열 생성

            // key가 없으면 새 리스트 생성 후 추가
            map.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());
    }
}


//
// 수정 전 코드
//
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        
        int idx = 0;
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        List<List<String>> anagrams = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            char[] charArray = strs[i].toCharArray();
            
            Arrays.sort(charArray);
            
            String sortedStr = new String(charArray);
            
            if (!set.add(sortedStr)) {
                int listIdx = map.get(sortedStr);
                anagrams.get(listIdx).add(strs[i]);
                
            } else {
                anagrams.add(new ArrayList<>());
                anagrams.get(idx).add(strs[i]);
                map.put(sortedStr, idx);
                idx++;
            }
        }

        return anagrams;
    }
}
