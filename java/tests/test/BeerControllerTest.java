package lab5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class BeerControllerTest {
    private BeerRepository beerRepository;
    private BeerController beerController;

    @BeforeEach
    public void init(){
        beerRepository = mock(BeerRepository.class);
        beerController = new BeerController(beerRepository);
    }

    @Test
    public void testDeleteFoundBeer(){
        String beerName = "name";
        assertThat(beerController.delete(beerName)).isEqualTo("done");
    }

    @Test
    public void testDeleteNotFoundBeer() {
        String beerName = "bad name";
        doThrow(new IllegalArgumentException("Beer " + beerName + " not found")).when(beerRepository).delete(beerName);
        assertThat(beerController.delete(beerName)).isEqualTo("not found");
    }

    @Test
    public void testFindFoundBeer(){
        String beerName = "name";
        Beer beer = new Beer(beerName, 3);
        doReturn(Optional.of(beer)).when(beerRepository).find(beerName);
        assertThat(beerController.find(beerName)).isEqualTo(beer.toString());
    }

    @Test
    public void testFindNotFoundBeer(){
        String mageName = "bad name";
        doReturn(Optional.empty()).when(beerRepository).find(mageName);
        assertThat(beerController.find(mageName)).isEqualTo("not found");
    }

    @Test
    public void testSaveBeerWithExistingKey(){
        String mageName = "existing name";
        Beer beer = new Beer(mageName, 1);
        doThrow(new IllegalArgumentException("Beer with name " + beer.getName()
                + " already exist")).when(beerRepository).save(any(Beer.class));
        assertThat(beerController.save(new DTO(beer.getName(), beer.getPercentages()))).isEqualTo("bad request");
    }

    @Test
    public void testSaveBeerWithNonExistingKey(){
        String beerName = "name";
        assertThat(beerController.save(new DTO(beerName, 3))).isEqualTo("done");
    }
}