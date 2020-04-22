package algroithm;

import algroithm.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBST {
    public int numTrees(int n) {
        if(n == 0)
            return 0;
        if(n == 1)
            return 1;
        int[] a = new int[n+1];
                // 根据二叉查找树可以知道，某根节点x，它的左子树的值全<=x（当然本题不存在等于的情况），它的右子树的值全>=x，所以，当它的根节点是1的时候，左子树个数为 0 ，右子树的个数为 n-1， 当它的根节点为 2 的时候， 左子树个数为 1， 右子树的个数为 n-2……
                // 树的不同形态的二叉查找树的个数 = 根节点的左子树的个数*右子树的个数
                //动态规划，从前到后计算出当有i个节点时,它有多少种不同形态的树。nums[i] += nums[j] * nums[i-1-j]  （初始j==0，每做完一步j++）。（这里i-1-j 减掉的 1 代表是根节点占了一个位置）
                // 转移方程 a[n](n个节点所有二叉树的可能性) a[n] = a[0] * a[n-1-0] + a[1] * a[n-1-1] + .. + a[i] * a[n-1-i] + ..a[n - 1] * a[0]  减一是排除根节点
                for(int i = 2 ;i<=n ;i++){
                    for(int j = 0 ;j<=i ;j++){
                        a[i] += a[j] * a[i-j-1];
            }
        }
        return a[n];
    }

    public List<TreeNode> generateTrees(int n) {
        return new ArrayList<>(0);
    }
}
