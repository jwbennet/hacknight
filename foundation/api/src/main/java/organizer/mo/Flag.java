package organizer.mo;

public enum Flag {
    DEFAULT("None", "flag-default"),
    COMPLETE("Complete", "flag-complete"),
    INFORMATION("Information", "flag-information"),
    IMPORTANT("Important", "flag-important"),
    CANCELED("Canceled", "flag-canceled");

    private final String name;
    private final String color;

    private Flag(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
