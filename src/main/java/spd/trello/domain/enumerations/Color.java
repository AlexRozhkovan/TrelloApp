package spd.trello.domain.enumerations;

public enum Color {

    RED("#FF0000"),
    GREEN("#008000"),
    YELLOW("#FFFF00"),
    BLACK("#000000"),
    BLUE("#0000FF"),
    VIOLET("#EE82EE"),
    ORANGE("#FFA500");

    public final String hash;

    Color(String hash) {
        this.hash = hash;
    }
}
