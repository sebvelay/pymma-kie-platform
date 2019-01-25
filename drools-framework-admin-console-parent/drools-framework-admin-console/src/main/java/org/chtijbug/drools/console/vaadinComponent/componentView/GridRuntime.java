package org.chtijbug.drools.console.vaadinComponent.componentView;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.SerializablePredicate;
import org.chtijbug.drools.console.service.ProjectPersistService;
import org.chtijbug.drools.console.service.RuntimeService;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.proxy.persistence.model.ProjectPersist;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;
import com.vaadin.flow.component.html.Label;

import java.util.List;

public class GridRuntime extends Grid<RuntimePersist> {

    private TextField runtimeName;

    private TextField hostName;

    private TextField version;


    private String strRuntimeName="Runtime Name";

    private String strHostName="Hostname";

    private String strVersion="Version";


    private ProjectPersistService projectPersistService;
    private RuntimeService runtimeService;

    private ListDataProvider<RuntimePersist> dataProvider;
    private ConfigurableFilterDataProvider<RuntimePersist,Void,SerializablePredicate<RuntimePersist>> filterDataProvider;


    public GridRuntime(){

        projectPersistService = AppContext.getApplicationContext().getBean(ProjectPersistService.class);
        runtimeService= AppContext.getApplicationContext().getBean(RuntimeService.class);

        setClassName("deployment-grid-perso");
        setSelectionMode(Grid.SelectionMode.SINGLE);

        Grid.Column<RuntimePersist> runtimeNameCo=addColumn(runtimePersist -> runtimePersist.getServerName());
        runtimeName=new TextField(strRuntimeName);
        runtimeName.setValueChangeMode(ValueChangeMode.EAGER);
        runtimeName.addValueChangeListener(e -> {
            refreshtGrid(runtimeName.getValue(), strRuntimeName);
        });
        runtimeNameCo.setHeader(runtimeName);

        Grid.Column<RuntimePersist> hostnameCo=addColumn(runtimePersist -> runtimePersist.getHostname());
        hostName=new TextField(strHostName);
        hostName.setValueChangeMode(ValueChangeMode.EAGER);
        hostName.addValueChangeListener(e -> {
            refreshtGrid(hostName.getValue(), strHostName);
        });
        hostnameCo.setHeader(hostName);

        Grid.Column<RuntimePersist> versionCo=addColumn(runtimePersist -> runtimePersist.getVersion());
        version=new TextField(strVersion);
        version.setValueChangeMode(ValueChangeMode.EAGER);
        version.addValueChangeListener(e -> {
            refreshtGrid(version.getValue(), strVersion);
        });
        versionCo.setHeader(version);


        addColumn(new ComponentRenderer<>(runtimePersist -> {

            List<ProjectPersist> projectPersists=projectPersistService.getProjectRepository().findByServerName(runtimePersist.getServerName());

            Label label=new Label(projectPersists!=null?projectPersists.size()+"":"0");

            return label;
        })).setHeader("associated projects");

        setDataProvider();
    }
    private void refreshtGrid(String value,String type){

        filterDataProvider.setFilter(filterGrid(value.toUpperCase(),type));
        getDataProvider().refreshAll();
    }
    private SerializablePredicate<RuntimePersist> filterGrid(String value, String type){
        SerializablePredicate<RuntimePersist> columnPredicate = null;
        if(value.equals(" ")||type.equals(" ")){
            columnPredicate = runtimePersist -> (true);
        }else {
            if (type.equals(strHostName)) {
                columnPredicate = runtimePersist -> (runtimePersist.getHostname()!=null&&runtimePersist.getHostname().toUpperCase().contains(value));

            } else if (type.equals(strRuntimeName)) {
                columnPredicate = runtimePersist -> (runtimePersist.getServerName()!=null&&runtimePersist.getServerName().toUpperCase().contains(value));

            } else if (type.equals(strVersion)) {
                columnPredicate = runtimePersist -> (runtimePersist.getVersion()!=null&&runtimePersist.getVersion().toUpperCase().contains(value));
            }
        }
        return columnPredicate;
    }

    public void setDataProvider(){

        List<RuntimePersist> runtimePersists = runtimeService.getRuntimeRepository().findAll();
        if(runtimePersists!=null) {
            dataProvider = new ListDataProvider<>(runtimePersists);

            filterDataProvider = dataProvider.withConfigurableFilter();
            setDataProvider(filterDataProvider);

            reinitFilter();

        }
    }
    public void reinitFilter(){
        hostName.setValue("");
        version.setValue("");
        runtimeName.setValue("");
    }
}
