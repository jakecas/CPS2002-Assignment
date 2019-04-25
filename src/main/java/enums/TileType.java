package enums;

public enum TileType {
    GRASS("grass"),
    WATER("water"),
    TREASURE("treasure");

    String text;

    TileType(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
