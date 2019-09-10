package facades;

import dto.StudentDTO;
import entities.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class Studentfacade {

    private static Studentfacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private Studentfacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static Studentfacade getStudentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Studentfacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getStudentCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long studentCount = (long)em.createQuery("SELECT COUNT(r) FROM Student r").getSingleResult();
            return studentCount;
        }finally{  
            em.close();
        }
        
    }

    Student addStudent(Student newStudent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<Student> getAllStudents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Student getStudentById(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Student getStudentByName(String mads) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
