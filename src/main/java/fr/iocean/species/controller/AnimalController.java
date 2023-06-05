package fr.iocean.species.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.iocean.species.enums.Sex;
import fr.iocean.species.model.Animal;
import fr.iocean.species.model.Species;
import fr.iocean.species.repository.AnimalRepository;
import fr.iocean.species.repository.SpeciesRepository;

@Controller
public class AnimalController {

	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private SpeciesRepository speciesRepository;
	
	@GetMapping("animal")
	public String getListAnimal(Model model) {
		// 1er etapes recup les spacies via le reop
		List<Animal> animalList = animalRepository.findAll();
		
		//2eme mettre la list dans le model
		model.addAttribute("animalList", animalList);
		
		// 3eme retourne la vue
		return "animal/list_animal";
	}
	
	@GetMapping("animal/{id}")
	public String getDetailAnimal(@PathVariable("id") Integer id, Model model) {
		Optional<Animal> animalOpt = animalRepository.findById(id);
		if(animalOpt.isEmpty()) {
			// pas de species avec l'id renseigné
			return "animal/errorAnimal";
		}
		model.addAttribute("animalItem", animalOpt.get());
		model.addAttribute("speciesList", 
				speciesRepository.findAll(Sort.by(Sort.Direction.ASC, "commonName"))
		);
		model.addAttribute("enumSex", Sex.values());
		// 3eme retourne la vue
		return "animal/detail_animal";
	}
	
	@GetMapping("animal/create")
	public String getCreateAnimal(Model model) {
		model.addAttribute("newAnimal", new Animal());
		// 3eme retourne la vue
		return "animal/create_animal";
	}
}
