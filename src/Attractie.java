public class Attractie {

    private String name;
    private double price;
    private int timesRun;

    public void runRide() throws OnderhoudException {
        System.out.println("Attractie: " + name + " draait");
        incrementTimesRun();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTimesRun() {
        return timesRun;
    }

    public void incrementTimesRun() {
        this.timesRun++;
    }
}
