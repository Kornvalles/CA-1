package facades;

import entities.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Carfacade {

    private static Carfacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private Carfacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static Carfacade getCarFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Carfacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getCarCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long carCount = (long)em.createQuery("SELECT COUNT(r) FROM Car r").getSingleResult();
            return carCount;
        }finally{  
            em.close();
        }
        
    }

    public Car MakeCar(Car newCar) {
        EntityManager em = emf.createEntityManager();
        try{
        em.getTransaction().begin();
        em.persist(newCar);
        em.getTransaction().commit();
        return newCar;
        } finally {
        em.close();
        }
    }

    public List<Car> getCarByMake(String make) {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c WHERE c.make = :make", Car.class)
                    .setParameter("make", make);
            return query.getResultList();
        } finally {
        em.close();
        }
    }

    public Car getCarById(long id) {
        EntityManager em = emf.createEntityManager();
        try{
            Car car = em.find(Car.class, id);
            return car;
        } finally {
        em.close();
        }
    }

    public List<Car> getAllCars() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Car> query = em.createQuery("SELECT c FROM Car c", Car.class);
            return query.getResultList();
        } finally {
        em.close();
        }
    }

    public void populateCars() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.persist(new Car("Maserati", "GranTurismo",2008, 490000));
            em.persist(new Car("Maserati", "GranCabrio",2013, 525000));
            em.persist(new Car("Ford", "Mustang GT500",2007, 430000));
            em.persist(new Car("Ford", "Mustang GT500",1968, 350000));
            em.persist(new Car("Porsche", "Macan",2016, 749900));
            em.persist(new Car("Porsche", "Carrera",2017, 1999900));
            em.persist(new Car("Porsche", "Cayenne",2010, 539000));
            em.persist(new Car("Ferrari", "Portofino",2019, 1600000));
            em.persist(new Car("Ferrari", "Superfast",2020, 2599000));
            em.persist(new Car("Ferrari", "California",2009, 595000));
            em.persist(new Car("Bentley", "Continental",2017, 1135000));
            em.persist(new Car("Bentley", "Saloon",1957, 585000));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
