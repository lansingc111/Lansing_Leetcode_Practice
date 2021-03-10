/**
与minMeetingRoom的不同是没有办法先排序，需要根据每次加入的日程进行新一次的开始时间正序排序，
所以不适合用priority queue的解法

正常的日程安排，如果要无时间交叉，即开始-结束-开始-结束，但一旦交叉，会变成开始-开始-结束-结束
用+1代表book的k次数，-1代表不冲突已对冲book
当有交叉安排是，累计的正数无法被负数抵消。k次预定即最大的正数累计值。


时间复杂度 O(n) 因为放入N个日程如tree map且遍历tree map.values()用来判断k次预定时需要遍历n次（每次book完都要遍历一次）
空间复杂度 O(n) treemap 使用的空间
**/

class MyCalendarThree {
    TreeMap<Integer, Integer> calendar;

    public MyCalendarThree() {
        calendar = new TreeMap();
    }
    
    //每次book时, calendar[start]++, calendar[end]--
    public int book(int start, int end) {
        calendar.put(start, calendar.getOrDefault(start,0)+1);
        calendar.put(end, calendar.getOrDefault(end,0)-1);

        int cumCount = 0, maxCount = 0;
        for(int flag : calendar.values()){
            cumCount += flag;
            maxCount = Math.max(cumCount, maxCount);
        }

        return maxCount;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */