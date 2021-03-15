/**
 动态规划 + 滚动数组优化空间复杂度O(1)

 每次状态转变只有两种情况:爬一步或者两步。 爬一步继承于dp[n-1]; 爬两步继承于dp[n-2]
 爬0级:num=0
 爬1级:num =1  1
 爬2级:num =2   1+1爬完一级再爬1步, 0+1, 爬完0级再爬2级
 爬3级:num =2+1 1+2 爬完二级再爬1步dp[n-1], 2+1, 爬完一级再爬2步dp[n-2]

 如果把dp数组每个位置都存一个步数，空间复杂度是O(n), 由于每次只需要 dp[n-1], df[n-2],产生dp[n],相当于只需要三个变量，
 所以可以用滚动数组(斐波那契数列)优化空间复杂度至O(1)。 每次计算完dp[n], 向左滚动更新dp[n-1]和dp[n]
 cur = pre + beforePre
 beforePre = pre;
 pre = cur;

 状态传递公式 dp[n] = dp[n-1] + dp[n-2] 边界条件：设置dp[0]=1(虽然没有爬，但也算一种方案，即不动0) dp[1]=1 -> dp[2] = 2

 动态规划优于递归的是，避免每一步都重复的去算之前的部数比如爬4层和爬3层都需要算climbStairs(2), 能把时间复杂度从O(n^2)降到O(n).
 此题递归会oom
 因为算一次就存了，可以直接去取用O(1)

 时间复杂度: O(n) 需要从头遍历每个位置，传递步数状态
 空间复杂度: O(n) 滚动数组只需要存三个变量
 **/


class Solution0 {
    public int climbStairs(int n) {
        int pre = 1;  //n-1 (1)
        int beforePre = 1; //n-2 (0)


        for(int i=2; i<=n; i++){
            int curr = pre + beforePre;
            beforePre = pre;  //迭代后成 n-2成n-1
            pre = curr;//迭代后成 n-1成n 所以返回的最终结果应该是pre
        }

        return pre;
    }
}

