package arboriatreemissions.trees;

/**
 * A lightweight entry stored in the Arboria BST index.
 */
public class CrystalIndexEntry {

    private final int crystalId;
    private final String crystalLabel;

    public CrystalIndexEntry(int crystalId, String crystalLabel) {
        this.crystalId = crystalId;
        this.crystalLabel = crystalLabel;
    }

    public int getCrystalId() {
        return crystalId;
    }

    public String getCrystalLabel() {
        return crystalLabel;
    }

    @Override
    public String toString() {
        if (crystalLabel == null || crystalLabel.isEmpty()) {
            return String.valueOf(crystalId);
        }
        return crystalId + " - " + crystalLabel;
    }
}

