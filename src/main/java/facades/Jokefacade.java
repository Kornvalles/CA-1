package facades;

import dto.JokeDTO;
import entities.Joke;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Kodebanditterne
 */
public class Jokefacade {

    private static Jokefacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private Jokefacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static Jokefacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Jokefacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //TODO Remove/Change this before use
    public long getJokeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long jokeCount = (long) em.createQuery("SELECT COUNT(r) FROM Joke r").getSingleResult();
            return jokeCount;
        } finally {
            em.close();
        }

    }

    public Joke addJoke(Joke newJoke) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(newJoke);
            em.getTransaction().commit();
            return newJoke;
        } catch (IllegalArgumentException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }

    public Joke getRandomJoke() {
        List<Joke> jokes = getAllJokes();
        long randomNumber = (long) (Math.random() * jokes.size() + 1);
        return getJokeById(randomNumber);
    }

    public Joke getJokeById(long id) {
        EntityManager em = getEntityManager();
        try {
            Joke joke = em.find(Joke.class, id);
            return joke;
        } finally {
            em.close();
        }
    }

    public List<Joke> getAllJokes() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Joke> query = em.createQuery("SELECT j FROM Joke j", Joke.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void populateJokes() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Joke.deleteAllRows").executeUpdate();
            em.persist(new Joke("Hvorfor var blondinen glad for, at samle et puzzlespil på 6 måneder?", "fordi der stod 2-4 år", 3));
            em.persist(new Joke("En røver kommer ind i butikken og stjæler et TV. blondinen løber efter ham og råber, “Vent, du har glemt fjernbetjeningen!", "Blondiner er generelt dumme.", 10));
            em.persist(new Joke("Alle børnene kom sikkert over havet undtagen Jannik han tog titanic", "Titanic var det verdenskendte skib der sank på sin jomfrurejse", 2));
            em.persist(new Joke("Hvorfor er zoologisk have aldrig blevet solgt? - Den er for dyr.", "Ordet dyrs homonym", 6));
            em.persist(new Joke("Jeg overvejer at gifte mig med en tysker er det over grænsen?", "Tyskland grænser op til Danmark som der formodes, der bliver talt om.", 8));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
