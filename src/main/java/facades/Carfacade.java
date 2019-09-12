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
            em.persist(new Car(2008, "Maserati", "GranTurismo", 490000));
            em.persist(new Car(2013, "Maserati", "GranCabrio", 525000));
            em.persist(new Car(2008, "Ford", "Mustang GT500", 430000));
            em.persist(new Car(1968, "Ford", "Mustang GT500", 350000));
            em.persist(new Car(2016, "Porsche", "Macan", 749900));
            em.persist(new Car(2016, "Porsche", "Carrera", 1999900));
            em.persist(new Car(2010, "Porsche", "Cayenne", 539000));
            em.persist(new Car(2018, "Ferrari", "Portofino", 1600000));
            em.persist(new Car(2019, "Ferrari", "Superfast", 2599000));
            em.persist(new Car(2009, "Ferrari", "California", 595000));
            em.persist(new Car(2017, "Bentley", "Continental", 1135000));
            em.persist(new Car(1957, "Bentley", "Saloon", 585000));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
