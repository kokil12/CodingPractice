package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    public static void main(String args[]) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(2);
        tree.root.left.right = new Node(2);
        System.out.println("Inorder");
        tree.inorder(tree.root);
        System.out.println("Level Order");
        tree.levelOrder(tree.root);//recursive
        tree.levelOrderNonRecursive(tree.root);
    }

    public void inorder(Node root) {
        if (root != null ) {
            inorder(root.left);
            System.out.println(root.data);
            inorder(root.right);
        }
     }

     public int max(int lh, int rh) {
        if (lh >= rh )
            return lh;
        return rh;
     }

     public int height(Node root) {
        if (root == null) {
            return 0;
        }
        int lh = height(root.left);
        int rh = height(root.right);
        return max(lh,rh) + 1;
     }

     public void printGivenLevel(Node root, int level) {
        if (root == null) {
            return;
        }
        if (level ==1){
            System.out.println(root.data);
        }
        else {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }

     public void levelOrder(Node root) {
        int h = height(root);
        for (int level = 1; level<=h ; level++) {
            printGivenLevel(root, level);
        }
     }

     public void levelOrderNonRecursive(Node root) {
         Queue<Node> queue = new LinkedList<>();
         queue.add(root);
         queue.add(null);
         System.out.println("Level order using queue -->");
         int level = 0;
         while (!queue.isEmpty()) {
             Node node = queue.poll();
             if (node == null && !queue.isEmpty()) {
                 //End of level
                 queue.add(null);
                 System.out.println();
                 level++;
             }
             else if(node != null){
                 System.out.println(node.data);
                 if (node.left != null) {
                     queue.add(node.left);
                 }
                 if (node.right != null) {
                     queue.add(node.right);
                 }
             }
         }
         System.out.println();
         System.out.println("Level : "+level);
     }
}
