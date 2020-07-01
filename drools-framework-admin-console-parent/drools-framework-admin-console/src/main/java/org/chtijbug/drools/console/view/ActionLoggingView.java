package org.chtijbug.drools.console.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.chtijbug.drools.console.vaadinComponent.ComponentPerso.DialogPerso;
import org.chtijbug.drools.console.vaadinComponent.componentView.GridActionLogging;
import org.chtijbug.drools.proxy.persistence.model.BusinessTransactionPersistence;


public class ActionLoggingView extends VerticalLayout {

    private Label title;

    private GridActionLogging gridActionLogging;



    public ActionLoggingView(BusinessTransactionPersistence businessTransactionPersistence, DialogPerso dialogPerso){

        dialogPerso.getClose().setVisible(false);
        //Text text = new Text("Sessino Context ");
        //dialogPerso.getBar().add(text);


        title=new Label("TransactionID : "+businessTransactionPersistence.getId());
        title.setClassName("creation-runtime-title");

        add(title);


        gridActionLogging=new GridActionLogging(businessTransactionPersistence.getId());
        add(gridActionLogging);




    }

}
