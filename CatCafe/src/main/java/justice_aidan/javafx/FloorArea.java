package justice_aidan.javafx;

public class FloorArea {
    private int totalCost;
    private int lastChanged;
    private int age;
    private int weeklyCost;
    private String name;
    private int revenue;
    public FloorArea(String name, int totalCost, int weeklyCost, int revenue, int age) {
        lastChanged = 0;
        this.name = name;
        this.totalCost = totalCost;
        this.weeklyCost = weeklyCost;
        this.revenue = revenue;
        this.age = age;
    }

    public void nextWeek() {

    }

    public String getName() {
        return name;
    }
}
