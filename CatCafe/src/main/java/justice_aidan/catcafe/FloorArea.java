/*******************************************************************************************************************
 * Author: Aidan Justice
 * Description: Holds the FloorArea base class. It is the parent class for the other floor types. It describes the
 * information about each tile type like cost, revenue, age, etc.
 ******************************************************************************************************************/
package justice_aidan.catcafe;

public class FloorArea {
    private int totalCost;
    private int lastChanged;
    private int age;
    private int weeklyCost;
    private String name;
    private int revenue;
    private int initialCost;
    private String imageURL;
    private int floorAge = 0;
    private int totalRevenue = 0;

    /*******************************************************************************************************************
     * Description: FloorArea Constructor
     *
     * Parameters: String name - name of area
     *             int totalCost - initial cost of tile
     *             int weeklyCost - weekly cost of tile
     *             int revenue - weekly revenue of tile
     *             int age - starting age of tile
     *             int lastChanged - week created
     *             String image - url of image
     * Returns: FloorArea
     ******************************************************************************************************************/
    public FloorArea(String name, int totalCost, int weeklyCost, int revenue, int age, int lastChanged, String image) {
        this.lastChanged = lastChanged;
        this.name = name;
        this.totalCost = totalCost;
        this.initialCost = totalCost;
        this.weeklyCost = weeklyCost;
        this.revenue = revenue;
        this.age = age;
        this.imageURL = image;
    }

    public int getAge() {
        return age;
    }

    /*******************************************************************************************************************
     * Description: Get the text to display on the button.
     *
     * Returns: String - Button text
     ******************************************************************************************************************/
    public String getButtonString() {
        return "-" + name.charAt(0) + "-\n-$" + weeklyCost;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getInitialCost() {
        return initialCost;
    }

    public int getLastChanged() {
        return lastChanged;
    }
    public String getName() {
        return name;
    }

    public int getRevenue() {
        return revenue;
    }
    public int getTotalRevenue() {
        return totalRevenue;
    }

    public int getWeeklyCost() {
        return weeklyCost;
    }

    /*******************************************************************************************************************
     * Description: Updates the area information for a new week
     ******************************************************************************************************************/
    public void nextWeek() {
        age++;
        totalCost += weeklyCost;
        totalRevenue += revenue;
        floorAge++;
    }
    public void setAge(int age) {
        this.age = age;
    }

    /*******************************************************************************************************************
     * Description: Get the overall details of the area
     *
     * Returns: String - floor area details.
     ******************************************************************************************************************/
    @Override
    public String toString() {
        return name + "\nFloor Changed: " + Integer.toString(lastChanged)
                + "\nFloor Age: " + Integer.toString(floorAge)
                + "\nTotal Cost: " + Integer.toString(totalCost);
    }
}
