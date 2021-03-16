import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 贪心算法

 题目要求算最小删除的区间数量，即留下最多区间数量->也就是说如果是重叠的区间，留下的区间尽量长度短一些，这样留下剩余空间多，之后重叠的可能性低
 也就是说在局部(每一次区间重叠时)选一个长度最短的。

 同时，如果我们限定一端的顺序，只要比较另一段的大小。
 若按start排序，则固定左边，从左向右遍历，每次需要留下一个minRight。
 若按end排序，则固定右边，从右向左遍历，每次需要留下一个maxLeft。
 每次重叠，interval[0]<minRight, 说明需要剔除一次，需要留下的是那个right更小的，所以直接更新minRight。
 若不重叠, 直接更新minRight

 贪心思想：局部最优：优先选右边界小的区间，所以从左向右遍历，留给下一个区间的空间大一些，从而尽量避免交叉。
 全局最优：留下非交叉最多的区间

 时间复杂度：O(nlog(n)) 快拍排序O(nlog(N)) 遍历O(n)
 空间复杂度:O(1)

 最初思路：
 排序 按interval的start,再按end排序
 2. 单调递增栈
 a. 如果interval的start大于等于当前栈顶的end，把interval的end压入栈, nonOverlapCnt++(因为题目说明interval[1]大于interval[0])

 3. 需要溢出区间的最小数量即 intervals.length - nonOverlapCnt
 思维正确方向：1.考虑了排序
 2.以及以留下的数量来倒推需要剔除的数量。但只考虑了start一致的要留结束更早的，忘了即使start不一样但重叠的仍需要留下right更小的
 思维漏洞：1.没有好好思考怎样叫最小的删除数量。 2，因为其实只需要栈顶, 所以用栈维护其实浪费空间了，用一个常量minRight就够了
 **/
class Solution0 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length==0 || intervals==null){
            return 0;
        }
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] i1, int[]i2){
                if(i1[0]==i2[0]){
                    return i1[1]-i2[1];
                }else{
                    return i1[0]-i2[0];
                }
            }
        });


        Stack<Integer> nonOverlapStack = new Stack<>();
        int toRemoveCnt = 0;
        for(int i=0; i<intervals.length; i++){
            if(!nonOverlapStack.isEmpty() && intervals[i][0] < nonOverlapStack.peek()){
                toRemoveCnt++;
                if(intervals[i][1]<nonOverlapStack.peek()){
                    nonOverlapStack.pop();
                    nonOverlapStack.push(intervals[i][1]);
                }
            }else{
                nonOverlapStack.push(intervals[i][1]);
            }

        }
        return toRemoveCnt;
    }
}

