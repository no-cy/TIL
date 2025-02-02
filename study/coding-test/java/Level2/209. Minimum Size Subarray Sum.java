class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int sumVal = 0, left = 0;
        int minLength = Integer.MAX_VALUE;
       
        for (int rigth = 0; rigth < nums.length; rigth++) {

            sumVal += nums[rigth];

            while (sumVal >= target) {
                minLength = Math.min(minLength, rigth - left + 1);
                sumVal -= nums[left];
                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) return 0;

        return minLength;
    }
}
