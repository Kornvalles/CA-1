package facades;

import dto.StudentDTO;
import entities.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Studentfacade {

    private static Studentfacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private Studentfacade() {
    }
    
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

    public Student addStudent(Student newStudent) {
                EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newStudent);
            em.getTransaction().commit();
            return newStudent;
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }

    public List<StudentDTO> getAllStudents() {
                EntityManager em = getEntityManager();
        try {
            List<Student> students = em.createNamedQuery("Student.findAll").getResultList();
            List<StudentDTO> result = new ArrayList<>();
            for(Student student: students){
                result.add(new StudentDTO(student));
            }
            return result;
        } finally {
            em.close();
        }
    }

    public StudentDTO getStudentById(long id) {
                EntityManager em = emf.createEntityManager();
        try {
            Student student = em.find(Student.class, id);
            return new StudentDTO(student); 
        } finally {
            em.close();
        }
    }

    public StudentDTO getStudentByName(String name) {
                EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT new dto.StudentDTO(s) FROM Student s WHERE s.name = :name", StudentDTO.class)
                    .setParameter("name", name).getSingleResult();
        } finally {
            em.close();
        }
    }
    
        public void populateStudents() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Student.deleteAllRows").executeUpdate();
            em.persist(new Student("Benjamin", "cph-bb159", "Yellow"));
            em.persist(new Student("Iben", "cph-ia62", "Yellow"));
            em.persist(new Student("Mikkel", "cph-mc335", "Yellow"));
            em.persist(new Student("Nicklas", "cph-nd76", "Yellow"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
