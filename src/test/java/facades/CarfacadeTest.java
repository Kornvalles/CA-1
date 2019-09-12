package facades;

import dto.CarDTO;
import utils.EMF_Creator;
import entities.Car;
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
//@Disabled
public class CarfacadeTest {

    private static EntityManagerFactory emf;
    private static Carfacade facade;
    private static Car car;
    private static Car car2;
    private static List<Car> cars;

    public CarfacadeTest() {
    }

    //@BeforeAll
//    public static void setUpClass() {
//        emf = EMF_Creator.createEntityManagerFactory(
//                "pu",
//                "jdbc:mysql://localhost:3307/CA1_test",
//                "dev",
//                "ax2",
//                EMF_Creator.Strategy.CREATE);
//        facade = Carfacade.getCarFacade(emf);
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
       facade = Carfacade.getCarFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        facade = Carfacade.getCarFacade(emf);
        car = new Car("Ford", "Mustang GT500",2008, 430000);
        car2 = new Car("Ford", "Mustang GT500",1968, 350000);
        Car car3 = new Car("Porsche", "Cayenne",2010, 539000);
        cars = new ArrayList<>();
        cars.add(car);
        cars.add(car2);
        cars.add(car3);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNativeQuery("truncate table CA1_test.CAR");
            query.executeUpdate();
            em.getTransaction().commit();
            
            em.getTransaction().begin();
            em.persist(car);
            em.persist(car2);
            em.persist(car3);
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
        assertEquals(3, facade.getCarCount(), "Expects 3 rows in the database");
    }
    
    @Test
    public void testGetAllCars() {
        //Arrange
        List<Car> expResult = cars;
        //Act
        List<Car> result = facade.getAllCars();
        System.out.println(result);
        //Assert
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCarByID() throws Exception {
        //Arrange 
        Car expResult = car;
        //Act
        Car result = facade.getCarById(14);
        //Assert
        assertEquals(expResult, result);
    }

     @Test
    public void testGetCarsByMake() throws Exception {
        //Arrange 
        List<Car> expResult = new ArrayList<>();
        expResult.add(car);
        expResult.add(car2);
        //Act
        List<Car> result = facade.getCarByMake(car.getMake());
        //Assert
        assertEquals(expResult, result);
    }
    
     @Test
    public void testMakeCar() {
        //Arrange
        Car newCar = new Car("Tesla", "Model 3",2018, 399999);
        //Act
        Car result = facade.MakeCar(newCar);
        //Assert
        assertEquals(newCar, result);
        
    }

}
