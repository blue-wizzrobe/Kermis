import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Kassa {

    private int totalTickets;
    private final Map<Attractie, Double> revenueMap = new HashMap<>();
    private double taxes;
    private int inspectorVisits;

    public Kassa(Collection<Attractie> attracties) {
        attracties.forEach(a -> revenueMap.put(a, 0.0));
    }

    public double calculateTotalRevenue() {
        return revenueMap.values().stream().reduce(0.0, Double::sum);
    }

    public double calculateRideRevenue(Attractie ride) {
        return revenueMap.get(ride);
    }

    public int getTotalTickets(Map<Integer, Attractie> attr) {
        totalTickets = 0;
        attr.forEach((k, v) -> totalTickets += v.getTimesRun());
        return totalTickets;
    }

    public void updateRideRevenue(Attractie ride) {
        revenueMap.replace(ride, revenueMap.get(ride) + ride.getPrice());
        if(ride instanceof GokAttractie) {
            taxes += ((GokAttractie) ride).payTax(ride.getPrice());
        }
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes){
        this.taxes = taxes;
    }

    public void incrementTaxVisits(){
        inspectorVisits++;
    }

    public int getInspectorVisits() {
        return inspectorVisits;
    }
}
