package arboriatreemissions.missions;

import arboriatreemissions.datastructures.Queue;

/**
 * Mission 1: prints active nodes level by level from a level-order array.
 * Value -1 means a missing/destroyed node position.
 */
public class Mission1EmergencyBroadcastLevels {

    /**
     * Returns lines in the format: Level0:..., Level1:..., etc.
     */
    public static String solve(int[] values) {
        if (values.length == 0 || values[0] == -1) {
            return "";
        }

        // Queue stores array indices, not values, so children are easy to compute.
        Queue<Integer> q = new Queue<>();
        q.enqueue(0);

        // StringBuilder builds large text efficiently inside loops.
        StringBuilder out = new StringBuilder();
        int level = 0;

        while (!q.isEmpty()) {
            int nodesInLevel = q.size();
            out.append("Level").append(level).append(":");

            for (int i = 0; i < nodesInLevel; i++) {
                Integer idx = q.dequeue();
                if (idx == null) {
                    continue;
                }

                if (i > 0) {
                    out.append(' ');
                }
                out.append(values[idx]);

                // Complete-tree index relations in array representation.
                int left = 2 * idx + 1;
                int right = left + 1;

                if (left < values.length && values[left] != -1) {
                    q.enqueue(left);
                }
                if (right < values.length && values[right] != -1) {
                    q.enqueue(right);
                }
            }

            level++;
            if (!q.isEmpty()) {
                out.append('\n');
            }
        }

        return out.toString();
    }
}
