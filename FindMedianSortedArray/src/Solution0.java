/**
 双指针找中位数
 1. 两个数组左右指针，遍历(len1+len2)/2 + 1次找到中位数
 奇数：中位数位置 (len+1)/2 遍历len/2 +1 次
 偶数: 中位数位置 len/2以及(len+1)/2 也需要遍历len/2+1次
 所以以右指针为right,左指针为left。left存储上一个right的值，这样求let和right的均值即可兼容奇数和偶数的场景
 2. 对两个数组各启用一个指针来遍历当前list, curr1和curr2。在遍历次数<= len/2之前(即找到中位数前)，每次比较nums1[curr1]和nums2[curr2]的值，如果nums1[curr1]<=nums2[curr2]或者nums2已经遍历完了,向后移动nums1的指针，不然移动nums2的指针
 3. 要注意的边界条件即一个数组遍历完毕后，再++向后会出界，所以loop条件是curr1<len1 && (curr2>=len2|| nums1[curr1]<=nums2[curr2]),把比较值大小的条件放在判断len2是否遍历完之后，这样值判断可以不进行。 **注意如果这里不提前进行特殊判断，即对nums1或者nums2为空进行特殊判断的话，curr1<len1是必要的，相当于nums1空的话就不会进行，不会有null pointer
 4. 最后根据len的奇偶性输出right或者（left+right)/2

 时间复杂度: O((m+n)/2) 遍历 len/2 +1 次
 空间复杂度: O(1), 只存了一个变量，并没有其他额外空间
 **/

class Solution0 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        //left,right记录要找的中位数对应值。left为right的前一个值
        double left = 0.0, right = 0.0; //注意中位数不一定是整数，要存double
        //curr1,curr2记录在两个数组遍历的位置
        int curr1 = 0, curr2 = 0;

        //无论len奇偶,要找到中位数需要遍历的次数都是len/2+1次
        //因为提示中说明 1<= m+n,所以可以直接else,不用判断nums2是否为空即curr2>len2
        for(int i=0; i <= len/2; i++){
            left = right;
            if(curr1 < len1 && (curr2 >= len2 || nums1[curr1] <= nums2[curr2])){//切记要把curr2是否遍历结束的判断放在只判断之前以免出界
                right = nums1[curr1];
                curr1++;
            }else {
                right = nums2[curr2];
                curr2++;
            }
        }
        //输出中位数的值,要注意要区分奇偶长度，不能直接输出left和right均值，因为最后一次奇数时left存的是中位数前一个值
        if(len % 2 == 0){ //(len & 1) == 0 binary operand 结果是0为偶数，否则为奇数
            return (left + right)/2;
        }else{
            return right;
        }

    }
}



