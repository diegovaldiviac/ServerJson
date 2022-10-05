//Represents a vertical and horizontal direction as an object
class JsonValue {
    private String vertical;
    private String horizontal;

    JsonValue(String vertical, String horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    //returns vertical
    public String getVertical() {
        return vertical;
    }

    //returns horizontal
    public String getHorizontal() {
        return horizontal;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Data: {").append("Vertical: ")
                .append(vertical).append(", Horizontal: ")
                .append(horizontal).append("}").toString();
    }
}