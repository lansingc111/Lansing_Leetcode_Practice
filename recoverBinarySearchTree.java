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
1.中序遍历树(dfs)，但只保留前一个节点，并在遍历过程中进行当前root和pre的判断。正常的二叉搜索树中序遍历的数组应为递增。(递归dfs或者手动模拟建stack)
    1.1 如果存在节点错误，有两种情况：1. 连续节点位置出错，按递增判断，只会有一个点错误
                                 2. 非连续节点位置出错，两个点错误
2.对于不符合递增的位置计入x，y
3. 交换x和y的位置
 */
class Solution {
    //建立变量存储需交换的节点x,y以及临时变量pre记录当前节点前一节点
    private TreeNode x = null;
    private TreeNode y = null;
    private TreeNode pre = null;

    public void recoverTree(TreeNode root) {
        inorder(root);
        swapWrongNodes(x, y);  
    }
    //如果两个错误节点都被找出，即x和y都不为空，则交换x和y节点的值
    public void swapWrongNodes(TreeNode x, TreeNode y){
        if(x != null && y!= null){
            int tmp = x.val; 
            x.val = y.val; 
            y.val = tmp;
        }
    }
    //中序遍历树，并记录pre，比较当前节点与pre。如果pre和当前节点不符合递增，则记录下这两个节点
    public void inorder(TreeNode root){
        if(root == null){
            return;
        }
        inorder(root.left);
        if(pre == null){
            pre = root;
        } else {
            if(root.val < pre.val){
                y = root;
                if(x == null){
                    x = pre;
                }
            }
            pre = root; //当当前节点判断完成后，迭代当前节点为pre，进入dfs右节点
        }
        inorder(root.right);
    }
}


// /**
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode() {}
//  *     TreeNode(int val) { this.val = val; }
//  *     TreeNode(int val, TreeNode left, TreeNode right) {
//  *         this.val = val;
//  *         this.left = left;
//  *         this.right = right;
//  *     }
//  * }
//  */

//  /**
// 1.中序遍历树，存入数组。正常的二叉搜索树中序遍历的数组应为递增。
//     1.1 如果存在节点错误，有两种情况：1. 连续节点位置出错，按递增判断，只会有一个点错误
//                                  2. 非连续节点位置出错，两个点错误
// 2.顺序遍历数组，判断是否存在左侧大于右侧的点，并输出x,y。 记得x的index应为小于y的index
// 3. 交换x和y的位置
//  */
// class Solution {
//     public void recoverTree(TreeNode root) {
//         List<TreeNode> nodes = new ArrayList<TreeNode>();
//         inorder(root,nodes);
//         swapWrongNodes(nodes);
        

//     }
//     public void swapWrongNodes(List<TreeNode> nodes){
//         TreeNode x = null; 
//         TreeNode y = null;
//         for(int i=1;i<nodes.size();++i){
//             if(nodes.get(i).val < nodes.get(i-1).val){
//                 y = nodes.get(i);
//                 if(x==null){
//                     x = nodes.get(i-1);
//                 }else{
//                     break;
//                 }
//             }
//         }
//         if(x != null && y != null){
//             int tmp = x.val; 
//             x.val = y.val;
//             y.val = tmp; 
//         }

//     }
//     public void inorder(TreeNode root, List<TreeNode> nodes){
//         if(root == null){
//             return;
//         }
//         inorder(root.left,nodes);
//         nodes.add(root);
//         inorder(root.right,nodes);
//     }
// }