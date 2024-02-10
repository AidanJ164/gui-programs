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
        age++;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "\nFloor Changed: " + Integer.toString(lastChanged)
                + "\nFloor Age: " + Integer.toString(age)
                + "\nTotal Cost: " + Integer.toString(totalCost);
    }

    public int getWeeklyCost() {
        return weeklyCost;
    }

    public int getAge() {
        return age;
    }
}
