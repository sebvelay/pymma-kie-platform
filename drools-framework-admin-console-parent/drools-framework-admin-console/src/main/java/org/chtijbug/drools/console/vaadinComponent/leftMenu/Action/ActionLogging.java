package org.chtijbug.drools.console.vaadinComponent.leftMenu.Action;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.chtijbug.drools.console.vaadinComponent.ComponentPerso.DialogPerso;
import org.chtijbug.drools.console.vaadinComponent.Squelette.SqueletteComposant;
import org.chtijbug.drools.console.vaadinComponent.componentView.AddRuntime;
import org.chtijbug.drools.console.vaadinComponent.componentView.DefineProject;
import org.chtijbug.drools.console.vaadinComponent.componentView.GridActionLogging;
import org.chtijbug.drools.console.vaadinComponent.componentView.GridLogging;
import org.chtijbug.drools.console.view.LoggingView;
import org.chtijbug.drools.indexer.persistence.model.BusinessTransactionPersistence;

public class ActionLogging extends VerticalLayout {

    private Button viewAction;


    public ActionLogging(LoggingView loggingView){

        setClassName("leftMenu-global-action");


        viewAction =new Button("View Details", VaadinIcon.INFO.create());
        viewAction.setClassName("leftMenu-global-button");
        viewAction.setEnabled(false);

        add(viewAction);

        viewAction.addClickListener(buttonClickEvent -> {
            active(viewAction);

            BusinessTransactionPersistence b=loggingView.getGridLogging().getSelectedItems().stream().findFirst().get();

            if(b!=null) {
                DialogPerso dialog = new DialogPerso();
                dialog.add(new Label(b.getTransactionId()));
                dialog.add(new GridActionLogging(b.getId()));
                dialog.open();
            }

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
        removeActive(viewAction);
        button.getClassNames().add("active");
    }

    public Button getViewAction() {
        return viewAction;
    }

    public void setViewAction(Button viewAction) {
        this.viewAction = viewAction;
    }
}
