package lab5;

public class DTO {
    private String name;
    private int percentages;

    public DTO(String name, int percentages){
        this.name = name;
        this.percentages = percentages;
    }

    public String getName() { return name; }

    public int getPercentages() { return percentages; }

}
