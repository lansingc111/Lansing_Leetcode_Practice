import java.util.ArrayList;
import java.util.List;

/**
 * 双指针
 * 时间复杂度: O(m+n)；考虑到两个list首尾相交，交替成为下一个比较基准，所以遍历次数为m+n？
 * 空间复杂度: O(m+n): 答案中区间数量的上限 (上线为m+n-1，邻近都相交）
 */
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> resList = new ArrayList<>();
        //两个list已排序且list内部不相交

        //双指针，两个list各一个
        int i =0, j=0;

        //只有两个list都没遍历完，才有intersection可能性
        while(i < firstList.length && j < secondList.length){
            //存储maxStart和minEnd以便之后判断是否有交集
            int maxStart = Math.max(firstList[i][0],secondList[j][0]);
            int minEnd = Math.min(firstList[i][1],secondList[j][1]);

            //无论是否有交集，都需要判断出更晚结束的那个来继续跟另一个list的后一个区间比较，后移较早结束的list指针
            //若两个结束时间一致，两个指针同时后移
            if(firstList[i][1] > secondList[j][1]){
                j++;
            }else if(secondList[j][1] > firstList[i][1]){
                i++;
            }else{
                i++;
                j++;
            }
            //若有交集，输出交集区间，可以保证[x,x]也会被包括
            if(maxStart <= minEnd){
                resList.add(new int[]{maxStart, minEnd});
            }
        }
        return resList.toArray(new int[resList.size()][2]);
    }
}
