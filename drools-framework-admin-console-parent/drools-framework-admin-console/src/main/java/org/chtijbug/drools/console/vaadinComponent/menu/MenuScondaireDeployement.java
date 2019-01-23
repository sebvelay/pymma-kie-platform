package org.chtijbug.drools.console.vaadinComponent.menu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.chtijbug.drools.console.view.AddRuntime;

@StyleSheet("css/accueil.css")
public class MenuScondaireDeployement extends HorizontalLayout {

    private Button addRuntime;

    private Button projectDeploy;

    private Button accueilDeployment;

    public MenuScondaireDeployement(){
        setVisible(false);

        setClassName("menu-secondaire-content");



        accueilDeployment=new Button("Accueil deployement",VaadinIcon.ARCHIVE.create());
        accueilDeployment.setClassName("menu-secondaire-button");
        add(accueilDeployment);
        accueilDeployment.addClickListener(buttonClickEvent -> {
           getUI().get().navigate("AssetUpdate");
        });

        projectDeploy=new Button("Deployable project",VaadinIcon.EJECT.create());
        projectDeploy.setClassName("menu-secondaire-button");
        add(projectDeploy);
        projectDeploy.addClickListener(buttonClickEvent -> {
            getUI().get().navigate("deployment");
        });

        addRuntime=new Button("add runtime", VaadinIcon.PLUS.create());
        addRuntime.setClassName("menu-secondaire-button");
        add(addRuntime);

        Dialog dialog=new Dialog();
        dialog.add(new AddRuntime(dialog));

        addRuntime.addClickListener(buttonClickEvent -> {
           dialog.open();
        });
    }

    public Button getAddRuntime() {
        return addRuntime;
    }

    public void setAddRuntime(Button addRuntime) {
        this.addRuntime = addRuntime;
    }

    public Button getProjectDeploy() {
        return projectDeploy;
    }

    public void setProjectDeploy(Button projectDeploy) {
        this.projectDeploy = projectDeploy;
    }

    public Button getAccueilDeployment() {
        return accueilDeployment;
    }

    public void setAccueilDeployment(Button accueilDeployment) {
        this.accueilDeployment = accueilDeployment;
    }
}
