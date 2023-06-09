package fr.iocean.species.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.iocean.species.model.Person;
import fr.iocean.species.model.Species;
import fr.iocean.species.repository.AnimalRepository;
import fr.iocean.species.repository.PersonRepository;
import jakarta.validation.Valid;

@Controller
public class PersonController {
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@GetMapping("person")
	public String getListPerson(Model model) {
		// 1er etapes recup les spacies via le reop
		List<Person> personList = personRepository.findAll();
		
		//2eme mettre la list dans le model
		model.addAttribute("personList", personList);
		
		// 3eme retourne la vue
		return "person/list_person";
	}
	
	@GetMapping("person/{id}")
	public String getDetailPerson(@PathVariable("id") Integer id, Model model) {
		Optional<Person> personOpt = personRepository.findById(id);
		if(personOpt.isEmpty()) {
			// pas de person avec l'id renseigné
			return "/error";
		}
		model.addAttribute("person", personOpt.get());
		model.addAttribute("animalList", 
				animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
		);
		// 3eme retourne la vue
		return "person/detail_person";
	}
	
	@GetMapping("person/create")
	public String getCreatePerson(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("animalList", 
				animalRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
		
		// 3eme retourne la vue
		return "person/create_person";
	}
	
	// pour generer directement des personnes en aleatoire
	@GetMapping("person/generate")
	public String generateRandomPerson(@RequestParam("nb") Integer nbToCreate) {
		personRepository.addPerson(nbToCreate);
		return "redirect:/person";
	}
	
	@PostMapping("person")
	public String createOrUpdate(@Valid Person person, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("animalList", animalRepository.findAll());
			if(person.getId() != null) {
				return "person/detail_person";
			}
			return "person/create_person";
		}
		personRepository.save(person);
		return "redirect:/person";
	}

	@GetMapping("/person/delete/{id}")
	public String delete(@PathVariable("id") Integer personId) {
		personRepository.deleteById(personId);
		return "redirect:/person";
	}
	
}
