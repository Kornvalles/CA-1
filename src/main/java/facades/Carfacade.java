package facades;

import entities.Car;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    
    //TODO Remove/Change this before use
    public long getCarCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long carCount = (long)em.createQuery("SELECT COUNT(r) FROM Car r").getSingleResult();
            return carCount;
        }finally{  
            em.close();
        }
        
    }

    Car MakeCar(Car newCar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Car getCarByMake(String toyota) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Car getCarById(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
<<<<<<< HEAD
    }

    List<Car> getAllCars() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

<<<<<<< HEAD
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
=======
    }

    List<Car> getAllCars() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> parent of fc7bdaa... CarFacade is done
    }

=======
>>>>>>> parent of a402c40... Update Carfacade.java
}
