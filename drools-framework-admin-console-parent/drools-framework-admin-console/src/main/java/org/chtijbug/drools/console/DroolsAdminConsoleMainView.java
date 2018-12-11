package org.chtijbug.drools.console;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import org.chtijbug.drools.console.service.JenkinsService;
import org.chtijbug.drools.console.service.KieRepositoryService;
import org.chtijbug.drools.console.service.KieServerRepositoryService;
import org.chtijbug.drools.console.service.model.UserConnected;
import org.chtijbug.drools.console.view.DeploymentView;
import org.chtijbug.drools.console.view.TableLikeArtefactView;
import org.springframework.beans.factory.annotation.Autowired;

@Push
@Theme("runo")
@SpringUI
@PreserveOnRefresh
@SuppressWarnings("serial")
public class DroolsAdminConsoleMainView extends UI {


    //  @Autowired
    //  private GitLabRepositoryService gitLabRepositoryService;

    final UserConnected userConnected = new UserConnected();
    //  @Autowired
    //  private GitRepositoryService gitRepositoryService;
    @Autowired
    private KieRepositoryService kieRepositoryService;
    @Autowired
    private KieServerRepositoryService kieServerRepositoryService;
    @Autowired
    private JenkinsService jenkinsService;

    final private Navigator navigator = new Navigator(this, this);

    public DroolsAdminConsoleMainView() {
    }

    @Override
    protected void init(VaadinRequest request) {


        final DeploymentView deploymentView = new DeploymentView(userConnected);
        final TableLikeArtefactView tableLikeArtefactView = new TableLikeArtefactView(userConnected);
        MenuBar menuBar = new MenuBar();
        navigator.addView("", new WelcomeView(userConnected, menuBar));
        navigator.addView("Deployment", deploymentView);
        navigator.addView("AssetUpdate", tableLikeArtefactView);


        MenuBar.MenuItem subMenuDeployment = menuBar.addItem("Deployment", null);
        MenuBar.MenuItem submenuAsset = menuBar.addItem("Asset", null);


        subMenuDeployment.addItem("Option 2", null);

        submenuAsset.addItem("Option 4", null);

        subMenuDeployment.addItem("Deploy", (MenuBar.Command) menuItem -> {
            navigator.navigateTo("Deployment");
            deploymentView.refreshCombo();
        });
        submenuAsset.addItem("Asset Modification", (MenuBar.Command) selectedItem -> {
            navigator.navigateTo("AssetUpdate");
            tableLikeArtefactView.refreshList();
        });
        // this.setContent(menuBar);
    }


}