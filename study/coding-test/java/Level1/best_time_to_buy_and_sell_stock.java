class Solution {
    public int maxProfit(int[] prices) {
        int min = 0;
        int profit = 0;
        
        if (prices == null || prices.length == 0) return 0;

        min = prices[0];
        for(int i = 1; i < prices.length; i++) {
            if (min >= prices[i]) {
                min = prices[i];
            } else if (prices[i] - min > profit) {
                profit = prices[i] - min;
            }
        }
        return profit;
    }
}
