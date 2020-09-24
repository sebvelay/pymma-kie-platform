package org.chtijbug.drools.console.vaadincomponent.componentview;

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
import org.chtijbug.drools.proxy.persistence.model.BusinessTransactionPersistence;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GridLogging extends Grid<BusinessTransactionPersistence> {

    private TextField groupeId;

    private TextField artefactId;

    private TextField version;

    private TextField containerId;

    private TextField serverName;

    private String strGroupeId ="GroupeId";

    private String strArtefactId ="ArtefactId";

    private String strVersion ="Version";

    private String strContainerId ="ContainerId";

    private String strServerName ="ServerName";

    private ListDataProvider<BusinessTransactionPersistence> dataProvider;
    private ConfigurableFilterDataProvider<BusinessTransactionPersistence,Void,SerializablePredicate<BusinessTransactionPersistence>> filterDataProvider;

    private transient IndexerService indexerService;

    public GridLogging(){

        indexerService = AppContext.getApplicationContext().getBean(IndexerService.class);

        setClassName("log-grid-perso");
        setSelectionMode(Grid.SelectionMode.SINGLE);


        addColumn(new ComponentRenderer<>(runtimePersist -> {
            return new Label(runtimePersist.getHour()+":"+runtimePersist.getMinute()+":"+runtimePersist.getMillis()+" ->"+runtimePersist.getDay()+"/"+runtimePersist.getMonth()+"/"+runtimePersist.getYear());
        })).setHeader("Date");


        Grid.Column<BusinessTransactionPersistence> transactionIdC=addColumn(runtimePersist -> runtimePersist.getTransactionId()).setHeader("TransactionId");

        Grid.Column<BusinessTransactionPersistence> groupIdC=addColumn(runtimePersist -> runtimePersist.getGroupID());
        groupeId =new TextField(strGroupeId);
        groupeId.setValueChangeMode(ValueChangeMode.EAGER);
        groupeId.addValueChangeListener(e -> {
            refreshtGrid(groupeId.getValue(), strGroupeId);
        });
        groupIdC.setHeader(groupeId);

        Grid.Column<BusinessTransactionPersistence> artefactIdC=addColumn(runtimePersist -> runtimePersist.getArtefactID());
        artefactId =new TextField(strArtefactId);
        artefactId.setValueChangeMode(ValueChangeMode.EAGER);
        artefactId.addValueChangeListener(e -> {
            refreshtGrid(artefactId.getValue(), strArtefactId);
        });
        artefactIdC.setHeader(artefactId);

        Grid.Column<BusinessTransactionPersistence> containerIdC=addColumn(runtimePersist -> runtimePersist.getContainerId());
        containerId =new TextField(strContainerId);
        containerId.setValueChangeMode(ValueChangeMode.EAGER);
        containerId.addValueChangeListener(e -> {
            refreshtGrid(containerId.getValue(), strContainerId);
        });
        containerIdC.setHeader(containerId);

        Grid.Column<BusinessTransactionPersistence> versionC=addColumn(runtimePersist -> runtimePersist.getVersion());
        version =new TextField(strVersion);
        version.setValueChangeMode(ValueChangeMode.EAGER);
        version.addValueChangeListener(e -> {
            refreshtGrid(version.getValue(), strVersion);
        });
        versionC.setHeader(version);

        Grid.Column<BusinessTransactionPersistence> serverNameC=addColumn(runtimePersist -> runtimePersist.getServerName());
        serverName =new TextField(strServerName);
        serverName.setValueChangeMode(ValueChangeMode.EAGER);
        serverName.addValueChangeListener(e -> {
            refreshtGrid(serverName.getValue(), strServerName);
        });
        serverNameC.setHeader(serverName);

        setDataProvider(indexerService.getBusinessTransactionPersistenceRepository().findAll(PageRequest.of(0,100)).getContent());
    }
    private void refreshtGrid(String value,String type){

        filterDataProvider.setFilter(filterGrid(value.toUpperCase(),type));
        getDataProvider().refreshAll();
    }
    private SerializablePredicate<BusinessTransactionPersistence> filterGrid(String value, String type){
        SerializablePredicate<BusinessTransactionPersistence> columnPredicate = null;
        if(value.equals(" ")||type.equals(" ")){
            columnPredicate = runtimePersist -> (true);
        }else {
            if (type.equals(strGroupeId)) {
                columnPredicate = runtimePersist -> (runtimePersist.getGroupID()!=null&&runtimePersist.getGroupID().toUpperCase().contains(value));

            }else if (type.equals(strArtefactId)) {
                columnPredicate = runtimePersist -> (runtimePersist.getArtefactID()!=null&&runtimePersist.getArtefactID().toUpperCase().contains(value));
            }else if (type.equals(strServerName)) {
                columnPredicate = runtimePersist -> (runtimePersist.getServerName()!=null&&runtimePersist.getServerName().toUpperCase().contains(value));
            }else if (type.equals(strVersion)) {
                columnPredicate = runtimePersist -> (runtimePersist.getVersion()!=null&&runtimePersist.getVersion().toUpperCase().contains(value));
            }else if (type.equals(strContainerId)) {
                columnPredicate = runtimePersist -> (runtimePersist.getContainerId()!=null&&runtimePersist.getContainerId().toUpperCase().contains(value));
            }
        }
        return columnPredicate;
    }

    public void setDataProvider(List<BusinessTransactionPersistence> b){


        List<BusinessTransactionPersistence> businessTransactionPersistences=null;

        if(b!=null) {

            businessTransactionPersistences=new ArrayList<>(b);

            Collections.sort(businessTransactionPersistences, new Comparator<BusinessTransactionPersistence>() {
                @Override
                public int compare(BusinessTransactionPersistence businessTransactionPersistence, BusinessTransactionPersistence t1) {

                    return indexerService.toDate(businessTransactionPersistence.getYear(), businessTransactionPersistence.getMonth(), businessTransactionPersistence.getDay(), businessTransactionPersistence.getHour(), businessTransactionPersistence.getMinute(), businessTransactionPersistence.getMillis()).compareTo(
                            indexerService.toDate(t1.getYear(), t1.getMonth(), t1.getDay(), t1.getHour(), t1.getMinute(), t1.getMillis()));
                }
            });


            dataProvider = new ListDataProvider<>(businessTransactionPersistences);

            filterDataProvider = dataProvider.withConfigurableFilter();
            setDataProvider(filterDataProvider);

            reinitFilter();

        }
    }
    public void reinitFilter(){
        groupeId.setValue("");
        artefactId.setValue("");
        version.setValue("");
        containerId.setValue("");
        serverName.setValue("");
    }

}
