package facades;

import entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class Jokefacede {

    private static Jokefacede instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private Jokefacede() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static Jokefacede getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Jokefacede();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }

}
