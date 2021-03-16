/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/**
 递归

 类似最大子序和
 类似后序遍历来选路径

 递归方法：
 对于每一个子节点，计算它的最大贡献maxGain。
 1. 空节点 0
 2. 非空节点: max(maxLeftGain,maxRightGain) + node.val
 递归退出条件：空节点 return 0
 类似最大子序和, 传递的是一条path上的累计gain，而全局变量maxSum可以不通过递归传递，而直接被更新
 即使maxSum被更新了，不影响traverse的进行。因为先走子节点，再走head，所以如果连上path和更大，在跟节点也会更新maxSum

 maxSum的选择：如果以当前节点为root的path的和更大，就更新maxSum

 时间复杂度: O(n) 其中 n 是二叉树中的节点个数。对每个节点访问不超过 2次。(非叶子节点会有经过和回溯，2次访问, 比如root 10，最开始一次，最后回溯一次)
 空间复杂度：O(n) 其中 n 是二叉树中的节点个数。空间复杂度主要取决于递归调用层数，最大层数等于二叉树的高度，最坏情况下，二叉树的高度等于二叉树中的节点个数。


 **/
class Solution0 {
    int maxSum = Integer.MIN_VALUE;//不能放入maxPathSum方法里，这样getMaxSum()会用不了这个参数
    public int maxPathSum(TreeNode root) {
        getMaxGain(root);
        return maxSum;
    }

    public int getMaxGain(TreeNode node){
        if(node == null){
            return 0;
        }
        int maxGainLeft = Math.max(getMaxGain(node.left),0); //与0比较,如果子节点的gain是负数，就舍弃这个子节点的path
        int maxGainRight = Math.max(getMaxGain(node.right),0);

        int cumSum = Math.max(maxGainLeft, maxGainRight)+node.val;
        int newRootPathSum = node.val+ maxGainLeft + maxGainRight;

        maxSum = Math.max(newRootPathSum, maxSum);

        return cumSum;
    }
}