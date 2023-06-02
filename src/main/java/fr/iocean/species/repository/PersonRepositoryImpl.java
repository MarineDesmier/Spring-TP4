package fr.iocean.species.repository;

import fr.iocean.species.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PersonRepositoryImpl implements PersonRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public int deletePersonsWithoutAnimal() {
        // Autres solutions

        // En JPQL
        // Query q = em.createQuery("DELETE FROM Person WHERE animals IS EMPTY");

        // En SQL
        // Query q = em.createNativeQuery("DELETE FROM person WHERE person.id NOT IN (" +
        //         "SELECT person_id FROM person_animals" +
        //         ")");
        
        Query sqlQuery = em.createNativeQuery("delete p from person p left join person_animals pa on p.id = pa.person_id where pa.animals_id is null");
        return sqlQuery.executeUpdate();
    }

    @Transactional
    public void deletePersonsWithoutAnimal2() {
        // En JPQL
//        Query q = em.createQuery("DELETE FROM Person WHERE animals IS EMPTY");
        // En SQL
        Query q = em.createNativeQuery("DELETE FROM person WHERE person.id NOT IN (" +
                "SELECT person_id FROM person_animals" +
                ")");
        q.executeUpdate();
    }

    @Override
    public void insertRandomPersons(int numberToCreate) {
        Random rand = new Random();
        List<String> noms = Arrays.asList("Blanc", "Boudi", "Brahmi", "Brun", "Duflot", "Grobost", "Guigue", "Haned", "Mohamed", "Vignozzi", "Omari", "Ramier", "Randrianarivony", "Warin", "Mage");
        List<String> prenoms = Arrays.asList("David", "Mohand", "Sonia", "Justine", "Valentin", "Garmi", "Véronique", "Abderrahmane", "Amin", "Aurélie", "Ismail", "Alexandre", "Rijandrisolo", "Thomas", "Jordi");

        for (int i = 0; i < numberToCreate ; i++) {
            Person p = new Person();
            p.setAge(rand.nextInt(120));
            p.setFirstname(prenoms.get(rand.nextInt(prenoms.size())));
            p.setLastname(noms.get(rand.nextInt(noms.size())));
            em.persist(p);
        }
    }

}
