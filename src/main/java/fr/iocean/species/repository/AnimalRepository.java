package fr.iocean.species.repository;

import fr.iocean.species.enums.Sex;
import fr.iocean.species.model.Animal;
import fr.iocean.species.model.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    /**
     * Renvoie tous les animaux de la Species fournie en paramètre
     * @param species la Species des animaux retournés
     */
    List<Animal> findBySpecies(Species species);

    /**
     * Renvoie tous les animaux dont la couleur fait partie de la liste de couleurs fournie
     * @param colors la liste de couleurs dont la couleur des animaux retournés fait partie
     */
    List<Animal> findByColorIn(List<String> colors);









    /**
     * Renvoie le nombre d’Animaux dont le Sex est égal à la valeur donnée en paramètres
     * @param sex le Sexe que les animaux à dénombrer ont
     */
    @Query("select count(a) from Animal a where a.sex = :sex")
    Integer countBySex(@Param("sex") Sex sex);


    /**
     * Renvoie true si l’animal fourni « appartient » à au moins une personne, false sinon.
     * Solution qui n'utilise pas le mapped by
     */
    @Query("select case when count(p) > 0 then true else false end" +
            " from Person p where :animal member of p.animals ")
    Boolean animalHasOwner(@Param("animal") Animal animal);

    /**
     * Renvoie true si l’animal fourni « appartient » à au moins une personne, false sinon.
     * EN SQL (à corriger : class cast exception quand on utilise cette méthode)
     */
//    @Query(value = "select NOT EXISTS (" +
//            " select * from animal" +
//            " join person_animals on animals_id = animal.id" +
//            " join person on person.id = person_id" +
//            " where animal.id = :animalId" +
//            " )", nativeQuery = true)

    // autre solution possible avec MySQL
    //SELECT IF
    //(
    //	(
    //		SELECT COUNT(*)
    //		FROM person as p
    //		INNER JOIN person_animals as p_a ON  p.id = p_a.person_id
    //		INNER JOIN animal as a ON a.id = p_a.animals_id
    //		INNER JOIN species as s  ON s.id = a.species_id
    //		WHERE s.common_name = 'Chat'
    //	)>0, 'TRUE', 'FALSE'
    //)
    //
    // SELECT IF((SELECT COUNT(*)
    //        FROM person as p
    //        INNER JOIN person_animals as p_a ON  p.id = p_a.person_id
    //        WHERE p_a.animals_id = 1),
    //          "TRUE", "FALSE")

    @Query(value = "select case when count(*) > 0 then 'true' else 'false' end"
            + " from person_animals where animals_id = :animalId",
            nativeQuery = true)
    Boolean animalHasOwnerSql(@Param("animalId") Integer animalId);

}
