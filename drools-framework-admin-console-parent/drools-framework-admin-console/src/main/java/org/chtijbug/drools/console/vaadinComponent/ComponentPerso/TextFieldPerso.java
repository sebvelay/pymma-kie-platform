package org.chtijbug.drools.console.vaadinComponent.ComponentPerso;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

@StyleSheet("css/composantperso/textFieldPerso.css")
public class TextFieldPerso extends HorizontalLayout {

    TextField textField;

    public TextFieldPerso (String caption,String placeHolder, Icon icon){
        this.setMargin(false);
        this.setSpacing(false);

        setClassName("content-perso-field");

        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.setClassName("content-icon");

        icon.setClassName("passwordField-perso-icon");
        verticalLayout.add(icon);

        textField=new TextField(caption,placeHolder);
        textField.setClassName("horizontal-content");
        textField.setRequired(true);
        this.add(verticalLayout);
        this.add(textField);
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }
}
