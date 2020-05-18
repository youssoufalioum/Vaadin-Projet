package com.vaadintest.app.ui;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadintest.app.backend.entities.Personne;
import com.vaadintest.app.backend.service.BGSound;
import com.vaadintest.app.backend.service.PersonneService;
import com.vaadintest.app.backend.service.Video;

@Route(value="",layout=MainLayout.class)
@PageTitle("Liste des personne par ville")
public class ListView extends VerticalLayout {

	Grid<Personne> grid=new Grid<>(Personne.class);
	private PersonneService personneService;
	TextField filtre=new TextField();
	private PersonneForm personneForm;
	private Image image;
	private Video video;
	//private BGSound bgSound;
	MemoryBuffer buffer = new MemoryBuffer();
	Upload upload;
	
    public ListView(PersonneService personneService) {
    	this.personneService=personneService;
    	addClassName("list-view");
    	setSizeFull();
    	configureGrid();
    	
    	personneForm = new PersonneForm();
    	upload = new Upload(buffer);
    	upload.setUploadButton(new Button("Télécharger"));
    	upload.setDropLabel(new Label("Deposer fichier ici"));
    	upload.setHeight("200px");
    	upload.setWidth("500px");
    	upload.addClassNames("text-center");
    	upload.setMaxFiles(1);
    	upload.setAcceptedFileTypes(".jpeg,.pdf");
    	
    	video = new Video("videos/ADA EHI - Cheta - The Official Video.webm");
    	video.setWidth("500px");
    	
    	//bgSound = new BGSound("audio/Dadju---Ma-Fuzzy-Style-(Exclu-2018).mp3");
    	
    	personneForm.addListener(PersonneForm.SaveEvent.class,this::savePersonne);
    	personneForm.addListener(PersonneForm.DeleteEvent.class, this::deletePersonne);
    	personneForm.addListener(PersonneForm.CloseEvent.class, e-> closeEditor());
    	HorizontalLayout videoAndUpload = new HorizontalLayout(video,upload);
    	Div content = new Div(grid,personneForm);
    	content.addClassName("content");
    	content.setSizeFull();
    	add(getToolbar(),content,popUp(),videoAndUpload);
    	updateList();
    	closeEditor();
    	
    }
    

	private HorizontalLayout popUp() {
    	image = new Image("images/pic.jpg", "image");
    	image.setWidth("200px");
    	Button btnpopup = new Button("Pop Up",click->displayPopUp());
    	btnpopup.addClassName("btn-success");
    	HorizontalLayout popUpLayout = new HorizontalLayout(image,btnpopup);
    	return popUpLayout;
	}


	private void displayPopUp() {
		Button btnOk = new Button("OK");
		btnOk.addClassName("btn-primary");
		Dialog modal = new Dialog(btnOk);
		modal.setWidth("500px");
		modal.setHeight("450px");
		modal.open();	
	}


	private HorizontalLayout getToolbar() {
		
		filtre.setPlaceholder("Recherche");
		filtre.setClearButtonVisible(true);
		filtre.setValueChangeMode(ValueChangeMode.LAZY);
		filtre.addValueChangeListener(e->updateList());
		Button addPersonneBtn = new Button("Ajouter", click->addPersonne());
		addPersonneBtn.addClassName("btn-success");
		HorizontalLayout toolbar = new HorizontalLayout(filtre,addPersonneBtn);
		toolbar.addClassName("toolbar");
		return toolbar;
		}

    
    private void addPersonne() {
		grid.asSingleSelect().clear();
		editPersonne(new Personne());
	}


	private void savePersonne(PersonneForm.SaveEvent evt) {
    	personneService.save(evt.getPersonne());
    	updateList();
    	closeEditor();
    }
    
    private void deletePersonne(PersonneForm.DeleteEvent evt) {
    	personneService.delete(evt.getPersonne());
    	updateList();
    	closeEditor();
    }

	private void closeEditor() {
		personneForm.setPersonne(null);
		personneForm.setVisible(false);
		removeClassName("editing");
	}

	
	private void updateList() {
		
		grid.setItems(personneService.listPersonne(filtre.getValue()));
	}

	private void configureGrid() {
		grid.addClassName("personne-grid");
		grid.setSizeFull();
		grid.setHeight("400px");
		//grid.setHeightByRows(true);
		grid.setColumns("nom","prenom","lieuNaissance");
		grid.asSingleSelect().addValueChangeListener(evt->editPersonne(evt.getValue()));
	}

	private void editPersonne(Personne personne) {
		if(personne==null) {
			closeEditor();
		}else {
			personneForm.setPersonne(personne);
			personneForm.setVisible(true);
			addClassName("editing");
		}
	}

}
