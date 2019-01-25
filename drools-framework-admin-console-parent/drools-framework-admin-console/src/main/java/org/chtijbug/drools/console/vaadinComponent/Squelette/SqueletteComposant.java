package org.chtijbug.drools.console.vaadinComponent.Squelette;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import org.chtijbug.drools.console.service.UserConnectedService;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.vaadinComponent.componentView.ConsoleDeploy;
import org.chtijbug.drools.console.vaadinComponent.leftMenu.LeftMenuGlobal;
import org.chtijbug.drools.console.vaadinComponent.menu.MenuPrincipal;
import org.chtijbug.drools.console.vaadinComponent.menu.MenuScondaireDeployement;
import org.chtijbug.drools.console.vaadinComponent.menu.MenuSecondaireAssets;
import org.chtijbug.drools.console.vaadinComponent.menu.MenuSecondaireInfoUser;
import org.chtijbug.drools.console.view.DeploymentView;

@StyleSheet("css/accueil.css")
public class SqueletteComposant extends VerticalLayout {

    private MenuPrincipal menuPrincipal;

    private LeftMenuGlobal leftMenuGlobal;

    private MenuScondaireDeployement menuScondaireDeployement;

    private MenuSecondaireInfoUser menuSecondaireInfoUser;

    private MenuSecondaireAssets menuSecondaireAssets;

    private VerticalLayout content;

    private VerticalLayout infoPage;

    private UserConnectedService userConnectedService;

    private ConsoleDeploy consoleDeploy;

    public SqueletteComposant(){

        userConnectedService= AppContext.getApplicationContext().getBean(UserConnectedService.class);

        if(userConnectedService.getUserConnected()!=null) {

            setClassName("squelette-composant-contentAll");

            menuPrincipal = new MenuPrincipal(this);
            add(menuPrincipal);

            HorizontalLayout horizontalLayout = new HorizontalLayout();
            horizontalLayout.setClassName("squelette-component-horizontal");
            add(horizontalLayout);

            content = new VerticalLayout();
            content.setClassName("squelette-component-content");

            VerticalLayout tmp=new VerticalLayout();
            tmp.setClassName("squelette-menu-secondaire");
            content.add(tmp);

            menuScondaireDeployement = new MenuScondaireDeployement(this);
            tmp.add(menuScondaireDeployement);

            menuSecondaireInfoUser = new MenuSecondaireInfoUser();
            tmp.add(menuSecondaireInfoUser);

            menuSecondaireAssets = new MenuSecondaireAssets(this);
            tmp.add(menuSecondaireAssets);

            infoPage = new VerticalLayout();
            infoPage.setClassName("squelette-component-infoPage");
            content.add(infoPage);


            consoleDeploy=new ConsoleDeploy();


            content.add(consoleDeploy);

            leftMenuGlobal = new LeftMenuGlobal();
            horizontalLayout.add(leftMenuGlobal);

            horizontalLayout.add(content);
        }
    }

    public void navigate(VerticalLayout verticalLayout,String pageName,VerticalLayout contentAction){
        leftMenuGlobal.getInformationStructure().getNomPage().setText(pageName);

        leftMenuGlobal.getContentAction().removeAll();
        leftMenuGlobal.getContentAction().add(contentAction);

        infoPage.removeAll();
        infoPage.add(verticalLayout);

        if(pageName.equals(DeploymentView.pageName)){
            consoleDeploy.setVisible(true);
        }
    }

    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }

    public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }

    public LeftMenuGlobal getLeftMenuGlobal() {
        return leftMenuGlobal;
    }

    public MenuSecondaireAssets getMenuSecondaireAssets() {
        return menuSecondaireAssets;
    }

    public void setMenuSecondaireAssets(MenuSecondaireAssets menuSecondaireAssets) {
        this.menuSecondaireAssets = menuSecondaireAssets;
    }

    public void setLeftMenuGlobal(LeftMenuGlobal leftMenuGlobal) {
        this.leftMenuGlobal = leftMenuGlobal;
    }

    public MenuScondaireDeployement getMenuScondaireDeployement() {
        return menuScondaireDeployement;
    }

    public void setMenuScondaireDeployement(MenuScondaireDeployement menuScondaireDeployement) {
        this.menuScondaireDeployement = menuScondaireDeployement;
    }

    public MenuSecondaireInfoUser getMenuSecondaireInfoUser() {
        return menuSecondaireInfoUser;
    }

    public void setMenuSecondaireInfoUser(MenuSecondaireInfoUser menuSecondaireInfoUser) {
        this.menuSecondaireInfoUser = menuSecondaireInfoUser;
    }

    public VerticalLayout getContent() {
        return content;
    }

    public void setContent(VerticalLayout content) {
        this.content = content;
    }

    public VerticalLayout getInfoPage() {
        return infoPage;
    }

    public void setInfoPage(VerticalLayout infoPage) {
        this.infoPage = infoPage;
    }

    public UserConnectedService getUserConnectedService() {
        return userConnectedService;
    }

    public void setUserConnectedService(UserConnectedService userConnectedService) {
        this.userConnectedService = userConnectedService;
    }

    public ConsoleDeploy getConsoleDeploy() {
        return consoleDeploy;
    }

    public void setConsoleDeploy(ConsoleDeploy consoleDeploy) {
        this.consoleDeploy = consoleDeploy;
    }
}
