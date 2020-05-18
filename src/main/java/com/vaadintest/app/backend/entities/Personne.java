package com.vaadintest.app.backend.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.Entity
public class Personne {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//@Column(name = "nom")
	private String nom;
	//@Column(name = "prenom")
	private String prenom;
	//@Column(name = "lieu_naissance")
	private String lieuNaissance;
	
	public Personne() {
		super();
	}
	
	public Personne(String nom, String prenom, String lieuNaissance) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.lieuNaissance = lieuNaissance;
	}
	
	public Personne(Long id, String nom, String prenom, String lieuNaissance) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.lieuNaissance = lieuNaissance;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", lieuNaissance=" + lieuNaissance + "]";
	}

	
}
