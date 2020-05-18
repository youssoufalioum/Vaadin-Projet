package com.vaadintest.app.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightCondition;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

@CssImport("./styles/shared-styles.css")
@CssImport("./styles/bootstrap.min.css")
public class MainLayout extends AppLayout {

	public MainLayout() {
		createHeader();
		createDrawer();
	}

	private void createDrawer() {
		RouterLink listLink = new RouterLink("Liste des personnes",ListView.class);
		listLink.setHighlightCondition(HighlightConditions.sameLocation());
		addToDrawer(new VerticalLayout(listLink));
	}

	private void createHeader() {
		//H1 logo = new H1("Logo");
		Image logo = new Image("images/pic.jpg", "logo");
		logo.setWidth("50px");
		logo.addClassName("logo");
		
		DrawerToggle drawerToggle=new DrawerToggle();
		drawerToggle.addClassName("menu");
		HorizontalLayout header = new HorizontalLayout(drawerToggle,logo);
		header.addClassName("header");
		header.setWidth("100%");
		header.setDefaultVerticalComponentAlignment(Alignment.CENTER);
		addToNavbar(header);
	}

}
