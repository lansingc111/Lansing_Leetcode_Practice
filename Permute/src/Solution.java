import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 回溯算法 backtracking

 DFS + 回溯
 回溯的要点是找到解集是怎样的、状态设置与重置,是否可剪枝
 DFS即递归，需要考虑到退出条件->返回解的条件
 在哪一步返回集
 另外，要考虑状态变量是否是全局一套的，若是，是否解要拷贝，需要的话，再哪一步浅拷贝

 此题目中：
 状态：1.解path（stack)以及2.这个数是否用过 int[] ifUsed
 每一次递归调用都需要回退这两个条件
 返回条件:当遍历的层数(解的size)与nums.length一样时
 在叶子节点返回，且需要拷贝(因为path是全局通用的) **重要：如果不拷贝的话，最终结束回到root时path是null，因为引用是同一个地址，会返回6个[]

 dfs参数: nums, path, depth, ifUsed, len(解应该达到的长度),

 时间复杂度： O(n*n!)
 非叶子节点：排列 A(m,n)=m!/(m-n)!在第一层节点个数:N个数选1个的排列A(N,1);在第二层节点个数:N个数选2个的排列A(N,2); 直到A(N,N-1) 加总<=2N! -> O(n!)，
 每个节点内部内部循环N次->非叶子节点时间复杂度O(N*N!)
 叶子节点：最后一层共N！个叶子节点且每个叶子节点都需要拷贝path O(n)->叶子节点时间复杂度O(N*N!)
 空间复杂度：O(n*n!) 递归调用O(n) & N!个叶子节点返回的每个解拷贝后长度为O(n)
 **/

class Solution {
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> resList = new ArrayList<>();
        int len = nums.length;

        if(len == 0 || nums == null){
            return resList;
        }

        Stack<Integer> path = new Stack<>();
        boolean[] ifUsed = new boolean[len];

        dfs(nums, 0, path, ifUsed, len, resList);
        return resList;

    }

    public void dfs(int[] nums, int depth, Stack<Integer> path, boolean[] ifUsed, int len, List<List<Integer>> resList){
        //递归退出，浅拷贝path,添加解
        if(depth == len){
            resList.add(new ArrayList<>(path));
            return; //退出
        }

        for(int i=0; i<len; i++){
            //如果这个数已经用过了，跳过
            if(ifUsed[i]){
                continue;
            }
            path.push(nums[i]);
            ifUsed[i] = true;

            // System.out.println("  递归之前 => " + path);
            //递归
            dfs(nums,depth+1,path,ifUsed,len,resList);

            //撤销状态更新
            ifUsed[i] = false;
            path.pop();
            //  System.out.println("递归之后 => " + path);
        }
    }
}