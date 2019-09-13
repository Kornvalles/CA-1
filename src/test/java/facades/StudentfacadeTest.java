package facades;

import dto.StudentDTO;
import utils.EMF_Creator;
import entities.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
@Disabled
public class StudentfacadeTest {

    private static EntityManagerFactory emf;
    private static Studentfacade facade;
    private static Student student;
    private static Student student2;
    private static List<Student> students;

    public StudentfacadeTest() {
    }

    //@BeforeAll
//    public static void setUpClass() {
//        emf = EMF_Creator.createEntityManagerFactory(
//                "pu",
//                "jdbc:mysql://localhost:3307/CA1_test",
//                "dev",
//                "ax2",
//                EMF_Creator.Strategy.CREATE);
//        facade = Studentfacade.getStudentFacade(emf);
//    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
//    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = Studentfacade.getStudentFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        facade = Studentfacade.getStudentFacade(emf);
        student = new Student("Mads", "cphmj12", "grøn");
        student2 = new Student("Mads2", "cphmj13", "grøn");
        Student student3 = new Student("Mads3", "cphmj14", "grøn");
        students = new ArrayList<>();
        students.add(student);
        students.add(student2);
        students.add(student3);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("truncate table CA1_test.STUDENT");
            query.executeUpdate();
            em.getTransaction().commit();

            em.getTransaction().begin();
            em.persist(student);
            em.persist(student2);
            em.persist(student3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(3, facade.getStudentCount(), "Expects two rows in the database");
    }

    @Test
    public void testGetAllStudents() {
        //Arrange
        List<Student> expResult = students;
        //Act
        List<Student> result = facade.getAllStudents();
        System.out.println(result);
        //Assert
        assertEquals(expResult, result);
    }

    @Test
    public void testGetStudentByID() throws Exception {
        //Arrange 
        Student expResult = student;
        //Act
        Student result = facade.getStudentById(1);
        //Assert
        assertEquals(expResult, result);
    }

    @Test
    public void testGetStudentByName() throws Exception {
        //Arrange 
        Student expResult = student;
        //Act
        Student result = facade.getStudentByName("Mads");
        //Assert
        assertEquals(expResult, result);
    }

     @Test
    public void testMakeCar() {
        //Arrange
        Student newStudent = new Student("Mads", "cphmj12", "grøn");
        //Act
        Student result = facade.addStudent(newStudent);
        //Assert
        assertEquals(newStudent, result);
        
    }
}
