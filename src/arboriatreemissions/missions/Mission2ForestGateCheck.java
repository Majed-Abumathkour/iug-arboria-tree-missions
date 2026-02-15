package arboriatreemissions.missions;

/**
 * Mission 2: validates whether a level-order array represents a valid BST.
 * Missing nodes are represented by -1.
 */
public class Mission2ForestGateCheck {

    /**
     * Starts recursive validation from root with wide value bounds.
     */
    public static boolean solve(int[] values) {
        return isValidBstArray(values, 0, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * Validates subtree rooted at index using (minExclusive, maxExclusive) bounds.
     */
    private static boolean isValidBstArray(int[] values, int index, long minExclusive, long maxExclusive) {
        if (index >= values.length || values[index] == -1) {
            return true;
        }

        int value = values[index];
        if (value <= minExclusive || value >= maxExclusive) {
            return false;
        }

        int left = 2 * index + 1;
        int right = left + 1;

        return isValidBstArray(values, left, minExclusive, value)
                && isValidBstArray(values, right, value, maxExclusive);
    }
}
