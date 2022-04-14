package lab4.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import lab4.entities.*;
import java.util.List;
import java.util.Locale;

public class BreweryRepository extends MainRepository<Brewery, String> {
    public BreweryRepository(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Brewery.class);
    }

    public List<Brewery> findAllWithBiggerBudget(int budget) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Brewery> list = entityManager.createQuery("SELECT br FROM " + classEntity.getSimpleName() +
                " br WHERE br.budget > " + budget, classEntity).getResultList();
        entityManager.close();
        return list;
    }

    public Brewery findParticularBrewery(String name){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Brewery> list = entityManager.createQuery("SELECT br FROM " + classEntity.getSimpleName() +
                        " br WHERE br.name = '" + name + "'", classEntity).getResultList();
        entityManager.close();
        if(list.size() > 0) return list.get(0);
        else return null;
    }
}
