class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        // 배열 오름차순 정렬
        Arrays.sort(nums);

        // nums.length에 -2를 한 이유는 left와 rigth 자리를 제외
        // 예: nums.length가 6일 경우, 
        //    인덱스는 point: 4, left: 5, right: 6
        //    point가 5와 6일 때는 while 조건에 맞지 않으므로 불필요한 반복을 함
        //    그러므로 -2를 명시함.
        for (int point = 0; point < nums.length - 2; point++) {

            // 중복된 값 건너뛰기
            if (point > 0 && nums[point] == nums[point - 1]) continue;

            // nums[point] 값이 양수 일 경우 0을 만들 수 없으므로 반복문 종료
            // nums가 오름차순으로 정렬되어 있으므로 nums[point]가 양수일 경우 모든 인덱스 값들은 0보다 큼
            if (nums[point] > 0) break;

            int left = point + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[point] + nums[left] + nums[right];

                // sum이 0보다 작으면 left 값 증가
                // 0에 가까워지기 위해서는 현재 left 값보다 커야 함.
                if (sum < 0) {  
                    left++;
                }
                // sum이 0보다 크면 rigth 값 증가
                // 0에 가까워지기 위해서는 현재 right 값보다 작아야 함. 
                else if (sum > 0) {
                    right--;
                } else {
                    // 현재 값을 List에 저장
                    // asList: 값들을 List로 변환
                    results.add(Arrays.asList(nums[point], nums[left], nums[right]));

                    // 중복된 left와 rigth 값 건너뛰기
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                }
            }
        }            
        
        return results;
    }
}
