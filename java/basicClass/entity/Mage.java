package lab1.entity;

import java.util.Set;
import java.util.TreeSet;

public class Mage implements Comparable<Mage> {
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

    public Mage(String name, int level, double power){
        this.name = name;
        this.level = level;
        this.power = power;
        this.apprentices = new TreeSet<>();
    }

    public void addApprentice(Mage mage){ apprentices.add(mage); }

    public void print(String separator){
        System.out.print(separator);
        System.out.println(this);
        for(Mage mage : apprentices){
            mage.print(separator + separator.charAt(0));
        }
    }

    @Override
    public String toString(){
        return "Mage = {" + "name = '" + name + "'," +
                " level = " + level + ", power = " +
                power + "}";
    }

    @Override
    public int compareTo(Mage mage) {
        int result = name.compareTo(mage.name);
        if(result != 0)
            return result;
        result = level - mage.level;
        if(result != 0)
            return result;
        result = Double.compare(power, mage.power);
        return result;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Mage mage = (Mage) o;

        if(level != mage.level) return false;
        if(Double.compare(mage.power, power) != 0) return false;
        return name.equals(mage.name);
    }

    // Alternative to equals() is hashCode but its weaker
    @Override
    public int hashCode(){
        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + level;
        temp = Double.doubleToLongBits(power);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public double getPower() {
        return power;
    }

    public Set<Mage> getApprentices() {
        return apprentices;
    }


}
