import java.util.LinkedList;
import java.util.Queue;


//Approach :
// 1. Find root2-> data in root1 using any traversal.
// 2. Now check the two subtree's are superimposable.
public class TreeSuperimpose {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(1);
        t1.left = t2;
        t3.right = t4;

        if (!checkSuperimpose(t1, t3)) {
            System.out.println("Not imposable");
        } else {
            System.out.println("Yayy. Superimposed");
        }
    }

    private static boolean checkSuperimpose(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root1);
        while (!queue.isEmpty()) {
            root1 = queue.poll();
            if (root1.data == root2.data)
                break;
            if (root1.left != null)
                queue.add(root1.left);
            if (root1.right != null)
                queue.add(root1.right);
        }
        if (check(root1, root2))
            return true;
        return false;

    }

    private static boolean check(TreeNode root1, TreeNode root2) {
        if ((root1 == null && root2 == null) || (root1 != null && root2 == null) || (root1 == null && root2 != null))
            return true;
        if (root1.data != root2.data)
            return false;
        return check(root1.left, root2.left) && check(root1.right, root2.right);
    }
}