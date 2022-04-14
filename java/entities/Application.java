package lab4;

import lab4.entities.*;
import lab4.repository.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab4");
        BeerRepository beerRepository = new BeerRepository(entityManagerFactory);
        BreweryRepository breweryRepository = new BreweryRepository(entityManagerFactory);
        init(beerRepository, breweryRepository);
    }

    public static void init(BeerRepository beerRepository, BreweryRepository breweryRepository){
        Scanner input = new Scanner(System.in);
        System.out.println("What do you want to do?\n1. Add beer\n2. Add brewery\n" +
                "3. Show all beers with higher price than\n4. Show all breweries and beers\n" +
                "5. Delete particular brewery\n6. Show particular brewery with price of beer less than\n7. Exit");
        int inp = input.nextInt();
        while(inp != 7) {
            switch (inp) {
                case 1:
                    System.out.print("Input name of beer and then price: ");
                    String newBeerName = input.next();
                    int newPrice = input.nextInt();
                    Beer newBeer = new Beer(newBeerName, newPrice);
                    System.out.print("From which brewery is this beer : ");
                    String beerBrewery = input.next();
                    if(beerBrewery != "null"){
                        Brewery particular = breweryRepository.findParticularBrewery(beerBrewery);
                        newBeer.setBrewery(particular);
                    }
                    else{
                        newBeer.setBrewery(null);
                    }
                    beerRepository.add(newBeer);
                    break;
                case 2:
                    System.out.print("Input name of brewery and then budget: ");
                    String newBreweryName = input.next();
                    int newBudget = input.nextInt();
                    Brewery newBrewery = new Brewery(newBreweryName, newBudget);
                    breweryRepository.add(newBrewery);
                    break;
                case 3:
                    System.out.print("What price are we talking about: ");
                    int price = input.nextInt();
                    List<Beer> beers = beerRepository.findAllMoreExpensiveThan(price);
                    System.out.println("All beers more expensive than " + price + "$:");
                    for(int i = 0; i < beers.size(); i++) System.out.println(beers.get(i));
                    break;
                case 4:
                    System.out.println("All breweries: ");
                    List<Brewery> breweries = breweryRepository.findAll();
                    for(int i = 0; i < breweries.size(); i++) System.out.println(breweries.get(i));
                    System.out.println("All beers: ");
                    List<Beer> beers2 = beerRepository.findAll();
                    for(int i = 0; i < beers2.size(); i++) System.out.println(beers2.get(i));
                    break;
                case 5:
                    System.out.print("Which brewery you want to delete: ");
                    String breweryToDelete = input.next();
                    Brewery particular2 = breweryRepository.findParticularBrewery(breweryToDelete);
                    if(particular2 == null) System.out.println("There is no brewery named like this");
                    else{
                        breweryRepository.burnDown(particular2);
                        System.out.println("Brewery was burned down.");
                    }
                    break;
                case 6:
                    System.out.print("Which brewery are we talking about: ");
                    String breweryToShow = input.next();
                    Brewery particular3 = breweryRepository.findParticularBrewery(breweryToShow);
                    if(particular3 == null) System.out.println("There is no brewery named like this");
                    else{
                        System.out.print("What price are we talking about: ");
                        int priceToShow = input.nextInt();
                        List<Beer> beersToShow = beerRepository.findAllLessExpensiveThan(priceToShow, particular3);
                        for(int i = 0; i < beersToShow.size(); i++) System.out.println(beersToShow.get(i));
                    }
                    break;
                default:
                    System.out.println("Choose one of the available options.");
                    break;
            }
            System.out.println("What do you want to do?\n1. Add beer\n2. Add brewery\n" +
                    "3. Show all beers with less price than\n4. Show all breweries and beers\n" +
                    "5. Delete particular brewery\n6. Show particular brewery with price of beer less than\n7. Exit");
            inp = input.nextInt();
        }
    }
}
