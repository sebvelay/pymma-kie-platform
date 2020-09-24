package org.chtijbug.drools.console.vaadincomponent.componentperso;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@StyleSheet("css/composantperso/datePickerPerso.css")
public class DatePickerPerso extends HorizontalLayout {

    DatePicker datepicker;

    public DatePickerPerso (String caption, Icon icon){
        this.setMargin(false);
        this.setSpacing(false);

        setClassName("horizontal-content");


        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.setClassName("content-icon");

        icon.setClassName("datepicker-perso-icon");
        verticalLayout.add(icon);

        datepicker =new DatePicker(caption);
        datepicker.setClassName("horizontal-content");
        this.add(verticalLayout);
        this.add(datepicker);
    }

    public DatePicker getDatepicker() {
        return datepicker;
    }
}
