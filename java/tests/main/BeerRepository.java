package lab5;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

public class BeerRepository {
    private Collection<Beer> collection = new HashSet<>();

    public Optional<Beer> find(String name){
        return collection.stream().filter(beer ->
                beer.getName().equals(name)).findFirst();
    }

    public void delete(String name) throws IllegalArgumentException {
        // Próba usunięcia nieistniejącego obiektu
        Beer beer = collection.stream().filter(currentBeer ->
                currentBeer.getName().equals(name)).findFirst().orElseThrow(() ->
                new IllegalArgumentException(("Beer " + name + " not found.")));
        collection.remove(beer);
    }

    public void save(Beer beer) throws IllegalArgumentException{
        // Próba zapisania obiektu, którego klucz główny już znajduje sie w repozytorium
        collection.forEach(currentBeer -> {
            if (currentBeer.getName().equals(beer.getName()))
                throw new IllegalArgumentException("Beer with name " +
                        beer.getName() + " already exist");
        });
        collection.add(beer);
    }

}
