package lab1.services;

import lab1.entity.Mage;
import lab1.repositories.MageRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MageService {
    private final MageRepository mageRepository;

    public MageService(MageRepository mageRepository){ this.mageRepository = mageRepository; }

    public void create(Mage mage){ mageRepository.add(mage); }

    public void delete(Mage mage){ mageRepository.delete(mage); }

    public Map<Mage, Integer> getStatistics(){
        Map<Mage, Integer> statistics = mageRepository.isSorted() ? new TreeMap<>() : new HashMap<>();

        Set<Mage> mages = mageRepository.findAll();
        for (Mage mage : mages){
            statistics.put(mage, 0);
            evaluateStatistics(mage, mage, statistics);
        }
        return statistics;
    }

    private void evaluateStatistics(Mage mage, Mage currentMage, Map<Mage, Integer> statistics){
        for (Mage apprentice : currentMage.getApprentices()){
            statistics.put(mage, statistics.get(mage) + 1);
            if(apprentice.getApprentices().size() > 0){
                evaluateStatistics(mage, apprentice, statistics);
            }
        }
    }



}
