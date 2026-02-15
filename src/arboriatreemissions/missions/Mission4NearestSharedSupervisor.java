package arboriatreemissions.missions;

/**
 * Mission 4: finds lowest common ancestor (nearest shared supervisor) in a BST.
 */
public class Mission4NearestSharedSupervisor {

    /**
     * Builds BST, validates that p and q exist, then returns LCA value.
     */
    public static int solve(int[] values, int p, int q) {
        Bst tree = new Bst();
        for (int value : values) {
            tree.insert(value);
        }

        boolean hasP = tree.contains(p);
        boolean hasQ = tree.contains(q);
        if (!hasP && !hasQ) {
            throw new IllegalArgumentException(p + " and " + q + " do not exist in the tree");
        }
        if (!hasP) {
            throw new IllegalArgumentException(p + " does not exist in the tree");
        }
        if (!hasQ) {
            throw new IllegalArgumentException(q + " does not exist in the tree");
        }

        return tree.lowestCommonAncestor(p, q);
    }

    /**
     * Minimal BST implementation needed for Mission 4.
     */
    private static class Bst {
        private Node root;

        void insert(int value) {
            root = insert(root, value);
        }

        private Node insert(Node node, int value) {
            if (node == null) {
                return new Node(value);
            }
            if (value < node.value) {
                node.left = insert(node.left, value);
            } else if (value > node.value) {
                node.right = insert(node.right, value);
            }
            return node;
        }

        boolean contains(int value) {
            Node current = root;
            while (current != null) {
                if (value < current.value) {
                    current = current.left;
                } else if (value > current.value) {
                    current = current.right;
                } else {
                    return true;
                }
            }
            return false;
        }

        int lowestCommonAncestor(int p, int q) {
            Node current = root;
            while (current != null) {
                // If both targets are on one side, keep moving in that direction.
                if (p < current.value && q < current.value) {
                    current = current.left;
                } else if (p > current.value && q > current.value) {
                    current = current.right;
                } else {
                    // Split point (or equal): this node is the LCA.
                    return current.value;
                }
            }
            throw new IllegalStateException("LCA not found");
        }
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
