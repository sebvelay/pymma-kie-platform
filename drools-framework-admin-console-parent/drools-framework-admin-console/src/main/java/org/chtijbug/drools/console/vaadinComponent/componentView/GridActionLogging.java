package org.chtijbug.drools.console.vaadinComponent.componentView;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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

import java.util.List;

public class GridActionLogging extends Grid<BusinessTransactionAction> {

    private TextField eventType;

    private TextField positionExecution;

    private TextField ruleName;

    private TextField packageName;



    private String strEventType ="EventType";

    private String strPositionExecution ="Position";

    private String strRuleName ="RuleName";

    private String strPackageName ="Package";


    private ListDataProvider<BusinessTransactionAction> dataProvider;
    private ConfigurableFilterDataProvider<BusinessTransactionAction,Void,SerializablePredicate<BusinessTransactionAction>> filterDataProvider;

    private IndexerService indexerService;

    public GridActionLogging(String idRequest){

        indexerService = AppContext.getApplicationContext().getBean(IndexerService.class);

        setClassName("action-log-grid-perso");
        setSelectionMode(Grid.SelectionMode.SINGLE);


        addColumn(new ComponentRenderer<>(runtimePersist -> {

            VerticalLayout verticalLayout=new VerticalLayout();

            if(runtimePersist.getRuleExecution()!=null) {
                Label label = new Label("Starting ->" + runtimePersist.getRuleExecution().getStartDate());
                verticalLayout.add(label);
                Label label2 = new Label("Ending ->" + runtimePersist.getRuleExecution().getEndDate());
                verticalLayout.add(label2);
            }
            return verticalLayout;
        })).setHeader("Date");


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
        }
        return columnPredicate;
    }

    public void setDataProvider(String id){

        List<BusinessTransactionAction> businessTransactionPersistences = indexerService.getBusinessTransactionActionRepository().findAllByBusinessTransactionId(id,new Sort(new Sort.Order(Sort.Direction.ASC,"eventNumber")),new PageRequest(0,50));

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
    }

}
