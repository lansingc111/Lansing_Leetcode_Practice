/**
 动态规划 + 滚动数组 / 贪心思想
 1. 状态：到i位置为止最大的子序和 dp[i]
 状态传递方程: 求maxSum：dp[i] = max(dp[i-1], f(i)) 或者把f(i)求cumSum的结果存成数组,取最大值，就不用每个i套一层maxSum比较。
 求cumSum: f(i) = max(f(i+1)+nums[i], nums[i])
 对于i来说,如果当前开始累计的cumSum已经<0了，说明前面这一段没有增益，直接舍弃，从i开始累加，并取之前的maxSum dp[i-1]与当前cumSum的最大值为dp[i]

 **这里为了优化空间复杂度，所以不存f(i)数组，而是用滚动数组的思想，因为其实只需要cumSum来维护当前不处理i之前的累加和。
 因为没有数组了，每一步都比较判断取max(maxSum,cumSum),就成为每一步取最优解，就是贪心思想。

 思路:对于i来说，如果之前的累积和已经小于<0了，就舍弃前面那些子序，cumSum从当前值开始累计,不影响maxSum。
 要注意，如果前面的累计加总>0,但加入当前值变小了，仍然累加cumSum，但不更新maxSum

 如果 cumSum > 0，则说明 cumSum 对结果有增益效果，则 sum 保留并加上当前遍历数字
 如果 cumSum <= 0，则说明 cumSum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
 每次比较 cumSum 和 ans的大小，将最大值置为ans，遍历结束返回结果

 时间复杂度：O(n) 遍历数组
 空间复杂度:O(1) 只存了常量

 **/

class Solution0 {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];//应初始为nums[0],不能初始为0，因为可能只有负数且在初始位，比如[-1,-2,-3]这样就会直接跳过，输出0,是不对的
        int cumSum = 0;

        for(int i=0; i< nums.length; i++){
            cumSum = Math.max(cumSum+nums[i],nums[i]);
            maxSum = Math.max(maxSum,cumSum);
        }

        return maxSum;
    }
}

