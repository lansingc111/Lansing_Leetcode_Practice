/**
要计算至少所需的会议室数量及统计在一个时间点不能共存的会议个数
套用canAttendMeeting的思路，1.先按开始时间排序 2.判断当前会议的开始时间是否晚于最小堆中最小的结束时间，若是，则可共存，队头出列。如否，则add到队列中。最后最小堆(priorityQueue)中的元素个数为最少所需的会议室个数
时间复杂度:O(nlog(n))  
    时间开销两部分：1. 排序: nlogn
    最小堆: 无论如何，我们都需要把每一个会议都写入堆，执行N次向堆插入的操作；在最坏的情况下，如果所有会议都相互不冲突，需要对堆进行n次查找及删除最小值操作。总的时间复杂度为 (NlogN)，因为查找并删除最小值操作只消耗 O(logN) 的时间。
空间复杂度:O(n)
    需要维护一个堆，最坏情况下所有会议都相互冲突，堆需要放入N个会议。额外需要N空间
**/
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(); //默认小顶堆
        Arrays.sort(intervals,(i,j)->(i[0]-j[0])); 
        //利用lambda表达式队intevals进行开始时间为基准的正序排序
        //min heap中存储结束时间
        queue.add(intervals[0][1]);

        for(int i=1; i<intervals.length; i++){
            if(intervals[i][0]>= queue.peek()){
                queue.poll();//如时间不冲突，则可共存，heap头出
            }
            queue.add(intervals[i][1]);//无论是否可以共存，当下的会议都要放入堆，跟之后的判断
        }
        return queue.size();
    }
}