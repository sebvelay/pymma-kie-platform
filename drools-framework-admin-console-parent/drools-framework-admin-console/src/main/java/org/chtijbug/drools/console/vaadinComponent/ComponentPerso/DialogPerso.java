package org.chtijbug.drools.console.vaadinComponent.ComponentPerso;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@StyleSheet("css/composantperso/dialog.css")
public class DialogPerso extends Dialog {

    public DialogPerso(){
        HorizontalLayout bar = new HorizontalLayout();
        Button close = new Button();
        close.setIcon(VaadinIcon.CLOSE.create());
        close.addClassName("DialogClose");
        bar.addClassName("DialogBar");
        bar.add(close);
        add(bar);
        setCloseOnEsc(false);
        setCloseOnOutsideClick(false);

        close.addClickListener(buttonClickEvent -> close());
    }
}
