package com.vaadintest.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.vaadintest.app.backend.entities.Personne;
import com.vaadintest.app.backend.repository.PersonneRepository;


@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {
	@Autowired
	PersonneRepository personneRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
		Personne personne=new Personne("Youssouf","Alioum","Maroua");
		Personne personne2=new Personne("Moktar","Moussa","Garoua");
		Personne personne3=new Personne("Moussa","bello","Yaoundé");
		Personne personne4=new Personne("Jean","françois","Douala");
		Personne personne5=new Personne("Pierre","françois","Bertoua");
		personneRepository.save(personne);
		personneRepository.save(personne2);
		personneRepository.save(personne3);
		personneRepository.save(personne4);
		personneRepository.save(personne5);
		
	}

}
