package org.chtijbug.drools.console.vaadinComponent.menu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.chtijbug.drools.console.vaadinComponent.Squelette.SqueletteComposant;
import org.chtijbug.drools.console.vaadinComponent.leftMenu.Action.ActionLogging;
import org.chtijbug.drools.console.vaadinComponent.leftMenu.Action.RuntimesAction;
import org.chtijbug.drools.console.view.DeploymentView;
import org.chtijbug.drools.console.view.LoggingView;
import org.chtijbug.drools.console.view.RuntimesView;

@StyleSheet("css/accueil.css")
public class MenuSecondaireLogging extends HorizontalLayout {

    private Button logging;

    public MenuSecondaireLogging(SqueletteComposant squeletteComposant){
        setVisible(false);

        setClassName("menu-secondaire-content");


        logging =new Button("Gestion-logging", VaadinIcon.OFFICE.create());
        logging.setClassName("menu-secondaire-button");
        add(logging);
        logging.addClickListener(buttonClickEvent -> {
            if(!isActive(logging)) {
                active(logging);
            }
            LoggingView loggingView=new LoggingView();

            ActionLogging actionLogging=new ActionLogging(loggingView);
            loggingView.setActionLogging(actionLogging);
            squeletteComposant.navigate(loggingView,LoggingView.pageName,actionLogging);
        });
    }

    private boolean isActive(Button button){
        return button.getClassNames().contains("active");
    }
    private void removeActive(Button button) {

        if(button.getClassNames().contains("active")){
            button.getClassNames().remove("active");
        }
    }
    private void active(Button button){
        removeActive(logging);

        button.getClassNames().add("active");
    }

    public Button getLogging() {
        return logging;
    }

    public void setLogging(Button logging) {
        this.logging = logging;
    }
}
