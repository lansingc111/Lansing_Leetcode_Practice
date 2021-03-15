import java.util.Arrays;
import java.util.Collections;

/**
 最朴素dp法 数组
 更新每个i对应的当前累计cumSum，输出数组max

 时间复杂度：O(n) 遍历数组
 空间复杂度:O(1) 只存了常量

 **/

class Solution1 {
    public int maxSubArray(int[] nums) {
        Integer[] cumSum = new Integer[nums.length]; //因为list才有max方法，所以存数组需要存成Integer[]
        cumSum[0] = nums[0]; //初始化dp[0]为nums[0]而非0,这样即使nums中只有负数，也会输出最小的那个负数，而不是0


        for(int i=1; i< nums.length; i++){
            cumSum[i] = Math.max(cumSum[i-1]+nums[i], nums[i]);
        }

        return Collections.max(Arrays.asList(cumSum));//因为array要先new一个arrayList,会慢
        //其次基本数据类型的数组使用asList()，出错(输出的是一个引用),遍历会很麻烦
    }

}

