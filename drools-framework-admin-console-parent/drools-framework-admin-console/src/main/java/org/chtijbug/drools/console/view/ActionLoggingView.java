package org.chtijbug.drools.console.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import org.chtijbug.drools.console.vaadinComponent.ComponentPerso.DialogPerso;
import org.chtijbug.drools.console.vaadinComponent.componentView.GridActionLogging;
import org.chtijbug.drools.logging.Fact;
import org.chtijbug.drools.proxy.persistence.model.BusinessTransactionAction;
import org.chtijbug.drools.proxy.persistence.model.BusinessTransactionPersistence;


public class ActionLoggingView extends VerticalLayout {

    private Label title;

    private GridActionLogging gridActionLogging;

    private Button inputData;

    private Button realFact;

    private Button whenFact;

    private Button thenFact;

    public ActionLoggingView(BusinessTransactionPersistence businessTransactionPersistence, DialogPerso dialogPerso){

        dialogPerso.getClose().setVisible(false);

        inputData=new Button("View InputData");
        inputData.setClassName("menu-button");
        inputData.setEnabled(false);


        dialogPerso.getBar().add(inputData);

        realFact=new Button("View RealFact");
        realFact.setClassName("menu-button");
        realFact.setEnabled(false);

        dialogPerso.getBar().add(realFact);

        whenFact=new Button("View WhenFact");
        whenFact.setClassName("menu-button");
        whenFact.setEnabled(false);

        dialogPerso.getBar().add(whenFact);

        thenFact=new Button("View ThenFact");
        thenFact.setClassName("menu-button");
        thenFact.setEnabled(false);

        dialogPerso.getBar().add(thenFact);

        title=new Label("TransactionID : "+businessTransactionPersistence.getId());
        title.setClassName("creation-runtime-title");

        add(title);


        gridActionLogging=new GridActionLogging(businessTransactionPersistence.getId());
        add(gridActionLogging);

        gridActionLogging.addSelectionListener(selectionEvent -> {

            BusinessTransactionAction b=gridActionLogging.getSelectedItems().stream().findFirst().get();

            if(b!=null){
                if(b.getInputData()!=null&&b.getInputData().getRealFact()!=null){
                    inputData.setEnabled(true);
                }else {
                    inputData.setEnabled(false);
                }
                if(b.getFact()!=null&&b.getFact().getRealFact()!=null){
                    realFact.setEnabled(true);
                }else {
                    realFact.setEnabled(false);
                }
                if(b.getRuleExecution()!=null&&b.getRuleExecution().getThenFacts()!=null&&b.getRuleExecution().getThenFacts().size()>0){
                    thenFact.setEnabled(true);
                }else {
                    thenFact.setEnabled(false);
                }
                if(b.getRuleExecution()!=null&&b.getRuleExecution().getWhenFacts()!=null&&b.getRuleExecution().getWhenFacts().size()>0){
                    whenFact.setEnabled(true);
                }else {
                    whenFact.setEnabled(false);
                }

            }

        });

        inputData.addClickListener(buttonClickEvent -> {
            DialogPerso dialogPerso1=new DialogPerso();

            BusinessTransactionAction b=gridActionLogging.getSelectedItems().stream().findFirst().get();

            if(b!=null&&b.getInputData()!=null&&b.getInputData().getRealFact()!=null){

                VerticalLayout verticalLayout=new VerticalLayout();
                Label label=new Label(b.getEventType().name()+" - "+(b.getRuleExecution()!=null&&b.getRuleExecution().getRuleName()!=null?b.getRuleExecution().getRuleName():""));
                label.setClassName("creation-runtime-title");
                verticalLayout.add(label);
                verticalLayout.setClassName("content-action-logging");

                TextArea textArea=new TextArea(b.getInputData().getFactType().name());
                textArea.setReadOnly(true);
                textArea.setClassName("content-log");
                textArea.setValue(
                        b.getInputData().getRealFact().toString().replaceAll(",",",\n")
                                .replaceAll("\\{","\\{\n")
                                .replaceAll("\\}","\n\\}")
                                .replaceAll("\\[","\n\\[")

                );
                verticalLayout.add(textArea);


                dialogPerso1.add(verticalLayout);
            }

            dialogPerso1.open();
        });

        realFact.addClickListener(buttonClickEvent -> {
            DialogPerso dialogPerso1=new DialogPerso();

            BusinessTransactionAction b=gridActionLogging.getSelectedItems().stream().findFirst().get();

            if(b!=null&&b.getFact()!=null&&b.getFact().getRealFact()!=null){

                VerticalLayout verticalLayout=new VerticalLayout();
                Label label=new Label(b.getEventType().name()+" - "+(b.getRuleExecution()!=null&&b.getRuleExecution().getRuleName()!=null?b.getRuleExecution().getRuleName():""));
                label.setClassName("creation-runtime-title");
                verticalLayout.add(label);
                verticalLayout.setClassName("content-action-logging");

                TextArea textArea=new TextArea(b.getFact().getFactType().name());
                textArea.setReadOnly(true);
                textArea.setClassName("content-log");
                textArea.setValue(
                        b.getFact().getRealFact().toString().replaceAll(",",",\n")
                                .replaceAll("\\{","\\{\n")
                                .replaceAll("\\}","\n\\}")
                                .replaceAll("\\[","\n\\[")

                );
                verticalLayout.add(textArea);


                dialogPerso1.add(verticalLayout);
            }

            dialogPerso1.open();
        });

        thenFact.addClickListener(buttonClickEvent -> {
            DialogPerso dialogPerso1=new DialogPerso();

            BusinessTransactionAction b=gridActionLogging.getSelectedItems().stream().findFirst().get();

            if(b!=null&&b.getRuleExecution()!=null&&b.getRuleExecution().getThenFacts()!=null){

                VerticalLayout verticalLayout=new VerticalLayout();
                Label label=new Label(b.getEventType().name()+" - "+(b.getRuleExecution()!=null&&b.getRuleExecution().getRuleName()!=null?b.getRuleExecution().getRuleName():""));
                label.setClassName("creation-runtime-title");
                verticalLayout.add(label);
                verticalLayout.setClassName("content-action-logging");
                for (Fact fact:b.getRuleExecution().getThenFacts()){
                    if(fact!=null&&fact.getRealFact()!=null){

                        TextArea textArea=new TextArea(fact.getFactType().name());
                        textArea.setReadOnly(true);
                        textArea.setClassName("content-log");
                        textArea.setValue(
                                fact.getRealFact().toString().replaceAll(",",",\n")
                                        .replaceAll("\\{","\\{\n")
                                        .replaceAll("\\}","\n\\}")
                                        .replaceAll("\\[","\n\\[")

                        );
                        verticalLayout.add(textArea);
                    }
                }
                dialogPerso1.add(verticalLayout);
            }

            dialogPerso1.open();
        });
        whenFact.addClickListener(buttonClickEvent -> {
            DialogPerso dialogPerso1=new DialogPerso();

            BusinessTransactionAction b=gridActionLogging.getSelectedItems().stream().findFirst().get();

            if(b!=null&&b.getRuleExecution()!=null&&b.getRuleExecution().getWhenFacts()!=null){

                VerticalLayout verticalLayout=new VerticalLayout();
                Label label=new Label(b.getEventType().name()+" - "+(b.getRuleExecution()!=null&&b.getRuleExecution().getRuleName()!=null?b.getRuleExecution().getRuleName():""));
                label.setClassName("creation-runtime-title");
                verticalLayout.add(label);
                verticalLayout.setClassName("content-action-logging");
                for (Fact fact:b.getRuleExecution().getWhenFacts()){
                    if(fact!=null&&fact.getRealFact()!=null){

                        TextArea textArea=new TextArea(fact.getFactType().name());
                        textArea.setReadOnly(true);
                        textArea.setClassName("content-log");
                        textArea.setValue(
                                fact.getRealFact().toString().replaceAll(",",",\n")
                                        .replaceAll("\\{","\\{\n")
                                        .replaceAll("\\}","\n\\}")
                                        .replaceAll("\\[","\n\\[")

                        );
                        verticalLayout.add(textArea);
                    }
                }
                dialogPerso1.add(verticalLayout);
            }

            dialogPerso1.open();
        });

    }

}
