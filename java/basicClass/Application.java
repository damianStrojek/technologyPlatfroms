package lab1;

import lab1.entity.Mage;
import lab1.entity.comparator.MageAlternativeOp;
import lab1.initialize.DataInitialize;
import lab1.repositories.MageRepository;
import lab1.services.MageService;

import java.util.Comparator;
import java.util.Map;

public class  Application {
    public static void main(String[] args){
        Application application = new Application();
        MageService mageService;
        String type = args[0];

        if(type.equals("alternativeSort")){
            mageService = application.createMageService(new MageAlternativeOp());
        } else {
            boolean sorted = type.equals("sorted");
            mageService = application.createMageService(sorted);
        }

        DataInitialize dataInitialize = new DataInitialize(mageService);
        dataInitialize.init();
        Map<Mage, Integer> statistics = mageService.getStatistics();
        application.printStatistics(statistics);
    }

    public MageService createMageService(boolean sorted){
        return new MageService(new MageRepository(sorted));
    }

    public MageService createMageService(Comparator<Mage> comparator){
        return new MageService(new MageRepository(comparator));
    }

    public void printStatistics(Map<Mage, Integer> statistics){
        for (Mage mage : statistics.keySet()){
            System.out.print(mage);
            System.out.println(" : " + statistics.get(mage));
        }
    }
}
