package enums;

public enum TileType {
    GRASS,
    WATER,
    TREASURE;

    private boolean isRevealed = false;

    public boolean isRevealed() {
        return isRevealed;
    }

    public void revealTile() {
        isRevealed = true;
    }
}
