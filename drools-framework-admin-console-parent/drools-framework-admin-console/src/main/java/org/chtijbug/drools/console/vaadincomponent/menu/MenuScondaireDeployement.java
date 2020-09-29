package org.chtijbug.drools.console.vaadincomponent.menu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.chtijbug.drools.console.vaadincomponent.Squelette.SqueletteComposant;
import org.chtijbug.drools.console.vaadincomponent.leftMenu.Action.DeploymentAction;
import org.chtijbug.drools.console.view.DeploymentView;

@StyleSheet("css/accueil.css")
public class MenuScondaireDeployement extends HorizontalLayout {

    private Button deployment;

    public MenuScondaireDeployement(SqueletteComposant squeletteComposant){
        setVisible(false);

        setClassName("menu-secondaire-content");


        deployment =new Button("Deployment",VaadinIcon.EJECT.create());
        deployment.setClassName("menu-secondaire-button");
        add(deployment);
        deployment.addClickListener(buttonClickEvent -> {
            if(!isActive(deployment)) {
                active(deployment);
            }
            DeploymentView deploymentView=new DeploymentView(squeletteComposant);

            DeploymentAction deploymentAction=new DeploymentAction(squeletteComposant,deploymentView);
            deploymentView.setDeploymentAction(deploymentAction);
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
        removeActive(deployment);

        button.getClassNames().add("active");
    }

    public Button getDeployment() {
        return deployment;
    }

    public void setDeployment(Button deployment) {
        this.deployment = deployment;
    }

}
