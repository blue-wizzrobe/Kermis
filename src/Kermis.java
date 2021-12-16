import java.util.Map;
import java.util.Scanner;

public class Kermis {
    private static Scanner scan = new Scanner(System.in);
    private static final Map<Integer, Attractie> attr = Map.of(
            1, new Botsauto(),
            2, new Spin(),
            3, new Spiegelpaleis(),
            4, new Spookhuis(),
            5, new Hawaii(),
            6, new Ladderklimmen()
    );
    private static Kassa kassa = new Kassa(attr.values());
    private static BelastingInspecteur bi = new BelastingInspecteur();

    public static void main(String[] args) {
        runKermis();
    }

    private static void runKermis() {
        printStartMessage();
        String input = scan.nextLine().toLowerCase();
        eventHandler(input);
        runKermis();
    }

    private static void printStartMessage() {
        System.out.println("Kies een optie:");
        attr.forEach((k, v) -> System.out.println("Toets '" + k + "' om '" + v.getName() + "' te starten."));
        System.out.println("Toets 'o' om de omzet in te zien.");
        System.out.println("Toets 'k' om het aantal verkochte kaartjes in te zien.");
        System.out.println("Toets 'm' om monteurs naar benodigde attracties te sturen.");
        System.out.println("Toets 'b' om de Belastingdienst langs te laten komen!");
    }

    private static void eventHandler(String input) {
        if(!input.matches("[1-6bmok]")) {
            System.out.println(input + " is geen beschikbare optie, probeer opnieuw");
            return;
        }
        switch (input) {
            case "o":
                printRevenue();
                break;
            case "k":
                printTickets();
                break;
            case "m":
                performMaintenance();
                break;
            case "b":
                bi.collectTax(kassa);
                break;
            default:
               runRide(input);
                break;
        }
    }

    private static void performMaintenance() {
        attr.entrySet().stream()
            .filter(e -> e.getValue() instanceof RisicoRijkeAttractie)
            .forEach(e -> {
                RisicoRijkeAttractie ra = (RisicoRijkeAttractie) e.getValue();
                if(ra.isInNeedOfInspection()) {
                    ra.setInNeedOfInspection(false);
                    System.out.println(ra.getName() + " is gerepareerd!");
                }
            });
    }

    private static void printTickets() {
        attr.forEach((k, v) -> System.out.println(v.getName() + " heeft in totaal " + v.getTimesRun() + " tickets verkocht."));
        System.out.println("Het totaal verkochte aantal tickets is: " + kassa.getTotalTickets(attr));
    }

    private static void printRevenue() {
        attr.forEach((k, v) -> System.out.println(v.getName() + " heeft in totaal €" + kassa.calculateRideRevenue(v) + " opgeleverd."));
        System.out.println("De totale omzet is: €" + kassa.calculateTotalRevenue());
    }

    private static void runRide(String input) {
        Attractie current = attr.get(Integer.parseInt(input));
        try {
            current.runRide();
            kassa.updateRideRevenue(current);
        } catch (OnderhoudException e) {
            System.out.println(e.getMessage());
        }
    }
}
