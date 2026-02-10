package arboriatreemissions.trees;

import java.util.Comparator;

/**
 * Comparator for CrystalIndexEntry ordered by crystal ID.
 */
public class CrystalIdComparator implements Comparator<CrystalIndexEntry> {

    @Override
    public int compare(CrystalIndexEntry a, CrystalIndexEntry b) {
        if (a == null && b == null) {
            return 0;
        }
        if (a == null) {
            return -1;
        }
        if (b == null) {
            return 1;
        }
        return Integer.compare(a.getCrystalId(), b.getCrystalId());
    }
}

