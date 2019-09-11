package facades;

import dto.JokeDTO;
import entities.Joke;
import utils.EMF_Creator;
import entities.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class JokefacadeTest {

    private static EntityManagerFactory emf;
    private static Jokefacade facade;
    private static Joke joke;
    private static List<Joke> jokes = new ArrayList<>();

    public JokefacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CA1_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = Jokefacade.getJokeFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
       facade = Jokefacade.getJokeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
        // Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    // TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        facade = Jokefacade.getJokeFacade(emf);
        joke = new Joke("joke, HAHA","Hr. HA", 10.0);
        jokes.add(joke);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(joke);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        // Remove any data after each test was run
    }

    // TODO: Delete or change this method 
    @Test
    public void testAFacadeMethod() {
        assertEquals(2, facade.getJokeCount(), "Expects two rows in the database");
    }
    
    @Test
    public void testGetAllStudents() {
        //Arrange
        List<JokeDTO> expResult = new ArrayList<>();
        expResult.add(new JokeDTO(joke));
        //Act
        List<Joke> result = facade.getAllJokes();
        //Assert
        assertEquals(expResult, result);
    }

    @Test
    public void testGetStudentByID() throws Exception {
        //Arrange 
        JokeDTO expResult = new JokeDTO(joke);
        //Act
        Joke result = facade.getJokeById(1);
        //Assert
        assertEquals(expResult, result);
    }

    @Test
    public void testGetJokeByRating() throws Exception {
        //Arrange 
        JokeDTO expResult = new JokeDTO(joke);
        //Act
        Student result = facade.getJokeByRating(10.0);
        //Assert
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAddJoke() {
        //Arrange
        Joke newJoke = new Joke("jokes, meh", "benny", 2.4);
        //Act
        Joke result = facade.addJoke(newJoke);
        //Assert
        assertEquals(newJoke, result);
        // Removing the user again so it doesn't mess up the other tests.
        EntityManager em = emf.createEntityManager();
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(Student.class, new Long(jokes.size() + 1)));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
