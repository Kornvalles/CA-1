package facades;

import dto.JokeDTO;
import entities.Joke;
import utils.EMF_Creator;
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
public class JokefacadeTest {

    private static EntityManagerFactory emf;
    private static Jokefacade facade;
    private static Joke joke;
    private static Joke joke2;
    private static List<Joke> jokes;

    public JokefacadeTest() {
    }

    //@BeforeAll
//    public static void setUpClass() {
//        emf = EMF_Creator.createEntityManagerFactory(
//                "pu",
//                "jdbc:mysql://localhost:3307/CA1_test",
//                "dev",
//                "ax2",
//                EMF_Creator.Strategy.CREATE);
//        facade = Jokefacade.getJokeFacade(emf);
//    }

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
        joke2 = new Joke("joke2, HAHA","Hr. HA", 10.0);
        Joke joke3 = new Joke("joke3, HAHA", "Hr. HA", 10.0);
        jokes = new ArrayList<>();
        jokes.add(joke);
        jokes.add(joke2);
        jokes.add(joke3);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("truncate table CA1_test.JOKE");
            query.executeUpdate();
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            em.persist(joke);
            em.persist(joke2);
            em.persist(joke3);
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
        assertEquals(3, facade.getJokeCount(), "Expects 3 rows in the database");
    }
    
//    @Test
//    public void testGetAllJoke() {
//        //Arrange
//        List<Joke> expResult = jokes;
//        //Act
//        List<Joke> result = facade.getAllJokes();
//        System.out.println(result);
//        //Assert
//        assertEquals(expResult, result);
//    }

    @Test
    public void testGetJokeByID() throws Exception {
        //Arrange 
        Joke expResult = joke;
        //Act
        Joke result = facade.getJokeById(2);
        //Assert
        assertEquals(expResult, result);
    }

//    @Test
//    public void testGetRandomJoke() throws Exception {
//        //Arrange 
//        Joke expResult = facade.getRandomJoke();
//        //Act
//        Joke result = facade.getRandomJoke();
//        //Assert
//        assertEquals(expResult, result);
//    }
    
     @Test
    public void testAddJoke() {
        //Arrange
        Joke newJoke = new Joke("test", "test", 1.0);
        //Act
        Joke result = facade.addJoke(newJoke);
        //Assert
        assertEquals(newJoke, result);
    }

}
