import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 排序 + 双指针
 1. 对nums排序，这样方便按三数之和与0的比较进行左移和右移
 2. 启用i遍历nums, 再启用left和right在右侧进行寻找。left = i+1; right = len-1
 3. 如果三个数之和>0, right--; <1,left++;因为是排序且i从最左侧开始，所以当>0或者<0时，不用考虑回去找。因为那个i的组合都已经找过了。
 4. 如何避免重复：1.遍历i时，如果非第一个i且nums[i]和nums[i-1]一样，就跳过 2.当三个数之和==0时，移动l和r时，也需要跳过重复的l/r

 注意edge case [0,0,0]; [0,0,0,0]...

 时间复杂度：O(n^2) 数组排序 O(NlogN)，i遍历数组 O(n)，左右双指针遍历 O(n)，
    总体 O(NlogN)+O(n)∗O(n) -> O(n^2)
 空间复杂度：O(1) 不考虑结果数组
 **/

class Solution0 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resList = new ArrayList<>();

        if(nums.length < 3){
            return resList;
        }
        //正序排序
        Arrays.sort(nums);

        //每次遍历i，启用新的left和right双指针
        for(int i=0; i<nums.length-2; i++){
            // //如果i对应的数>0,最左侧为0时,说明之后无法形成和为0了，直接返回
            if(nums[i]>0){
                return resList;
            }
            //跳过重复的i
            if(i > 0 && nums[i] == nums[i-1]){ //切记把i>0限制在前，不然nums[i-1]会出界到nums[-1]
                continue;
            } //本体核心
            int left = i+1;
            int right = nums.length-1;

            //对每一个i进行一次完整搜索
            while(left < right){
                int sum = nums[left] + nums[right] + nums[i];
                List<Integer> threeNum = new ArrayList<>();
                if(sum > 0){
                    right--;
                }else if(sum < 0){
                    left++;
                }else if(sum == 0){
                    //跳过重复的左右指针
                    while(nums[right] == nums[right-1] && left < right-1){
                        right--; //定位到当前左指针值最左一个重复位置
                    }
                    while(nums[left] == nums[left+1] && left+1 < right){
                        left++;//定位到当前右指针值最右一个重复位置
                    }

                    threeNum.add(nums[i]);
                    threeNum.add(nums[left]);
                    threeNum.add(nums[right]);
                    resList.add(threeNum);

                    right = right-1;
                    left = left+1;

                }
            }
        }
        return resList;

    }
}


