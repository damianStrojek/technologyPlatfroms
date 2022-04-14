package lab4.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Beer {
    @Id
    private String name;
    private int price;

    public String getName(){ return this.name; };
    public int getPrice(){ return this.price; };

    @ManyToOne
    @JoinColumn(name = "brewery")
    private Brewery brewery;
    public Brewery getBrewery(){ return this.brewery; };

    public Beer(){};
    public Beer(String name, int price){
        this.name = name;
        this.price = price;
    }

    public void setBrewery(Brewery brewery){
        this.brewery = brewery;
        if(brewery != null){
            brewery.addBeer(this);
        }
    }

    @Override
    public String toString(){
        return "Beer named " + name + " and priced at " + price + "$.\n";
    }
}
