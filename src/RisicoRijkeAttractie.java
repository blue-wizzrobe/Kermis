public abstract class RisicoRijkeAttractie extends Attractie{

    private boolean inNeedOfInspection;
    private boolean inspectedAtSetup;
    private int runLimit;

    @Override
    public void runRide() throws OnderhoudException {
        if (!inNeedOfInspection) {
            System.out.println("Attractie: " + this.getName() + " draait");
            incrementTimesRun();
            inNeedOfInspection =  getTimesRun() % runLimit == 0;
        } else {
            throw new OnderhoudException("Deze attractie heeft onderhoud nodig!");
        }
    }

    public boolean isInNeedOfInspection() {
        return inNeedOfInspection;
    }

    public void setInNeedOfInspection(boolean inNeedOfInspection) {
        this.inNeedOfInspection = inNeedOfInspection;
    }

    public boolean isInspectedAtSetup() {
        return inspectedAtSetup;
    }

    public void setInspectedAtSetup(boolean inspectedAtSetup) {
        this.inspectedAtSetup = inspectedAtSetup;
    }

    public int getRunLimit() {
        return runLimit;
    }

    public void setRunLimit(int runLimit) {
        this.runLimit = runLimit;
    }
}
