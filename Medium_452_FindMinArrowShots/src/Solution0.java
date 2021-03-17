import java.util.Arrays;

/**
 贪心

 每次都选出可以戳破(cover)最多气球(区间)的位置
 0. **重要:用右界排序更好，这样需要考虑interval[0]是否>maxCommonEnd,如果大就cnt++,更新maxCommonEnd
 如果不大，不管它左界线在哪儿，都不用更新maxCommonEnd
 //1. 先按左界线排序 **注意，由于负数的存在，排序的时候正负数同时比较且正数在前时，会形成正数在前的倒序，要特殊考虑
 //2. 当前区间与最大位置不重合时(interval[0]>maxCommonEnd)，说明需要开一个新的弓箭了，cnt++, 且更新最大位置为interval[1]
 //3. 当前区间与最大位置重合时，每次的最优选就是更小的右界线

 特殊case： [[-2147483646,-2147483645],[2147483646,2147483647]]
 sort时不能直接i[0]-j[0]会因为差值过大溢出，差值默认为4而导致排序顺序错误
 所以要用Integer.compare(a, b)或者< ? -1:1

 时间复杂度:O(n) 遍历数组 用右界排序速度更快，更新maxCommonEnd的次数更少
 空间复杂度:O(1) 常量额外空间
 **/
class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points.length ==0 || points == null){
            return 0;
        }
        Arrays.sort(points,(i, j)->(i[1]<j[1]?-1:1));//用右界线正序排序更新maxCommonEnd次数更少
        //不能使用a-b来比较，差值过大会溢出，应该使用Integer.compare(a,b)比较或者直接根据大小写死返回-1：1
        //(i,j)->(i[0]+j[0]==0 ? Math.abs(i[1])-Math.abs(j[1]) : i[0]-j[0]) 绝对值差值也不会溢出
        int maxCommonEnd = points[0][1]; //注意要去排序后的第一个元素
        int cnt = 1; //当points长度>=1时，最小弓箭数是1


        for(int i=1; i<points.length; i++){
            if(points[i][0] > maxCommonEnd){
                maxCommonEnd = Math.max(points[i][1], maxCommonEnd);
                cnt++;
            }
        }
        return cnt;
    }
}


