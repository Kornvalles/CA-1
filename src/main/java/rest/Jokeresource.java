package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.JokeDTO;
import entities.Joke;
import facades.Jokefacade;
import java.util.ArrayList;
import java.util.List;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
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
@Path("joke")
public class Jokeresource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/CA1",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final Jokefacade FACADE = Jokefacade.getJokeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getJokeCount() {
        long count = FACADE.getJokeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":" + count + "}";  //Done manually so no need for a DTO
    }

    @Path("random")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRandom() {
        return GSON.toJson(new JokeDTO(FACADE.getRandomJoke()));
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        List<Joke> joke = FACADE.getAllJokes();
        List<JokeDTO> jdto = new ArrayList();
        
        for(Joke j : joke) {
        jdto.add(new JokeDTO(j));
        }
        return GSON.toJson(jdto);
    }
}
