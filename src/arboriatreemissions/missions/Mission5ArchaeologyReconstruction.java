package arboriatreemissions.missions;

/**
 * Mission 5: reconstructs a binary tree from preorder and inorder traversals,
 * then outputs postorder traversal.
 */
public class Mission5ArchaeologyReconstruction {

    /**
     * Returns postorder traversal as a space-separated string.
     */
    public static String solve(int[] preorder, int[] inorder) {
        Node root = buildTree(preorder, inorder);
        StringBuilder postorder = new StringBuilder();
        collectPostorder(root, postorder);
        return postorder.toString();
    }

    private static Node buildTree(int[] preorder, int[] inorder) {
        // Wrapped in an array so recursion can update a shared mutable index.
        int[] preIndex = {0};
        return build(preorder, inorder, 0, inorder.length - 1, preIndex);
    }

    /**
     * Rebuilds subtree from inorder segment [inLeft, inRight].
     */
    private static Node build(int[] preorder, int[] inorder, int inLeft, int inRight, int[] preIndex) {
        if (inLeft > inRight) {
            return null;
        }

        int rootValue = preorder[preIndex[0]++];
        Node root = new Node(rootValue);

        // Locate root in inorder to split into left and right subtree segments.
        int split = findInorderIndex(inorder, inLeft, inRight, rootValue);
        root.left = build(preorder, inorder, inLeft, split - 1, preIndex);
        root.right = build(preorder, inorder, split + 1, inRight, preIndex);
        return root;
    }

    /**
     * Finds target in current inorder segment.
     */
    private static int findInorderIndex(int[] inorder, int left, int right, int target) {
        for (int i = left; i <= right; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }
        throw new IllegalArgumentException("Invalid traversals: value not found in inorder segment");
    }

    /**
     * Collects traversal in Left -> Right -> Root order.
     */
    private static void collectPostorder(Node node, StringBuilder out) {
        if (node == null) {
            return;
        }

        collectPostorder(node.left, out);
        collectPostorder(node.right, out);

        if (out.length() > 0) {
            out.append(' ');
        }
        out.append(node.value);
    }

    /**
     * Basic binary tree node.
     */
    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }
}
