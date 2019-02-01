package org.chtijbug.drools.console.vaadinComponent.leftMenu.Action;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.chtijbug.drools.console.service.IndexerService;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.vaadinComponent.ComponentPerso.DialogPerso;
import org.chtijbug.drools.console.vaadinComponent.ComponentPerso.TextFieldPerso;
import org.chtijbug.drools.console.vaadinComponent.Squelette.SqueletteComposant;
import org.chtijbug.drools.console.vaadinComponent.componentView.AddRuntime;
import org.chtijbug.drools.console.vaadinComponent.componentView.DefineProject;
import org.chtijbug.drools.console.vaadinComponent.componentView.GridActionLogging;
import org.chtijbug.drools.console.vaadinComponent.componentView.GridLogging;
import org.chtijbug.drools.console.view.LoggingView;
import org.chtijbug.drools.indexer.persistence.model.BusinessTransactionPersistence;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class ActionLogging extends VerticalLayout {

    private Button viewAction;

    private TextFieldPerso transactionIdSearch;

    private IndexerService indexerService;

    public ActionLogging(LoggingView loggingView){

        indexerService= AppContext.getApplicationContext().getBean(IndexerService.class);

        setClassName("leftMenu-global-action");

        transactionIdSearch=new TextFieldPerso("Search by TransactionId","",VaadinIcon.SEARCH.create());
        transactionIdSearch.getTextField().setValueChangeMode(ValueChangeMode.EAGER);
        transactionIdSearch.getTextField().addValueChangeListener(textFieldStringComponentValueChangeEvent -> {

            try {

                if (textFieldStringComponentValueChangeEvent.getValue().isEmpty()) {
                    loggingView.getTitle().setText("Logging : ");
                    loggingView.getGridLogging().setDataProvider(indexerService.getBusinessTransactionPersistenceRepository().findAll(new PageRequest(0, 100)).getContent());
                } else {
                    List<BusinessTransactionPersistence> b = indexerService.getBusinessTransactionPersistenceRepository().findAllByTransactionId(textFieldStringComponentValueChangeEvent.getValue(), new PageRequest(0, 100));

                    if (b != null) {
                        loggingView.getTitle().setText("Logging : " + textFieldStringComponentValueChangeEvent.getValue());
                        loggingView.getGridLogging().setDataProvider(b);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                transactionIdSearch.getTextField().setValue("");
                Notification.show("Valeur invalide");
            }
        });
        add(transactionIdSearch);

        viewAction =new Button("View Details", VaadinIcon.INFO.create());
        viewAction.setClassName("leftMenu-global-button");
        viewAction.setEnabled(false);

        add(viewAction);

        viewAction.addClickListener(buttonClickEvent -> {
            active(viewAction);

            BusinessTransactionPersistence b=loggingView.getGridLogging().getSelectedItems().stream().findFirst().get();

            if(b!=null) {
                DialogPerso dialog = new DialogPerso();
                dialog.add(new Label("Transaction Id : "+b.getTransactionId()));
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
