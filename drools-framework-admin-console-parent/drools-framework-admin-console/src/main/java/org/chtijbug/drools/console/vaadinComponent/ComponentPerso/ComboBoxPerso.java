package org.chtijbug.drools.console.vaadinComponent.ComponentPerso;


import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@StyleSheet("css/composantperso/textFieldPerso.css")
public class ComboBoxPerso<T> extends HorizontalLayout {


    ComboBox<T> comboBox;

    public ComboBoxPerso (String caption, Icon icon){
        this.setMargin(false);
        this.setSpacing(false);

        setClassName("horizontal-content");


        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.setClassName("content-icon");

        icon.setClassName("passwordField-perso-icon");
        verticalLayout.add(icon);

        comboBox =new ComboBox<>(caption);
        comboBox.setClassName("horizontal-content");
        this.add(verticalLayout);
        this.add(comboBox);
    }

    public ComboBox<T> getComboBox() {
        return comboBox;
    }

    public void setComboBox(ComboBox<T> comboBox) {
        this.comboBox = comboBox;
    }

}