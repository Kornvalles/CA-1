package facades;

import dto.CarDTO;
import utils.EMF_Creator;
import entities.Car;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
public class CarfacadeTest {

    private static EntityManagerFactory emf;
    private static Carfacade facade;
    private static Car car;
    private static List<Car> cars = new ArrayList<>();

    public CarfacadeTest() {
    }

    //@BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CA1_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = Carfacade.getCarFacade(emf);
    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
//    @BeforeAll
//    public static void setUpClassV2() {
//       emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST,Strategy.DROP_AND_CREATE);
//       facade = Carfacade.getCarFacade(emf);
//    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        facade = Carfacade.getCarFacade(emf);
        car = new Car(1998, "Toyota", "Corolla", 6000);
        cars.add(car);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.persist(car);
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
        assertEquals(2, facade.getCarCount(), "Expects two rows in the database");
    }
    
    @Test
            @Test
    public void testGetAllCars() {
            @Test
    public void testGetAllStudents() {
            @Test
    public void testGetAllStudents() {
        //Arrange
        List<CarDTO> expResult = new ArrayList<>();
        expResult.add(new CarDTO(car));
        //Act
        List<Car> result = facade.getAllCars();
        //Assert
        assertEquals(expResult, result);
    }

    @Test
    public void testGetStudentByID() throws Exception {
        //Arrange 
        CarDTO expResult = new CarDTO(car);
        //Act
        Car result = facade.getCarById(1);
        //Assert
        assertEquals(expResult, result);
    }

     @Test
    public void testGetCarByMake() throws Exception {
        //Arrange 
        List<Car> expResult = new ArrayList<>();
        expResult.add(car);
        expResult.add(car2);
        CarDTO expResult = new CarDTO(car);
        CarDTO expResult = new CarDTO(car);
        List<CarDTO> expResult = new ArrayList<>();
        //does this need an if-statement or something guys? 
        expResult.add(new CarDTO(car));
        //Act
        Car result = facade.getCarByMake("Toyota");
        //Assert
        assertEquals(expResult, result);
    }
    
     @Test
    public void testMakeCar() {
        //Arrange
        Car newCar = new Car(2018, "Tesla", "Model 3", 399999);
        //Act
        Car result = facade.MakeCar(newCar);
        //Assert
        assertEquals(newCar, result);
        // Removing the user again so it doesn't mess up the other tests.
        EntityManager em = emf.createEntityManager();
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.find(Car.class, new Long(cars.size() + 1)));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
