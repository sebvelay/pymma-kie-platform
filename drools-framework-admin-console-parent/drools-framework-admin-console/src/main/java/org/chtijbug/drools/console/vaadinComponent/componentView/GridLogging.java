package org.chtijbug.drools.console.vaadinComponent.componentView;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializablePredicate;
import org.chtijbug.drools.console.service.IndexerService;
import org.chtijbug.drools.console.service.ProjectPersistService;
import org.chtijbug.drools.console.service.RuntimeService;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.indexer.persistence.model.BusinessTransactionPersistence;
import org.chtijbug.drools.proxy.persistence.model.ProjectPersist;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;

import java.util.ArrayList;
import java.util.List;

public class GridLogging extends Grid<BusinessTransactionPersistence> {

    private TextField transactionId;

    private TextField groupeId;

    private TextField artefactId;

    private TextField version;

    private TextField containerId;

    private TextField serverName;



    private String strTransactionId ="transactiondId";

    private String strGroupeId ="groupeId";

    private String strArtefactId ="artefactId";

    private String strVersion ="version";

    private String strContainerId ="containerId";

    private String strServerName ="serverName";

    private ListDataProvider<BusinessTransactionPersistence> dataProvider;
    private ConfigurableFilterDataProvider<BusinessTransactionPersistence,Void,SerializablePredicate<BusinessTransactionPersistence>> filterDataProvider;

    private IndexerService indexerService;

    public GridLogging(){

        indexerService = AppContext.getApplicationContext().getBean(IndexerService.class);

        setClassName("deployment-grid-perso");
        setSelectionMode(Grid.SelectionMode.SINGLE);


        addColumn(new ComponentRenderer<>(runtimePersist -> {

            Label label=new Label(runtimePersist.getHour()+":"+runtimePersist.getMinute()+":"+runtimePersist.getMillis()+" ->"+runtimePersist.getDay()+"/"+runtimePersist.getMonth()+"/"+runtimePersist.getYear());

            return label;
        })).setHeader("Date");


        Grid.Column<BusinessTransactionPersistence> transactionIdC=addColumn(runtimePersist -> runtimePersist.getTransactionId());
        this.transactionId =new TextField(strTransactionId);
        this.transactionId.setValueChangeMode(ValueChangeMode.EAGER);
        this.transactionId.addValueChangeListener(e -> {
            refreshtGrid(this.transactionId.getValue(), strTransactionId);
        });
        transactionIdC.setHeader(this.transactionId);

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

        setDataProvider();
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

            } else if (type.equals(strTransactionId)) {
                columnPredicate = runtimePersist -> (runtimePersist.getTransactionId()!=null&&runtimePersist.getTransactionId().toUpperCase().contains(value));

            } else if (type.equals(strArtefactId)) {
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

    public void setDataProvider(){

        List<BusinessTransactionPersistence> businessTransactionPersistences = new ArrayList<>();
        indexerService.getBusinessTransactionPersistenceRepository().findAll().forEach(businessTransactionPersistences::add);

        if(businessTransactionPersistences!=null) {
            dataProvider = new ListDataProvider<>(businessTransactionPersistences);

            filterDataProvider = dataProvider.withConfigurableFilter();
            setDataProvider(filterDataProvider);

            reinitFilter();

        }
    }
    public void reinitFilter(){
        groupeId.setValue("");
        artefactId.setValue("");
        transactionId.setValue("");
        version.setValue("");
        containerId.setValue("");
        serverName.setValue("");
    }

}
