package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CarDTO;
import dto.StudentDTO;
import entities.Car;
import entities.Student;
import facades.Carfacade;
import utils.EMF_Creator;
import facades.Studentfacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("car")
public class Carresource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/CA1",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final Carfacade FACADE = Carfacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarCount() {
        long count = FACADE.getCarCount();
        return "{\"count\":" + count + "}";
    }

    @Path("populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populateCars();
        return "Pooulate completed: The cars have been added.";
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String allCars() {
        List<Car> cars = FACADE.getAllCars();
        List<CarDTO> cdto = new ArrayList();

        for (Car c : cars) {
            cdto.add(new CarDTO(c));
        }
        return GSON.toJson(cdto);
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarById(@PathParam("id") long id) {
        return GSON.toJson(new CarDTO(FACADE.getCarById(id)));
    }

}
