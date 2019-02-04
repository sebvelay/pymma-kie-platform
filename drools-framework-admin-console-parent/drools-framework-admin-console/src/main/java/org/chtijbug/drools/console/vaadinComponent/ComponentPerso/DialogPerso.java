package org.chtijbug.drools.console.vaadinComponent.ComponentPerso;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@StyleSheet("css/composantperso/dialog.css")
public class DialogPerso extends Dialog {

    private HorizontalLayout bar;

    private Button close;

    public DialogPerso(){
        bar = new HorizontalLayout();
        close = new Button();
        close.setIcon(VaadinIcon.CLOSE.create());
        close.addClassName("DialogClose");
        bar.addClassName("DialogBar");
        bar.add(close);
        add(bar);

        close.addClickListener(buttonClickEvent -> close());
    }

    public HorizontalLayout getBar() {
        return bar;
    }

    public void setBar(HorizontalLayout bar) {
        this.bar = bar;
    }

    public Button getClose() {
        return close;
    }

    public void setClose(Button close) {
        this.close = close;
    }
}
