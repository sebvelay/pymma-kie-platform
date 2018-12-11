package org.chtijbug.drools.console.view;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.service.model.kie.KieConfigurationData;
import org.chtijbug.drools.console.service.util.AppContext;
import org.drools.guvnor.server.jaxrs.jaxb.Asset;
import org.drools.workbench.models.guided.template.backend.RuleTemplateModelXMLPersistenceImpl;
import org.drools.workbench.models.guided.template.shared.TemplateModel;

public class AssetEditView extends VerticalLayout implements AddLog, View {

    final private KieRepositoryService kieRepositoryService;
    final private KieConfigurationData config;
    private UserConnected userConnected;
    private Asset assetToUpdate;
    private XmlMapper mapper = new XmlMapper();
    private String spaceName;
    private String projectName;

    public AssetEditView(UserConnected userConnected, String spaceName, String projectName, Asset assetToUpdate) {
        this.userConnected = userConnected;
        this.assetToUpdate = assetToUpdate;
        this.spaceName = spaceName;
        this.projectName = projectName;
        this.kieRepositoryService = AppContext.getApplicationContext().getBean(KieRepositoryService.class);
        this.config = AppContext.getApplicationContext().getBean(KieConfigurationData.class);
        String assetContent = kieRepositoryService.getAssetSource(config.getKiewbUrl(), userConnected.getUserName(), userConnected.getUserPassword(), spaceName, projectName, assetToUpdate.getTitle());
        TemplateModel model = RuleTemplateModelXMLPersistenceImpl.getInstance().unmarshal(assetContent);
        System.out.println("coucou");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    @Override
    public void addRow(String textToAdd) {

    }
}
