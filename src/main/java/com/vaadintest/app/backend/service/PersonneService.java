package com.vaadintest.app.backend.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaadintest.app.backend.entities.Personne;
import com.vaadintest.app.backend.repository.PersonneRepository;

@Service
public class PersonneService {
	
	@Autowired
	PersonneRepository personneRepository;
	private static final Logger LOGGER = Logger.getLogger(PersonneService.class.getName());
	
	
	public List<Personne> listPersonne(){
		return personneRepository.findAll();
	}
	
	public List<Personne> listPersonne(String textFiltrer){
		if(textFiltrer==null||textFiltrer.isEmpty()) {
			return personneRepository.findAll();
		}else {
			return personneRepository.search(textFiltrer);
		}
		
	}
	
	public long count() {
		return personneRepository.count();
		}
	
	public void delete(Personne personne) {
		personneRepository.delete(personne);
		}
	
	public void save(Personne personne) {
		if (personne == null) { 
		LOGGER.log(Level.SEVERE,
		"Personne is null. Are you sure you have connected your form to the application?");
		return;
		}
		personneRepository.save(personne);
		}
	
}
