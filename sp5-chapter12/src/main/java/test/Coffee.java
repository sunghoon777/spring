package test;

public class Coffee {

    private String name;
    private Integer cost;
    private String manufacturer;
    private String drinkCount;
    private String[] competitors;

    public Coffee() {
        this("",null,"","5",new String[0]);
    }

    public Coffee(String name, Integer cost, String manufacturer,String drinkCount,String [] competitors) {
        this.name = name;
        this.cost = cost;
        this.manufacturer = manufacturer;
        this.drinkCount = drinkCount;
        this.competitors = competitors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDrinkCount() {
        return drinkCount;
    }

    public void setDrinkCount(String drinkCount) {
        this.drinkCount = drinkCount;
    }

    public String[] getCompetitors() {
        return competitors;
    }

    public void setCompetitors(String[] competitors) {
        this.competitors = competitors;
    }
}
