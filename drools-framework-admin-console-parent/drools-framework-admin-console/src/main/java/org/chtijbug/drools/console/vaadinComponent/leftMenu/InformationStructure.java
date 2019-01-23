package org.chtijbug.drools.console.vaadinComponent.leftMenu;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.server.InputStreamFactory;
import com.vaadin.flow.server.StreamResource;

import java.io.InputStream;

@StyleSheet("css/accueil.css")
public class InformationStructure extends VerticalLayout {

    private Label nomPage;

    private Label numberProject;

    private Label numberKieWb;

    private Label numberKieServer;

    private Image logo;

    private String strProject="Number of projects : ";

    private String strKieWb="Number of kie-workbench : ";

    private String strKieServer="Number of Kie-Server : ";

    public InformationStructure(){

        setClassName("leftMenu-global-infoStructure-content");

        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.setClassName("leftMenu-global-infoStrcutre-contentTitre");
        add(verticalLayout);

        nomPage=new Label("Accueil");
        nomPage.setClassName("leftMenu-global-inforStructure-titre");
        verticalLayout.add(nomPage);

        InputStreamFactory inputStreamFactory=new InputStreamFactory() {
            @Override
            public InputStream createInputStream() {
                return getClass().getResourceAsStream("/images/book.png");
            }
        };

        StreamResource str=new StreamResource("logo",inputStreamFactory);

        logo=new Image(str,"");
        logo.setClassName("leftMenu-global-inforStructure-logo");
        add(logo);

        VerticalLayout verticalLayout1=new VerticalLayout();
        verticalLayout1.setClassName("leftMenu-global-inforStructure-content-label");
        add(verticalLayout1);

        numberKieServer=new Label(strKieServer+"0");
        numberKieServer.setClassName("leftMenu-global-inforStructure-label");
        verticalLayout1.add(numberKieServer);

        numberKieWb=new Label(strKieWb+"0");
        numberKieWb.setClassName("leftMenu-global-inforStructure-label");
        verticalLayout1.add(numberKieWb);

        numberProject=new Label(strProject+"0");
        numberProject.setClassName("leftMenu-global-inforStructure-label");
        verticalLayout1.add(numberProject);
    }

    public void actualiseKieWb(Integer numberOfKieWb){
        numberKieWb.setText(strKieWb+numberOfKieWb);
    }
    public void actualiseKieServer(Integer numberOfKieServer){
        numberKieServer.setText(strKieServer+numberOfKieServer);
    }
    public void actualiseProject(Integer numberOfProject){
        numberProject.setText(strProject+numberOfProject);
    }

    public Label getNomPage() {
        return nomPage;
    }

    public void setNomPage(Label nomPage) {
        this.nomPage = nomPage;
    }

    public Label getNumberProject() {
        return numberProject;
    }

    public Label getNumberKieWb() {
        return numberKieWb;
    }

    public Label getNumberKieServer() {
        return numberKieServer;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }
}
