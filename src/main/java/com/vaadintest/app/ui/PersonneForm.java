package com.vaadintest.app.ui;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.charts.model.style.Color;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import com.vaadintest.app.backend.entities.Personne;

public class PersonneForm extends FormLayout {
	
	TextField nom = new TextField("Nom");
	TextField prenom = new TextField("Prenom");
	TextField lieuNaissance = new TextField("Lieu de Naissance");
	Binder<Personne> binder = new BeanValidationBinder<>(Personne.class);
	
	Button save = new Button("Enregistrer");
	Button delete = new Button("Supprimer");
	Button close = new Button("Fermer");
	
	public PersonneForm() {
		addClassName("personne-form");
		binder.bindInstanceFields(this);
		nom.setPlaceholder("Saisir votre nom");
		prenom.setPlaceholder("Saisir votre prénom");
		lieuNaissance.setPlaceholder("Saisir votre lieu de Naissance");
		add(nom,prenom,lieuNaissance,createButtonsLayout());
	}
	
	public void setPersonne(Personne personne) {
		binder.setBean(personne);
	}

	private HorizontalLayout createButtonsLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		save.addClassName("btn-success");
		delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		close.addClassName("btnFermer");
		
		save.addClickShortcut(Key.ENTER);
		close.addClickShortcut(Key.ESCAPE);
		
		save.addClickListener(event -> validateAndSave());
		delete.addClickListener(event -> fireEvent(new DeleteEvent(this, binder.getBean())));
		close.addClickListener(event -> fireEvent(new CloseEvent(this)));
		binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));
		
		return new HorizontalLayout(save,delete,close);
	}
	
	
	
	
	
	private void validateAndSave() {
		if(binder.isValid()) {
			fireEvent(new SaveEvent(this, binder.getBean()));
		}
	}





	public static abstract class PersonneFormEvent extends ComponentEvent<PersonneForm> {
		private Personne personne;
		
		public PersonneFormEvent(PersonneForm source, Personne personne) {
			super(source, false);
			this.personne=personne;
		}
		
		public Personne getPersonne() {
			return personne;
		}
	}
	
	
	public static class SaveEvent extends PersonneFormEvent {
		SaveEvent(PersonneForm source, Personne personne) {
		super(source, personne);
		}
		}
	public static class DeleteEvent extends PersonneFormEvent {
		public DeleteEvent(PersonneForm source, Personne personne) {
			super(source, personne);
		}	
	}
	
	public static class CloseEvent extends PersonneFormEvent {

		public CloseEvent(PersonneForm source) {
			super(source, null);
		}	
	}
	
	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
	

}
