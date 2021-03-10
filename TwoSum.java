/** 
1. TwoSum
hashamp
 put every number into a hashmap: key is the number & value is the index 
 for each number, check if target-number exists in the hashmap
    if yes, return the index of number and map[target-number]
    if no, loop into next number 
时间复杂度：O(n) n为数组的元素个数。每次需要target-number的时间是O(1)
空间复杂度：O(n) n为数组的元素个数，额外需要哈希表的空间
**/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i=0; i<nums.length; i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{i, map.get(target-nums[i])};
            }
            map.put(nums[i],i);
        }
        return new int[0];
    }
}


// brutal force 暴力枚举
// find target -x after the index of x
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for(int i =0; i < nums.length -1; i++){
            for (int j = i+1; j < nums.length; j++){
                if(nums[i]+nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }
}