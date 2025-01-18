class Solution {
    public int maxArea(int[] height) {
        int maxHeightNum = 0, maxAmountOfWater = 0;

        for (int i = 0; i < height.length; i++) {
            if (height[i] < maxHeightNum) {
                continue;
            }

            maxHeightNum = height[i];
            int start = i;

            for (int end = start + 1; end < height.length; end++) {
                int amountOfWater = ((Math.min(height[start], height[end])) * (end - start));

                if (maxAmountOfWater < amountOfWater) {
                    maxAmountOfWater = amountOfWater;
                }
            }
        }

        return maxAmountOfWater;
    }
}
