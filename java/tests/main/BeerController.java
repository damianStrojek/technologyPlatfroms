package lab5;

import java.util.Optional;

public class BeerController {
    private BeerRepository beerRepository;

    public BeerController(BeerRepository beerRepository) { this.beerRepository = beerRepository; }

    public String delete(String name){
        try {
            beerRepository.delete(name);
        } catch (IllegalArgumentException exception){
            // Próba usunięcia nieistniejącego obiektu
            return "not found";
        }
        // Próba usuniecia istniejącego obiektu
        return "done";
    }

    public String find(String name){
        Optional<Beer> optional = beerRepository.find(name);
        // Próba pobrania nieistniejącego obiektu
        if(optional.isEmpty()) return "not found";
        // Próba pobrania istniejącego obiektu
        return optional.get().toString();
    }

    public String save(DTO dto) {
        try {
            beerRepository.save(new Beer(dto.getName(), dto.getPercentages()));
        } catch (IllegalArgumentException exception) {
            // Próba zapisania "nowego" obiektu, którego klucz główny znajduje
            // się już w repozytorium
            return "bad request";
        }
        // Próba zapisania nowego obiektu
        return "done";
    }
}
