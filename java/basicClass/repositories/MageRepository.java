package lab1.repositories;

import lab1.entity.Mage;
import java.util.*;

public class MageRepository {
    private Set<Mage> mages;
    private boolean sorted;

    public MageRepository(boolean sorted){
        mages = sorted ? new TreeSet<>() : new HashSet<>();
        this.sorted = sorted;
    }

    public MageRepository(Comparator<Mage> comparator){
        mages = new TreeSet<>(comparator);
        sorted = true;
    }

    public void add(Mage mage){
        mages.add(mage);
    }

    public void delete(Mage mage){
        for (Iterator<Mage> it = mages.iterator(); it.hasNext(); ) {
            Mage currentMage = it.next();
            if(currentMage.equals(mage)){
                mages.remove(mage);
                break;
            }
        }
    }

    public Set<Mage> findAll(){
        return mages;
    }

    public boolean isSorted() {
        return sorted;
    }

}
