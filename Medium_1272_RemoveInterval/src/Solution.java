/**
 模拟 分情况
 分三种情况：(类似 insertIntersection)
 1. 待删除区间左侧不重叠：interval[1] <= toBeRemoved[0], 保留，直接加入resList
 2. 待删除区间有重合的部分:
 a. toBeRemoved区间大：minLeft ==toBeRemoved[0] && maxRight = toBeRemoved[1]
 continue; 不加入即删除
 b. else toBeRemoved区间小：
 如果左端重叠，产生右侧插入区间 -> 当待删除区间左点右移,即toBeRemoved[0]>intervals[i][0] 才产生左侧区间 intervals[i][0],toBeRemoved[0]
 如果有段重叠，产生左侧插入区间 -> 当待删除区间右点左移,即toBeRemoved[1]<intervals[i][1] 才产生右侧区间 toBeRemoved[1],intervals[i][1]
 resList加入两个区间 [minLeft,toBeRemoved[0]],[toBeRemoved[1],maxRight]
 3. 待删除区间右侧不重叠: interval[0] >= toBeRemoved[1], 保留，直接加入resList (先判断ifInserted，如果false，先插入)

 考虑到1.3处理方式一样可以合并。
 与合并插入不同，由于是删除，所以不用单独维护一个变量来看是否删除后产生的区间是否插入。因为如果有区间交叉一定已经先于右侧不重叠加入了。

 时间复杂度：O(n)
 空间复杂度: O(1) 只存了常量,不考虑存储结果的数组/ArrayList
 **/

class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> resList = new ArrayList<>();
        if(intervals.length == 0 || intervals == null){
            return resList;
        }

        for(int i=0; i<intervals.length; i++){
            //左侧或右侧不重叠区间直接加入
            if(intervals[i][1] <= toBeRemoved[0] || intervals[i][0] >= toBeRemoved[1]){
                resList.add(Arrays.asList(intervals[i][0],intervals[i][1]));
            }else{
                //左侧remainder为[x,toL] 右侧reminder为[toR,y]
                if(intervals[i][0] < toBeRemoved[0]){
                    resList.add(Arrays.asList(intervals[i][0],toBeRemoved[0])); //new ArrayList(){{add(intervals[i][0]);add(toBeRemoved[0]);}}
                }
                if(intervals[i][1] > toBeRemoved[1]){
                    resList.add(Arrays.asList(toBeRemoved[1],intervals[i][1]));
                }
                //如果左右remainder由一个intervals[i]造成，即这个interval区间覆盖toBeRemoved
                //否则左右reminder由两个interval产生，中间的即区间小于toBeRemoved，不会产生reminder
            }
        }

        return resList;
    }
}