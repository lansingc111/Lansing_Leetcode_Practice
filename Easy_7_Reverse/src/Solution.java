/**
 数学思想
 1./10循环，%10从尾部取数
 2.防止>最大23位整数2147483647或者小于23位最小整数-2147483648。  temp = 10*res+pop>=intmax 溢出-> res必定>=intmax/10
 所以第一次/10后判断, 若正数，res大于最大整数/10或者res=最大整数/10且pop>7;若负数，res小于最大整数/10或者res=最大整数/10且pop<-7,返回0
 不能直接整个判断，因为不能存64位，即不能存long且存整个

 时间复杂度：O(log(n)) <- O(log10(n))。x 中大约有log10(n)个数字
 空间复杂度：O(1)
 **/

class Solution {
    public int reverse(int x) {
        int res = 0;
        while(x != 0){
            int pop = x%10;
            x = x/10;
            if(res>Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && pop >7)){
                return 0;
            }
            if(res<Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE/10 && pop <-8)){
                return 0;
            }
            res = 10*res + pop;
        }
        return res;
    }
}