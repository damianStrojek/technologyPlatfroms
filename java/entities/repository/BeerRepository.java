package lab4.repository;

import lab4.entities.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class BeerRepository extends MainRepository<Beer, String> {
    public BeerRepository(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Beer.class);
    }

    public List<Beer> findAllMoreExpensiveThan(int price){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Beer> list = entityManager.createQuery("SELECT b FROM " + classEntity.getSimpleName() +
                " b WHERE b.price > " + price, classEntity).getResultList();
        entityManager.close();
        return list;
    }

    public List<Beer> findAllMoreExpensiveThan(int price, Brewery brewery){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Beer> list = entityManager.createQuery("SELECT b FROM " + classEntity.getSimpleName() +
                " b WHERE b.price >" + price + " AND brewery = '" +
                brewery.getName() + "'", classEntity).getResultList();
        entityManager.close();
        return list;
    }

    public List<Beer> findAllLessExpensiveThan(int price, Brewery brewery){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Beer> list = entityManager.createQuery("SELECT b FROM " + classEntity.getSimpleName() +
                " b WHERE b.price <" + price + " AND brewery = '" +
                brewery.getName() + "'", classEntity).getResultList();
        entityManager.close();
        return list;
    }
}
