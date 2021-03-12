import java.util.*;

/**
 hashmap法
 1. 遍历一遍数组，存入hashmap,key为num，vlaue为index
 2. 起指针i，j(i+1) 遍历数组，且当nums[i/j] == nums[i-1/j-1]时(i>0, j>i+1)， 跳过每次i，j组合，同时计算target。如果get结果非NULL，则把三元组加入resList。当结果==0时，，跳过i/j。为了避免-2 1 1 以及 1 1 -2都被计入结果，要限制hashmap中匹配target的index要大于j
 **/
class Solution1 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>>resList = new ArrayList<>();
        if(nums.length < 3){
            return resList;
        }

        //排序，因为之后需要用index剔除重复提取出一种组合
        Arrays.sort(nums);
        //把数组加入hashmap，并存储index
        Map<Integer,Integer> charMap = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            // if(!charMap.containsKey(nums[i])){
            //注意：对于重复出现的字符，比如-4，-1，-1，0，2
            //对应的value为最后出现的index，比如-1:2而不是-1:1
            //这样仅对于n个0连续这种情况也不会漏掉
            charMap.put(nums[i],i);
            // }
        }

        int targetIndex;
        Integer target = 0; //注意类型是Integer不是int
        for(int i=0; i<nums.length-2; i++){
            if(nums[i]>0){
                return resList;
            }
            //跳过重复的i
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            for(int j=i+1; j<nums.length; j++){
                if(j>i+1 && nums[j] == nums[j-1]){
                    continue;
                }
                target = 0-nums[i]-nums[j];
                targetIndex = charMap.getOrDefault(target,-1);
                if(targetIndex!=-1 && targetIndex > j){
                    resList.add(new ArrayList<>(Arrays.asList(nums[i],nums[j],nums[targetIndex])));
                }
            }
        }
        return resList;
    }
}


