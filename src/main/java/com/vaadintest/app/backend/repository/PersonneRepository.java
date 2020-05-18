package com.vaadintest.app.backend.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vaadintest.app.backend.entities.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
	
	@Query("select p from Personne p " +
			"where lower(p.nom) like lower(concat('%', :searchTerm, '%')) " +
			"or lower(p.prenom) like lower(concat('%', :searchTerm, '%'))")
		List<Personne> search(@Param("searchTerm") String searchTerm);
}
