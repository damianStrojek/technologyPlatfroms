package lab4.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Brewery {
    @Id
    private String name;
    private int budget;

    public String getName(){ return this.name; };
    public int getBudget(){ return this.budget; };

    @OneToMany(mappedBy="brewery", fetch= FetchType.EAGER)
    private List<Beer> beers = new ArrayList<>();
    public List<Beer> getBeers(){ return this.beers; };
    public void addBeer(Beer beer) { beers.add(beer); };

    public Brewery(){};
    public Brewery(String name, int budget){
        this.name = name;
        this.budget = budget;
    }

    @PreRemove
    private void preRemove(){ for (Beer beer : beers) beer.setBrewery(null); }

    @Override
    public String toString(){ return "Brewery named " + name + " and with budget " + budget + "$.\n"; };
}
