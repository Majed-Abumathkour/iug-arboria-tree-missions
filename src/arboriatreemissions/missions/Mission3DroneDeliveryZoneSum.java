package arboriatreemissions.missions;

/**
 * Mission 3: builds a BST from insertion order, then computes sum of values in [L, R].
 */
public class Mission3DroneDeliveryZoneSum {

    /**
     * Inserts all values into BST, then returns inclusive range sum.
     */
    public static long solve(int[] values, int left, int right) {
        Bst tree = new Bst();
        for (int value : values) {
            tree.insert(value);
        }
        return tree.rangeSum(left, right);
    }

    /**
     * Minimal BST implementation needed for Mission 3.
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
            // Duplicate values are ignored to keep BST values unique.
            return node;
        }

        long rangeSum(int left, int right) {
            return rangeSum(root, left, right);
        }

        private long rangeSum(Node node, int left, int right) {
            if (node == null) {
                return 0L;
            }

            // BST pruning: if value is too small, only right subtree can contribute.
            if (node.value < left) {
                return rangeSum(node.right, left, right);
            }
            // BST pruning: if value is too large, only left subtree can contribute.
            if (node.value > right) {
                return rangeSum(node.left, left, right);
            }

            // Current node is inside range, so include it and explore both sides.
            return node.value
                    + rangeSum(node.left, left, right)
                    + rangeSum(node.right, left, right);
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
