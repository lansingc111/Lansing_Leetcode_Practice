/**
 二分法
 1. 要找的中位数位置 left,right
 奇数中位数位置: len/2 + 1 即 (len+2)/2
 偶数中位数位置：len/2, len/2+1 即 (len+1)/2, (len+2)/2
 为了统一奇数偶数方便二分法递归，综合要找的中位数即这两个位置的值的均值 (len+1)/2 以及 (len+2)/2
 也就是left和right分别代表要找的第k小的数
 2. 明确二分法作用的部分是：
 每次比较nums1和nums2在[start,start+k-1]的中位数，互相比较。中位数较小的那个list的左侧一半必然不会成为中位数。
 也就是说每次二分可以剔除floor(k/2)的个数
 3. 递归的退出条件是当k==1, 输出两个list当前start指向的较小数。
 或者其中一个list的长度等于0，直接输出另一个list的[start+k-1]
 边界条件：floor(k/2)可能会大于留存的某个list的长度，所以兜底每次end都指向min(len-1,start+floor(k/2)-1)
 4. 参考二分递归的思路，参数为list1,list2,以及分别的start和end以及要找第K个数，根据两边中位数的大小，每次迭代开始、结束以及k的值

 时间复杂度: O(log(k))->O(log((m+n)/2))->O(log(m+n))
 空间复杂度: 因为是尾递归，即直到递归结束才输出，所以使用的栈空间是常数的 O(1)
 **/

class Solution1 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1+len2;
        int leftIndex = (len + 1)/2, rightIndex = (len+2)/2;
        //输出的最终结果是leftIndex和rightIndex对应值的平均值
        return ((findKth(nums1,0,len1-1,nums2,0,len2-1,leftIndex)+findKth(nums1,0,len1-1,nums2,0,len2-1,rightIndex))/2);

    }
    public double findKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k){
        //为了方便，可以使左边的list相关参数一直是两者中当前长度更短的那个，以便于后面判断某一个list空后的输入
        int currLen1 = end1 - start1 +1;
        int currLen2 = end2 - start2 +1;
        if(currLen1 > currLen2){
            return findKth(nums2,start2,end2,nums1, start1,end1,k);
        }

        //这个条件要放在，不然会出现下面退出条件中nums[start1]出界
        if(currLen1 == 0){
            return nums2[start2+k-1];
        }
        //退出条件和结果为两者中较小值
        if(k==1){
            return Math.min(nums1[start1], nums2[start2]);
        }


        //找出两个list从start往后Math.floor(k/2)个数的位置,比较两者这个位置对应值的大小
        int i = start1 + Math.min(currLen1,k/2) -1;  //以免list长度不足k/2而出界
        int j = start2 + Math.min(currLen2,k/2) -1;

        if(nums1[i]<=nums2[j]){
            k =  k-(i - start1 +1);  //因为不一定每次删除的一半长度一定是k/2,可能是currLen,所以剩下的k需要根据删除长度倒减
            return findKth(nums1,i+1,end1,nums2,start2,end2,k);
        }else{
            k = k-(j - start2 +1);
            return findKth(nums1,start1,end1,nums2,j+1,end2,k);
        }

    }
}



