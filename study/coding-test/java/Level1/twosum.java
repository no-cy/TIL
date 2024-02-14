class Solution {
    public int[] twoSum(int[] nums, int target) {
        // HashMap의 K, V 타입은 primitive type으로 할 수 없다.
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            // 보수(보충을 해주는 수)값을 구함.
            int complement = target - nums[i];
            
            // 보수 값을 Key로 가지고 있는지 확인.
            if(map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }

            // (K:값, V:인덱스) HashMap에 추가
            map.put(nums[i], i);
        }

        return null;
    }
}
