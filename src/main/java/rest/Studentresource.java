package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.StudentDTO;
import entities.Student;
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
@Path("student")
public class Studentresource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/CA1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final Studentfacade FACADE =  Studentfacade.getStudentFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getStudentCount() {
        long count = FACADE.getStudentCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
        public String getAll() {
        List<Student> student = FACADE.getAllStudents();
        List<StudentDTO> sdto = new ArrayList();
        
        for(Student s : student) {
        sdto.add(new StudentDTO(s));
        }
        return GSON.toJson(sdto);
    }
    
    @GET
    @Path("/names/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String byName(@PathParam("name") String name) {
        List<Student> students = FACADE.findStudentByName(name);
        List<StudentDTO> sdto = new ArrayList<>();
        for (Student s : students) {
            sdto.add(new StudentDTO(s));
        }
        return GSON.toJson(FACADE.findStudentByName(name));
    }
    
    
    @Path("populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populateStudents();
        return "{\"msg\":\"done!\"}";
    }
    
}
