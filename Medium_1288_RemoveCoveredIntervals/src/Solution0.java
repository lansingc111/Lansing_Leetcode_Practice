import java.util.Arrays;
import java.util.Comparator;

/**
 贪心思想


 不被覆盖的区间一定是先左界正序后右界倒序排序后右界最大的。(被覆盖的interval是左界大右界小))))
 如果只按左界排序，如果出现相同左起始点, 比如 [2,8] [2,10] 这种就没法把前面出现的[2,8]找出来删除了
 1.按左界正序排序, 维护一个maxRight, 初始化为intervals[0][1]
 2.遍历比较当前interval与maxRight比较，如果right<=maxRight, toRemoveCnt++
 3. toKeepCnt = intervals.length-toRemoveCnt

 时间复杂度:O(nlog(n)) n为数组中interval的个数, 排序log(nlog(n))
 空间复杂度:O(1) 额外空间是常数空间

 初始思路: 修改路径要加上右界倒序排序
 **/
class Solution0 {
    public int removeCoveredIntervals(int[][] intervals) {
        if(intervals.length<2 || intervals==null){
            return intervals.length;
        }
        //先左界正序后右界倒序排序
        Arrays.sort(intervals,(i,j)->(i[0] == j[0] ? j[1]-i[1] : i[0]-j[0]));
//        Arrays.sort(intervals,new Comparator<int[]>(){
//            public int compare(int[] i1, int[] i2){
//                if(i1[0] == i2[0]){
//                    //右界倒序
//                    return i2[1] - i1[1];
//                }
//                return i1[0] - i2[0];
//            }
//        });

        int maxRight = intervals[0][1];
        int toRemoveCnt = 0;

        for(int i=1; i<intervals.length; i++){
            if(intervals[i][1]<=maxRight){
                toRemoveCnt++;
            }else{
                maxRight = intervals[i][1];
            }

        }
        return intervals.length-toRemoveCnt;
    }
}


// [[1,2],[1,4],[3,4]]
// [1,4] [1,2] [3,4]