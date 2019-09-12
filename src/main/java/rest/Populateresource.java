package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.StudentDTO;
import entities.Student;
import facades.Carfacade;
import facades.Jokefacade;
import utils.EMF_Creator;
import facades.Studentfacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("populate")
public class Populateresource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CA1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final Studentfacade STUDENTFACADE =  Studentfacade.getStudentFacade(EMF);
     private static final Jokefacade JOKEFACADE =  Jokefacade.getJokeFacade(EMF);
     private static final Carfacade CARFACADE =  Carfacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        STUDENTFACADE.populateStudents();
        JOKEFACADE.populateJokes();
        CARFACADE.populateCars();
        return "{\"msg\":\"done!\"}";
    }
    
}
