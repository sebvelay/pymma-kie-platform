package org.chtijbug.drools.console.vaadincomponent.componentview;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializablePredicate;
import org.chtijbug.drools.console.service.IndexerService;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.vaadincomponent.componentperso.DialogPerso;
import org.chtijbug.drools.logging.Fact;
import org.chtijbug.drools.proxy.persistence.model.BusinessTransactionAction;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.util.List;

public class GridActionLogging extends Grid<BusinessTransactionAction> {

    private TextField eventType;

    private TextField positionExecution;

    private TextField ruleName;

    private TextField packageName;

    private TextField ruleFlowGroup;


    private String strEventType = "EventType";

    private String strPositionExecution = "Position";

    private String strRuleName = "RuleName";

    private String strPackageName = "Package";

    private String strRuleFlowGroup = "RuleFlowGroup";
    private ObjectMapper mapper = new ObjectMapper();

    private ListDataProvider<BusinessTransactionAction> dataProvider;
    private ConfigurableFilterDataProvider<BusinessTransactionAction, Void, SerializablePredicate<BusinessTransactionAction>> filterDataProvider;

    private transient IndexerService indexerService;

    public GridActionLogging(String idRequest) {

        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy hh:mm"));
        indexerService = AppContext.getApplicationContext().getBean(IndexerService.class);

        setClassName("action-log-grid-perso");
        setSelectionMode(Grid.SelectionMode.SINGLE);
        setVerticalScrollingEnabled(true);

        addColumn(new ComponentRenderer<>(runtimePersist -> {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Label label = new Label();
            if (runtimePersist.getRuleExecution() != null) {
                label.setText(simpleDateFormat.format(runtimePersist.getRuleExecution().getStartDate()));
            }
            return label;
        })).setHeader("Début").setResizable(true);

        addColumn(new ComponentRenderer<>(runtimePersist -> {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Label label = new Label();
            if (runtimePersist.getRuleExecution() != null) {
                label.setText(simpleDateFormat.format(runtimePersist.getRuleExecution().getEndDate()));
            }
            return label;
        })).setHeader("Fin").setResizable(true);


        Grid.Column<BusinessTransactionAction> enventTypeC = addColumn(runtimePersist -> runtimePersist.getEventType());
        this.eventType = new TextField(strEventType);
        this.eventType.setValueChangeMode(ValueChangeMode.EAGER);
        this.eventType.addValueChangeListener(e ->
                refreshtGrid(this.eventType.getValue(), strEventType)
        );
        enventTypeC.setHeader(this.eventType).setResizable(true);

        Grid.Column<BusinessTransactionAction> positionC = addColumn(runtimePersist -> runtimePersist.getEventNumber());

        positionExecution = new TextField(strPositionExecution);
        positionExecution.setValueChangeMode(ValueChangeMode.EAGER);
        positionExecution.addValueChangeListener(e ->
                refreshtGrid(positionExecution.getValue(), strPositionExecution)
        );
        positionC.setHeader(positionExecution).setResizable(true);
        positionC.setWidth("3%");

        Grid.Column<BusinessTransactionAction> ruleNameC = addColumn(runtimePersist -> runtimePersist.getRuleExecution() != null ? runtimePersist.getRuleExecution().getRuleName() : "");
        ruleName = new TextField(strRuleName);
        ruleName.setValueChangeMode(ValueChangeMode.EAGER);
        ruleName.addValueChangeListener(e ->
                refreshtGrid(ruleName.getValue(), strRuleName)
        );
        ruleNameC.setHeader(ruleName).setResizable(true);

        Grid.Column<BusinessTransactionAction> versionC = addColumn(runtimePersist -> runtimePersist.getRuleExecution() != null ? runtimePersist.getRuleExecution().getPackageName() : "");
        packageName = new TextField(strPackageName);
        packageName.setValueChangeMode(ValueChangeMode.EAGER);
        packageName.addValueChangeListener(e ->
                refreshtGrid(packageName.getValue(), strPackageName)
        );
        versionC.setHeader(packageName).setResizable(true);

        Grid.Column<BusinessTransactionAction> ruleflowC = addColumn(runtimePersist -> runtimePersist.getRuleflowGroupName() != null ? runtimePersist.getRuleflowGroupName() : "");
        ruleFlowGroup = new TextField(strRuleFlowGroup);
        ruleFlowGroup.setValueChangeMode(ValueChangeMode.EAGER);
        ruleFlowGroup.addValueChangeListener(e ->
                refreshtGrid(ruleFlowGroup.getValue(), strRuleFlowGroup)
        );
        ruleflowC.setHeader(ruleFlowGroup).setResizable(true);

        addColumn(new ComponentRenderer<>(runtimePersist -> {
            HorizontalLayout horizontalLayout = new HorizontalLayout();
            if (runtimePersist.getInputData() != null && runtimePersist.getInputData().getRealFact() != null) {
                Button inputData = new Button("Input data");
                inputData.setClassName("menu-button");
                inputData.setEnabled(true);
                inputData.addClickListener(buttonClickEvent -> {
                    DialogPerso dialogPerso1 = new DialogPerso();

                    BusinessTransactionAction b = runtimePersist;

                    if (b != null && b.getInputData() != null && b.getInputData().getRealFact() != null) {

                        VerticalLayout verticalLayout = new VerticalLayout();
                        Label label = new Label(b.getEventType().name());
                        label.setClassName("creation-runtime-title");
                        verticalLayout.add(label);
                        verticalLayout.setClassName("content-action-logging");

                        TextArea textArea = new TextArea(b.getInputData().getFactType().name() + "-" + b.getInputData().getFullClassName());
                        textArea.setReadOnly(true);
                        textArea.setClassName("content-log");
                        try {
                            mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy hh:mm"));
                            String text = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(b.getInputData().getRealFact());
                            textArea.setValue(text);
                        } catch (JsonProcessingException e) {
                            textArea.setValue(
                                    b.getInputData().getRealFact().toString().replace(",", ",\n")
                                            .replaceAll("\\{", "\\{\n")
                                            .replaceAll("\\}", "\n\\}")
                                            .replaceAll("\\[", "\n\\[")

                            );
                        }
                        verticalLayout.add(textArea);
                        dialogPerso1.add(verticalLayout);
                    }

                    dialogPerso1.open();
                });
                horizontalLayout.add(inputData);
            }
            if (runtimePersist.getFact() != null && runtimePersist.getFact().getRealFact() != null) {
                Button realFact = new Button("Insert Data");
                realFact.setClassName("menu-button");
                realFact.setEnabled(true);
                realFact.addClickListener(buttonClickEvent -> {
                    DialogPerso dialogPerso1 = new DialogPerso();

                    BusinessTransactionAction b = runtimePersist;

                    if (b != null && b.getFact() != null && b.getFact().getRealFact() != null) {

                        VerticalLayout verticalLayout = new VerticalLayout();
                        Label label = new Label(b.getEventType().name());
                        label.setClassName("creation-runtime-title");
                        verticalLayout.add(label);
                        verticalLayout.setClassName("content-action-logging");

                        TextArea textArea = new TextArea(b.getFact().getFactType().name() + "-" + b.getFact().getFullClassName());
                        textArea.setReadOnly(true);
                        textArea.setClassName("content-log");
                        try {
                            String text = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(b.getFact().getRealFact());
                            textArea.setValue(text);
                        } catch (JsonProcessingException e) {
                            textArea.setValue(
                                    b.getFact().getRealFact().toString().replace(",", ",\n")
                                            .replace("\\{", "\\{\n")
                                            .replace("\\}", "\n\\}")
                                            .replace("\\[", "\n\\[")

                            );
                        }
                        verticalLayout.add(textArea);
                        dialogPerso1.add(verticalLayout);
                    }

                    dialogPerso1.open();
                });
                horizontalLayout.add(realFact);
            }
            if (runtimePersist.getRuleExecution() != null && runtimePersist.getRuleExecution().getThenFacts() != null && !runtimePersist.getRuleExecution().getThenFacts().isEmpty()) {
                Button whenFact = new Button("When Data");
                whenFact.setClassName("menu-button");
                whenFact.setEnabled(true);
                whenFact.addClickListener(buttonClickEvent -> {
                    DialogPerso dialogPerso1 = new DialogPerso();

                    BusinessTransactionAction b = runtimePersist;

                    if (b != null && b.getRuleExecution() != null && b.getRuleExecution().getWhenFacts() != null) {

                        VerticalLayout verticalLayout = new VerticalLayout();
                        Label label = new Label(b.getEventType().name() + " - " + (b.getRuleExecution() != null && b.getRuleExecution().getRuleName() != null ? b.getRuleExecution().getRuleName() : ""));
                        label.setClassName("creation-runtime-title");
                        verticalLayout.add(label);
                        verticalLayout.setClassName("content-action-logging");
                        for (Fact fact : b.getRuleExecution().getWhenFacts()) {
                            if (fact != null && fact.getRealFact() != null) {

                                TextArea textArea = new TextArea(fact.getFactType().name() + "-" + fact.getFullClassName());
                                textArea.setReadOnly(true);
                                textArea.setClassName("content-log");
                                try {
                                    String text = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fact.getRealFact());
                                    textArea.setValue(text);
                                } catch (JsonProcessingException e) {
                                    textArea.setValue(
                                            fact.getRealFact().toString().replace(",", ",\n")
                                                    .replace("\\{", "\\{\n")
                                                    .replace("\\}", "\n\\}")
                                                    .replace("\\[", "\n\\[")

                                    );
                                }
                                verticalLayout.add(textArea);
                            }
                        }
                        dialogPerso1.add(verticalLayout);
                    }

                    dialogPerso1.open();
                });
                horizontalLayout.add(whenFact);
            }
            if (runtimePersist.getRuleExecution() != null && runtimePersist.getRuleExecution().getThenFacts() != null
                    && !runtimePersist.getRuleExecution().getThenFacts().isEmpty()) {

                Button thenFact = new Button("Then data");
                thenFact.setClassName("menu-button");
                thenFact.setEnabled(true);
                thenFact.addClickListener(buttonClickEvent -> {
                    DialogPerso dialogPerso1 = new DialogPerso();

                    BusinessTransactionAction b = runtimePersist;

                    if (b != null && b.getRuleExecution() != null
                            && b.getRuleExecution().getThenFacts() != null) {

                        VerticalLayout verticalLayout = new VerticalLayout();
                        Label label = new Label(b.getEventType().name() + " - " + (b.getRuleExecution() != null && b.getRuleExecution().getRuleName() != null ? b.getRuleExecution().getRuleName() : ""));
                        label.setClassName("creation-runtime-title");
                        verticalLayout.add(label);
                        verticalLayout.setClassName("content-action-logging");
                        for (Fact fact : b.getRuleExecution().getThenFacts()) {
                            if (fact != null && fact.getRealFact() != null) {

                                TextArea textArea = new TextArea(fact.getFactType().name() + "-" + fact.getFullClassName());
                                textArea.setReadOnly(true);
                                textArea.setClassName("content-log");
                                try {
                                    String text = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fact.getRealFact());
                                    textArea.setValue(text);
                                } catch (JsonProcessingException e) {
                                    textArea.setValue(
                                            fact.getRealFact().toString().replace(",", ",\n")
                                                    .replace("\\{", "\\{\n")
                                                    .replace("\\}", "\n\\}")
                                                    .replace("\\[", "\n\\[")

                                    );
                                }
                                verticalLayout.add(textArea);
                            }
                        }
                        dialogPerso1.add(verticalLayout);
                    }
                    dialogPerso1.open();
                });
                horizontalLayout.add(thenFact);
            }
            if (runtimePersist.getOutputData() != null && runtimePersist.getOutputData().getRealFact() != null) {
                Button outputData = new Button("Output data");
                outputData.setClassName("menu-button");
                outputData.setEnabled(true);
                outputData.addClickListener(buttonClickEvent -> {
                    DialogPerso dialogPerso1 = new DialogPerso();

                    BusinessTransactionAction b = runtimePersist;

                    if (b != null && b.getOutputData() != null && b.getOutputData().getRealFact() != null) {

                        VerticalLayout verticalLayout = new VerticalLayout();
                        Label label = new Label(b.getEventType().name());
                        label.setClassName("creation-runtime-title");
                        verticalLayout.add(label);
                        verticalLayout.setClassName("content-action-logging");

                        TextArea textArea = new TextArea(b.getOutputData().getFactType().name() + "-" + b.getOutputData().getFullClassName());
                        textArea.setReadOnly(true);
                        textArea.setClassName("content-log");
                        try {
                            String text = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(b.getOutputData().getRealFact());
                            textArea.setValue(text);
                        } catch (JsonProcessingException e) {
                            textArea.setValue(
                                    b.getOutputData().getRealFact().toString().replace(",", ",\n")
                                            .replace("\\{", "\\{\n")
                                            .replace("\\}", "\n\\}")
                                            .replace("\\[", "\n\\[")

                            );
                        }
                        verticalLayout.add(textArea);


                        dialogPerso1.add(verticalLayout);
                    }

                    dialogPerso1.open();
                });
                horizontalLayout.add(outputData);
            }
            return horizontalLayout;
        })).setHeader("Action");
        setDataProvider(idRequest);
    }

    private void refreshtGrid(String value, String type) {

        filterDataProvider.setFilter(filterGrid(value.toUpperCase(), type));
        getDataProvider().refreshAll();
    }

    private SerializablePredicate<BusinessTransactionAction> filterGrid(String value, String type) {
        SerializablePredicate<BusinessTransactionAction> columnPredicate = null;
        if (value.equals(" ") || type.equals(" ")) {
            columnPredicate = runtimePersist -> (true);
        } else {
            if (type.equals(strPositionExecution)) {
                columnPredicate = runtimePersist -> (String.valueOf(runtimePersist.getEventNumber()).equals(value));

            } else if (type.equals(strEventType)) {
                columnPredicate = runtimePersist -> (runtimePersist.getEventType() != null && runtimePersist.getEventType().toString().toUpperCase().contains(value));

            } else if (type.equals(strRuleName)) {
                columnPredicate = runtimePersist -> (runtimePersist.getRuleExecution() != null && runtimePersist.getRuleExecution().getRuleName() != null && runtimePersist.getRuleExecution().getRuleName().toUpperCase().contains(value));
            } else if (type.equals(strPackageName)) {
                columnPredicate = runtimePersist -> (runtimePersist.getRuleExecution() != null && runtimePersist.getRuleExecution().getPackageName() != null && runtimePersist.getRuleExecution().getPackageName().toUpperCase().contains(value));
            } else if (type.equals(strRuleFlowGroup)) {
                columnPredicate = runtimePersist -> (runtimePersist.getRuleflowGroupName() != null && runtimePersist.getRuleflowGroupName().toUpperCase().contains(value));
            }

        }
        return columnPredicate;
    }

    public void setDataProvider(String id) {

        List<BusinessTransactionAction> businessTransactionPersistences = indexerService.getBusinessTransactionActionRepository().findAllByBusinessTransactionId(id, Sort.by(new Sort.Order(Sort.Direction.ASC, "eventNumber")), PageRequest.of(0, 5000));

        if (businessTransactionPersistences != null) {
            dataProvider = new ListDataProvider<>(businessTransactionPersistences);

            filterDataProvider = dataProvider.withConfigurableFilter();
            setDataProvider(filterDataProvider);

            reinitFilter();

        }
    }

    public void reinitFilter() {
        positionExecution.setValue("");
        ruleName.setValue("");
        eventType.setValue("");
        packageName.setValue("");
        ruleFlowGroup.setValue("");
    }

}
