package org.chtijbug.drools.console.vaadinComponent.componentView;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.chtijbug.drools.console.service.ProjectPersistService;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.view.DeploymentView;
import org.chtijbug.drools.proxy.persistence.model.ProjectPersist;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;

import java.util.ArrayList;
import java.util.List;

public class AssociateProjectKie extends VerticalLayout {

    private Label label;

    private Label label2;

    private GridRuntime gridRuntime;

    private Button associer;

    private ProjectPersistService projectPersistService;

    public AssociateProjectKie(DeploymentView deploymentView, Dialog dialog, ProjectPersist projectPersist) {

        projectPersistService = AppContext.getApplicationContext().getBean(ProjectPersistService.class);

        setClassName("creation-runtime-content");

        label = new Label("Define the runtime(s) for the project : " + projectPersist.getProjectName());
        label.setClassName("creation-runtime-title");
        add(label);

        label2 = new Label("This step is essential to be able to execute the rules defined in the project");
        label2.setClassName("creation-runtime-title2");
        add(label2);

        gridRuntime = new GridRuntime(projectPersist);
        add(gridRuntime);

        associer = new Button("Link and Save");
        associer.setEnabled(false);
        associer.setClassName("login-application-connexion");
        add(associer);
        associer.addClickListener(buttonClickEvent -> {
            List<RuntimePersist> lstToSave = new ArrayList<>();
            for (RuntimePersist runtimePersist : gridRuntime.getSelectedItems()) {
                lstToSave.add(runtimePersist);
            }
            if (lstToSave.size()>0) {

                boolean tmp = projectPersistService.associate(projectPersist,lstToSave);

                if (tmp == true) {
                    deploymentView.setDataProvider();
                    dialog.close();
                } else {
                    associer.setEnabled(false);
                    Notification.show("There is already a project of this name on this runtime");
                }
            }
        });

        gridRuntime.addSelectionListener(selectionEvent -> {
            if (selectionEvent.getFirstSelectedItem() != null && selectionEvent.getFirstSelectedItem().isPresent()) {

                associer.setEnabled(true);
            } else {
                associer.setEnabled(false);
            }
        });


    }
}
