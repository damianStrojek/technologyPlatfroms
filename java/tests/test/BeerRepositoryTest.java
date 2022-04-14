package lab5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BeerRepositoryTest {
    private BeerRepository beerRepository;

    @BeforeEach
    void init(){
        beerRepository = new BeerRepository();
        Beer beer = new Beer("name", 3);
        beerRepository.save(beer);
    }

    @Test
    public void testFoundBeer() {
        Optional<Beer> emptyOptional = Optional.empty();
        String beerName = "name";
        assertThat(beerRepository.find(beerName)).isNotEqualTo(emptyOptional);
    }

    @Test
    public void testFoundNotFoundBeer() {
        Optional<Beer> emptyOptional = Optional.empty();
        String beerName = "bad name";
        assertThat(beerRepository.find(beerName)).isEqualTo(emptyOptional);
    }

    @Test
    public void testDeleteNotFoundBeer() {
        String beerName = "bad name";
        assertThatThrownBy(() -> beerRepository.delete(beerName)).hasMessage("Beer " + beerName + " not found");
    }

    @Test
    public void testSaveBeerWithExistingKey() {
        String beerName = "name";
        Beer beer = new Beer(beerName, 2);
        assertThatThrownBy(() -> beerRepository.save(beer)).hasMessage(
                "Beer with name " + beer.getName() + " already exist");
    }

}
