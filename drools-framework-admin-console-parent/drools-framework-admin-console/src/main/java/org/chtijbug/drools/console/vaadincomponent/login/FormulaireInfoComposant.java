package org.chtijbug.drools.console.vaadincomponent.login;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Label;

@StyleSheet("css/accueil.css")
public class FormulaireInfoComposant extends VerticalLayout {

    public Label title;

    public Label paragraphe;

    public FormulaireInfoComposant(){

        setClassName("login-application-layout-FormulaireInfo");

        title=new Label("How to connect?");
        title.setClassName("login-application-layout-FormulaireInfo-Title");
        add(title);

        paragraphe=new Label("To access this application, make sure you already have an account on the kie-workbench");
        paragraphe.setClassName("login-application-layout-FormulaireInfo-Paragraphe");
        add(paragraphe);
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }

    public Label getParagraphe() {
        return paragraphe;
    }

    public void setParagraphe(Label paragraphe) {
        this.paragraphe = paragraphe;
    }
}
