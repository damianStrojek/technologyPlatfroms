package lab1.entity.comparator;

import lab1.entity.Mage;
import java.util.Comparator;

public class MageAlternativeOp implements Comparator<Mage>{
    @Override
    public int compare(Mage mageA, Mage mageB){
        int result = mageA.getLevel() - mageB.getLevel();
        if(result != 0)
            return result;
        result = mageA.getName().compareTo(mageB.getName());
        if(result != 0)
            return result;
        result = Double.compare(mageA.getPower(), mageB.getPower());
        return result;
    }
}
