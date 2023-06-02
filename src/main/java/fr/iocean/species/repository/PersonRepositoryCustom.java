package fr.iocean.species.repository;

import fr.iocean.species.model.Person;

import java.util.List;

public interface PersonRepositoryCustom {

    int deletePersonsWithoutAnimal();

    void insertRandomPersons(int numberToCreate);

    List<Person> testCriterias(String firstname, String lastname, Integer age);
}
