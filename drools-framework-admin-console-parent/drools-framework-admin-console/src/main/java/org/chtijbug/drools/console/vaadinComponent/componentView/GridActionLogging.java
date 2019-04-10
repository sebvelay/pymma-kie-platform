package org.chtijbug.drools.console.vaadinComponent.componentView;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializablePredicate;
import org.chtijbug.drools.console.service.IndexerService;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.indexer.persistence.model.BusinessTransactionAction;
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



    private String strEventType ="EventType";

    private String strPositionExecution ="Position";

    private String strRuleName ="RuleName";

    private String strPackageName ="Package";

    private String strRuleFlowGroup="RuleFlouwGroup";

    private ListDataProvider<BusinessTransactionAction> dataProvider;
    private ConfigurableFilterDataProvider<BusinessTransactionAction,Void,SerializablePredicate<BusinessTransactionAction>> filterDataProvider;

    private IndexerService indexerService;

    public GridActionLogging(String idRequest){

        indexerService = AppContext.getApplicationContext().getBean(IndexerService.class);

        setClassName("action-log-grid-perso");
        setSelectionMode(Grid.SelectionMode.SINGLE);


        addColumn(new ComponentRenderer<>(runtimePersist -> {

            SimpleDateFormat simpleDateFormat=new SimpleDateFormat(  "yyyy-MM-dd HH:mm:ss");
            Label label=new Label();
            if(runtimePersist.getRuleExecution()!=null) {
                label.setText(simpleDateFormat.format(runtimePersist.getRuleExecution().getStartDate()));
            }
            return label;
        })).setHeader("Début");

        addColumn(new ComponentRenderer<>(runtimePersist -> {

            SimpleDateFormat simpleDateFormat=new SimpleDateFormat(  "yyyy-MM-dd HH:mm:ss");
            Label label=new Label();
            if(runtimePersist.getRuleExecution()!=null) {
                label.setText(simpleDateFormat.format(runtimePersist.getRuleExecution().getEndDate()));
            }
            return label;
        })).setHeader("Fin");


        Grid.Column<BusinessTransactionAction> enventTypeC=addColumn(runtimePersist -> runtimePersist.getEventType());
        this.eventType =new TextField(strEventType);
        this.eventType.setValueChangeMode(ValueChangeMode.EAGER);
        this.eventType.addValueChangeListener(e -> {
            refreshtGrid(this.eventType.getValue(), strEventType);
        });
        enventTypeC.setHeader(this.eventType);

        Grid.Column<BusinessTransactionAction> positionC=addColumn(runtimePersist -> runtimePersist.getEventNumber());

        positionExecution =new TextField(strPositionExecution);
        positionExecution.setValueChangeMode(ValueChangeMode.EAGER);
        positionExecution.addValueChangeListener(e -> {
            refreshtGrid(positionExecution.getValue(), strPositionExecution);
        });
        positionC.setHeader(positionExecution);
        positionC.setWidth("3%");

        Grid.Column<BusinessTransactionAction> ruleNameC=addColumn(runtimePersist -> runtimePersist.getRuleExecution()!=null?runtimePersist.getRuleExecution().getRuleName():"");
        ruleName =new TextField(strRuleName);
        ruleName.setValueChangeMode(ValueChangeMode.EAGER);
        ruleName.addValueChangeListener(e -> {
            refreshtGrid(ruleName.getValue(), strRuleName);
        });
        ruleNameC.setHeader(ruleName);

        Grid.Column<BusinessTransactionAction> versionC=addColumn(runtimePersist -> runtimePersist.getRuleExecution()!=null?runtimePersist.getRuleExecution().getPackageName():"");
        packageName =new TextField(strPackageName);
        packageName.setValueChangeMode(ValueChangeMode.EAGER);
        packageName.addValueChangeListener(e -> {
            refreshtGrid(packageName.getValue(), strPackageName);
        });
        versionC.setHeader(packageName);

        Grid.Column<BusinessTransactionAction> ruleflowC=addColumn(runtimePersist -> runtimePersist.getRuleflowGroupName()!=null?runtimePersist.getRuleflowGroupName():"");
        ruleFlowGroup =new TextField(strRuleFlowGroup);
        ruleFlowGroup.setValueChangeMode(ValueChangeMode.EAGER);
        ruleFlowGroup.addValueChangeListener(e -> {
            refreshtGrid(ruleFlowGroup.getValue(), strRuleFlowGroup);
        });
        ruleflowC.setHeader(ruleFlowGroup);

        addColumn(new ComponentRenderer<>(runtimePersist -> {

            Checkbox label=new Checkbox();
            label.setEnabled(false);
            label.setValue(false);

            if(runtimePersist.getInputData()!=null&&runtimePersist.getInputData().getRealFact()!=null){
                label.setValue(true);
            }
            if(runtimePersist.getFact()!=null&&runtimePersist.getFact().getRealFact()!=null){
                label.setValue(true);
            }
            if(runtimePersist.getRuleExecution()!=null&&runtimePersist.getRuleExecution().getThenFacts()!=null&&runtimePersist.getRuleExecution().getThenFacts().size()>0){
                label.setValue(true);
            }
            if(runtimePersist.getRuleExecution()!=null&&runtimePersist.getRuleExecution().getWhenFacts()!=null&&runtimePersist.getRuleExecution().getWhenFacts().size()>0){
                label.setValue(true);
            }
            return label;
        })).setHeader("Data?");

        setDataProvider(idRequest);
    }
    private void refreshtGrid(String value,String type){

        filterDataProvider.setFilter(filterGrid(value.toUpperCase(),type));
        getDataProvider().refreshAll();
    }
    private SerializablePredicate<BusinessTransactionAction> filterGrid(String value, String type){
        SerializablePredicate<BusinessTransactionAction> columnPredicate = null;
        if(value.equals(" ")||type.equals(" ")){
            columnPredicate = runtimePersist -> (true);
        }else {
            if (type.equals(strPositionExecution)) {
                columnPredicate = runtimePersist -> (String.valueOf(runtimePersist.getEventNumber()).equals(value));

            } else if (type.equals(strEventType)) {
                columnPredicate = runtimePersist -> (runtimePersist.getEventType()!=null&&runtimePersist.getEventType().toString().toUpperCase().contains(value));

            } else if (type.equals(strRuleName)) {
                columnPredicate = runtimePersist -> (runtimePersist.getRuleExecution()!=null&&runtimePersist.getRuleExecution().getRuleName()!=null&&runtimePersist.getRuleExecution().getRuleName().toUpperCase().contains(value));
            }
            else if (type.equals(strPackageName)) {
                columnPredicate = runtimePersist -> (runtimePersist.getRuleExecution()!=null&&runtimePersist.getRuleExecution().getPackageName()!=null&&runtimePersist.getRuleExecution().getPackageName().toUpperCase().contains(value));
            }
            else if (type.equals(strRuleFlowGroup)) {
                columnPredicate = runtimePersist -> (runtimePersist.getRuleflowGroupName()!=null&&runtimePersist.getRuleflowGroupName().toUpperCase().contains(value));
            }

        }
        return columnPredicate;
    }

    public void setDataProvider(String id){

        List<BusinessTransactionAction> businessTransactionPersistences = indexerService.getBusinessTransactionActionRepository().findAllByBusinessTransactionId(id,new Sort(new Sort.Order(Sort.Direction.ASC,"eventNumber")),new PageRequest(0,5000));

        if(businessTransactionPersistences!=null) {
            dataProvider = new ListDataProvider<>(businessTransactionPersistences);

            filterDataProvider = dataProvider.withConfigurableFilter();
            setDataProvider(filterDataProvider);

            reinitFilter();

        }
    }
    public void reinitFilter(){
        positionExecution.setValue("");
        ruleName.setValue("");
        eventType.setValue("");
        packageName.setValue("");
        ruleFlowGroup.setValue("");
    }

}
