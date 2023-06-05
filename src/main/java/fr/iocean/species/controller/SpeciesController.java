package fr.iocean.species.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.iocean.species.model.Species;
import fr.iocean.species.repository.SpeciesRepository;

@Controller
public class SpeciesController {
	@Autowired
	private SpeciesRepository speciesRepository;
	
	@GetMapping("species")
	public String getListSpecies(Model model) {
		// 1er etapes recup les spacies via le reop
		List<Species> speciesList = speciesRepository.findAll();
		
		//2eme mettre la list dans le model
		model.addAttribute("speciesList", speciesList);
		
		// 3eme retourne la vue
		return "species/list_species";
	}
	
	@GetMapping("species/{id}")
	public String getDetailSpecies(@PathVariable("id") Integer id, Model model) {
		Optional<Species> speciesOpt = speciesRepository.findById(id);
		if(speciesOpt.isEmpty()) {
			// pas de species avec l'id renseigné
			return "species/error";
		}
		model.addAttribute("speciesItem", speciesOpt.get());
		// 3eme retourne la vue
		return "species/detail_species";
	}
	
	@GetMapping("species/create")
	public String getCreateSpecies(Model model) {
		model.addAttribute("newSpecies", new Species());
		// 3eme retourne la vue
		return "species/create_species";
	}
}
