package org.chtijbug.drools.console.vaadincomponent.menu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.chtijbug.drools.console.vaadincomponent.Squelette.SqueletteComposant;
import org.chtijbug.drools.console.vaadincomponent.leftMenu.Action.RuntimesAction;
import org.chtijbug.drools.console.view.DeploymentView;
import org.chtijbug.drools.console.view.RuntimesView;

@StyleSheet("css/accueil.css")
public class MenuSecondaireRuntime extends HorizontalLayout {

    private Button gestionRuntime;

    public MenuSecondaireRuntime(SqueletteComposant squeletteComposant){
        setVisible(false);

        setClassName("menu-secondaire-content");


        gestionRuntime =new Button("Gestion", VaadinIcon.OUTBOX.create());
        gestionRuntime.setClassName("menu-secondaire-button");
        add(gestionRuntime);
        gestionRuntime.addClickListener(buttonClickEvent -> {
            if(!isActive(gestionRuntime)) {
                active(gestionRuntime);
            }
            RuntimesView deploymentView=new RuntimesView();
            RuntimesAction deploymentAction=new RuntimesAction(squeletteComposant);
            squeletteComposant.navigate(deploymentView,DeploymentView.PAGE_NAME,deploymentAction);
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
        removeActive(gestionRuntime);

        button.getClassNames().add("active");
    }

    public Button getGestionRuntime() {
        return gestionRuntime;
    }

    public void setGestionRuntime(Button gestionRuntime) {
        this.gestionRuntime = gestionRuntime;
    }
}
