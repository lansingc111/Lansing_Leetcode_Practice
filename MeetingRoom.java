/** MeetingRoom 
1.排序：
时间复杂度 : O(nlogn) 。时间复杂度由排序决定。一旦排序完成，只需要 O(n)O(n) 的时间来判断交叠。
空间复杂度 : O(1)。没有使用额外空间。
2.暴力两两比较是否重叠: 
时间复杂度 : O(n^2)。我们对每两个会议都进行了比较。
空间复杂度 : O(1)。没有使用额外空间。
**/
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        //根据开始事件对intervals进行正序排序
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[]i1, int[]i2){
                return i1[0]-i2[0];
            }
        });
        
        for(int i =0; i<intervals.length-1; ++i){
            //判断后一个开始时间是否晚于等于当前的结束时间
            if(intervals[i][1] > intervals[i+1][0]){
                return false;
            }
        }
        return true; 
    }
}


class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        for(int i=0; i < intervals.length-1; i++){
            for(int j=i+1; j < intervals.length; j++){
                if(!ifOverlap(intervals[i], intervals[j])){
                    return false;
                }
            }
        }
        return true;
    }
    //写一个方法判断两两interval是否时间重叠
    //如果两这的min endTime不晚于max startTime, 则不 overlap
    public boolean ifOverlap(int[] i1, int[] i2){
        if(Math.min(i1[1],i2[1]) > Math.max(i1[0],i2[0])){
            return false;
        }
        return true;
    }
}