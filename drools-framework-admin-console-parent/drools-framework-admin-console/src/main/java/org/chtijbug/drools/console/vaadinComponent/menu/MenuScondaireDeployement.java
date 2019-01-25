package org.chtijbug.drools.console.vaadinComponent.menu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.chtijbug.drools.console.vaadinComponent.Squelette.SqueletteComposant;
import org.chtijbug.drools.console.vaadinComponent.componentView.AddRuntime;
import org.chtijbug.drools.console.vaadinComponent.leftMenu.Action.DeploymentAction;
import org.chtijbug.drools.console.view.DeploymentView;

@StyleSheet("css/accueil.css")
public class MenuScondaireDeployement extends HorizontalLayout {

    private Button addRuntime;

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
            squeletteComposant.navigate(deploymentView,DeploymentView.pageName,deploymentAction);
        });

        addRuntime=new Button("add runtime", VaadinIcon.PLUS.create());
        addRuntime.setClassName("menu-secondaire-button");
        add(addRuntime);

        Dialog dialog=new Dialog();
        dialog.add(new AddRuntime(dialog,squeletteComposant));

        addRuntime.addClickListener(buttonClickEvent -> {
           dialog.open();
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
        removeActive(addRuntime);
        removeActive(deployment);

        button.getClassNames().add("active");
    }

    public Button getAddRuntime() {
        return addRuntime;
    }

    public void setAddRuntime(Button addRuntime) {
        this.addRuntime = addRuntime;
    }

    public Button getDeployment() {
        return deployment;
    }

    public void setDeployment(Button deployment) {
        this.deployment = deployment;
    }

}
