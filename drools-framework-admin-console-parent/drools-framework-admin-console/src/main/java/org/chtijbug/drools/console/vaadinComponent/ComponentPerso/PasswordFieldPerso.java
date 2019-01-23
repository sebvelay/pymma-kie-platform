package org.chtijbug.drools.console.vaadinComponent.ComponentPerso;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;

@StyleSheet("css/composantperso/textFieldPerso.css")
public class PasswordFieldPerso extends HorizontalLayout {

    private PasswordField passwordField;

    public PasswordFieldPerso (String caption, Icon icon){
        this.setMargin(false);
        this.setSpacing(false);


        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.setClassName("content-icon");

        icon.setClassName("passwordField-perso-icon");
        verticalLayout.add(icon);

        passwordField =new PasswordField(caption);
        passwordField.setClassName("horizontal-content");
        this.add(verticalLayout);
        this.add(passwordField);
        passwordField.setRequired(true);
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(PasswordField passwordField) {
        this.passwordField = passwordField;
    }
}
