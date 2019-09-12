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
    }

    List<Car> getAllCars() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
