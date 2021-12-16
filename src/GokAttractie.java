public interface GokAttractie {

    default double payTax(double revenue) {
        return revenue / 100 * 30;
    }
}
