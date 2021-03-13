import java.util.ArrayList;
import java.util.List;

/**
 分情况法：
 1. 画图模拟
 4             8
 1 - 2 | 3 - 5 | 6 -7 | 8 - 10 | 12 - 16 |
 (1) 当前区间的右端<待插入区间的左端 -> 不会重叠，直接加入resList
 (2) 当前区间的左端>待插入区间的右边 -> 不会重叠，直接加入resList
 (3) 中间的重叠并集区间是Min(left）Max(right)

 时间复杂度：O(n) n为区间个数，需要遍历一遍
 空间复杂度: O(1) 除了存储结果的arraylist和数组,不需要额外空间
 **/

class Solution0 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> resList = new ArrayList<int[]>();

        if(intervals.length == 0){
            if(newInterval.length !=0){
                resList.add(newInterval);
                return resList.toArray(new int[resList.size()][2]);
            }else{
                return new int[0][2];
            }
        }

        int newLeft = newInterval[0];
        int newRight = newInterval[1];
        boolean ifInserted = false;

        for(int i=0; i<intervals.length; i++){
            //在插入区间的左侧无交集
            if(intervals[i][1]<newInterval[0]){
                resList.add(intervals[i]);
                //与插入区间有交集，合并交集
            }else if(intervals[i][1]>=newInterval[0] && intervals[i][0]<=newInterval[1]){
                newLeft = Math.min(newLeft,intervals[i][0]);
                newRight = Math.max(newRight,intervals[i][1]);
                //在插入区间的右侧无交集
            }else if(intervals[i][0]>newInterval[1]){
                //如果插入区间还未插入，则先插入插入区间并集，再插入大区间
                if(ifInserted == false){
                    resList.add(new int[]{newLeft,newRight});
                    ifInserted=true;
                }
                resList.add(intervals[i]);
            }
        }
        //如果没有大区间，则在最后放入插入区间并集
        if(ifInserted == false){
            resList.add(new int[]{newLeft,newRight});
            ifInserted=true;
        }

        return resList.toArray(new int[resList.size()][2]);
    }
}


