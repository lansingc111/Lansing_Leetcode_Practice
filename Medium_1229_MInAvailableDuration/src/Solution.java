import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 双指针 + 两个数组左界正序排序
 因为要最早的间隔用左界排
 算overlap interval的左边和右边， 若overlapStart<=overlapEnd说明确实重合, 计算gap是否>=duration，若是，返回；若否，则++右界线小的那一组继续比较，直到其中一组到尾部
 overlapStart = Math.max(overlapStart, Math.max(slots1[0],slots2[0]))
 overlapEnd = Math.min(overlapEnd, Math.end(slots1[1],slots2[1]))


 时间复杂度: O(nlog(n)) 最坏O(m+n) 排序log(n)
 空间复杂度: O(1) 常量额外空间 （排除输出的arraylist）
 **/

class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<Integer> resInterval = new ArrayList<>();
        if(slots1.length == 0 || slots1 == null || slots2.length == 0 || slots2 == null || duration < 0){
            return resInterval;
        }
        //左界正序排序
        Arrays.sort(slots1, (i,j)->(i[0]-j[0]));
        Arrays.sort(slots2, (i, j)->(i[0]-j[0]));

        int i = 0, j = 0;
        int overlapLeft = 0;
        int overlapRight = 0;

        while (i < slots1.length && j < slots2.length){
            overlapLeft = Math.max(slots1[i][0], slots2[j][0]);
            overlapRight = Math.min(slots1[i][1], slots2[j][1]);
            if(overlapRight - overlapLeft >= duration){ //overlapLeft <= overlapRight && 可以不需要duration>=1
                resInterval.add(overlapLeft);
                resInterval.add(overlapLeft+duration);
                return resInterval;
            }
            //如果slots1的interval右界更小，继续下一个slot1，否则用下一个slot2比较
            if(overlapRight == slots1[i][1]){ //slots1[i][1] <= slots2[j][1]
                i++;
            }else{
                j++;
            }
        }
        return resInterval;
    }
}
