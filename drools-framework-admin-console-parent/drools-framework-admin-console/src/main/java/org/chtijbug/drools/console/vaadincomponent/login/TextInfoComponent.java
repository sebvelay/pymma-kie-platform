package org.chtijbug.drools.console.vaadincomponent.login;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Label;

@StyleSheet("css/accueil.css")
public class TextInfoComponent extends VerticalLayout {

    private Label title;

    private HorizontalLayout separator;


    public TextInfoComponent(){

        setClassName("login-layout-textInfo");

        title=new Label("Drools kie-platform");
        title.setClassName("login-title");
        add(title);

        separator=new HorizontalLayout();
        separator.setClassName("separator");
        add(separator);

        add(row("Dynamic management of kie-servers"));
        add(row("Facilitates the process drools as a whole"));

    }

    public HorizontalLayout row(String text){

        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setClassName("login-textInfo-layoutContent");

        Checkbox checkbox=new Checkbox();
        checkbox.setValue(true);
        checkbox.setEnabled(false);
        horizontalLayout.add(checkbox);
        checkbox.setClassName("login-textInfo-button");

        Label label=new Label(text);
        horizontalLayout.add(label);
        label.setClassName("login-textInfo-paragraph");

        return horizontalLayout;
    }
}
