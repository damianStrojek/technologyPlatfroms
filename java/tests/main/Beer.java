package lab5;

public class Beer {
    private String name;
    private int percentages;

    public Beer(String name, int percentages) {
        this.name = name;
        this.percentages = percentages;
    }

    public String getName() {
        return name;
    }

    public int getPercentages() {
        return percentages;
    }

    @Override
    public String toString() {
        return "Beer with name " + name
                + "and percentages " + percentages + ".\n";
    }
}
