public class BelastingInspecteur {

    private double totalTaxes;

    public void collectTax(Kassa kassa){
        double tax = kassa.getTaxes();
        totalTaxes += tax;
        kassa.setTaxes(0.0);
        kassa.incrementTaxVisits();
        System.out.println("De Belastingdienst heeft: €" + tax + " geïnd.");
        System.out.println("In totaal heeft dit geintje: €" + totalTaxes + " gekost.");
    }
}
