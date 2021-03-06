package org.chtijbug.drools.console.view;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.chtijbug.drools.console.vaadincomponent.componentview.GridRuntime;

@StyleSheet("css/accueil.css")
public class RuntimesView extends VerticalLayout {

    public static final String pageName="Runtimes";


    public RuntimesView(){

        add(new Label("Runtimes stored"));
        add(new GridRuntime());
    }

}
