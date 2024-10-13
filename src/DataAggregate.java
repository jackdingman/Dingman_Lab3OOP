public class DataAggregate {
    String sector;
    String yearMillions;
    String basis;

    public DataAggregate(String sector, String yearMillions, String basis) {
        this.sector = sector;
        this.yearMillions = yearMillions;
        this.basis = basis;
    }
    public String getSector() {
        return sector;
    }
    public String getYearMillions() {
        return yearMillions;
    }
    public String getBasis() {
        return basis;
    }
    @Override
    public String toString() {
        return "Sector: " + getSector() + "Millions in 2023: " + getYearMillions() + "Basis: " + getBasis();
    }
}
