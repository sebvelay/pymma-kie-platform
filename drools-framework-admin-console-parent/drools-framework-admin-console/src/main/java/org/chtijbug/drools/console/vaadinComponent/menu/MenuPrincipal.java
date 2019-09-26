package org.chtijbug.drools.console.vaadinComponent.menu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.server.InputStreamFactory;
import com.vaadin.flow.server.StreamResource;
import org.chtijbug.drools.console.service.UserConnectedService;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.vaadinComponent.Squelette.SqueletteComposant;

import java.io.InputStream;


@StyleSheet("css/accueil.css")
public class MenuPrincipal extends HorizontalLayout {

    //COMPONENT

    private Image logo;

    private Button deployement;

    private Button assets;

    private Button runtimes;

    private Button infoUser;

    private Button hamburger;

    private Button logging;

    private Button administration;

    //METIER

    private UserConnectedService userConnectedService;

    public MenuPrincipal(SqueletteComposant squeletteComposant){

        userConnectedService= AppContext.getApplicationContext().getBean(UserConnectedService.class);

        addClassName("menu-principal-menubar-content");

        InputStreamFactory inputStreamFactory=new InputStreamFactory() {
            @Override
            public InputStream createInputStream() {
                return getClass().getResourceAsStream("/images/pymma.png");
            }
        };

        StreamResource str=new StreamResource("logo",inputStreamFactory);

        logo=new Image(str,"");
        logo.setClassName("menu-principal-logoPresentation");

        Icon icon=VaadinIcon.MENU.create();
        icon.setClassName("icon-menuPrincipal");


        hamburger=new Button("",icon);
        hamburger.setClassName("hamburger");
        add(hamburger);

        hamburger.addClickListener(buttonClickEvent -> {
            if(squeletteComposant.getLeftMenuGlobal().getVisibility()) {
                squeletteComposant.getLeftMenuGlobal().setVisible(false);
                squeletteComposant.getLeftMenuGlobal().setVisibility(false);
            }else {
                squeletteComposant.getLeftMenuGlobal().setVisible(true);
                squeletteComposant.getLeftMenuGlobal().setVisibility(true);
            }
        });

        deployement=new Button("Deployment");
        deployement.setClassName("menu-principal-button");
        add(deployement);
        deployement.addClickListener(buttonClickEvent -> {

            if(!isActive(deployement)) {
                active(deployement);
                squeletteComposant.getMenuScondaireDeployement().setVisible(true);
                squeletteComposant.getMenuSecondaireInfoUser().setVisible(false);
                squeletteComposant.getMenuSecondaireAssets().setVisible(false);
                squeletteComposant.getMenuSecondaireRuntime().setVisible(false);
                squeletteComposant.getMenuSecondaireLogging().setVisible(false);

            }else {
                removeActive(deployement);
                squeletteComposant.getMenuScondaireDeployement().setVisible(false);
                squeletteComposant.getMenuSecondaireInfoUser().setVisible(false);
                squeletteComposant.getMenuSecondaireAssets().setVisible(false);
                squeletteComposant.getMenuSecondaireLogging().setVisible(false);

                squeletteComposant.getMenuSecondaireRuntime().setVisible(false);
            }
        });

        assets=new Button("Assets");
        assets.setClassName("menu-principal-button");
        add(assets);
        assets.addClickListener(buttonClickEvent -> {

            if(!isActive(assets)) {
                active(assets);
                squeletteComposant.getMenuScondaireDeployement().setVisible(false);
                squeletteComposant.getMenuSecondaireInfoUser().setVisible(false);
                squeletteComposant.getMenuSecondaireAssets().setVisible(true);
                squeletteComposant.getMenuSecondaireLogging().setVisible(false);

                squeletteComposant.getMenuSecondaireRuntime().setVisible(false);

            }else {
                removeActive(assets);
                squeletteComposant.getMenuScondaireDeployement().setVisible(false);
                squeletteComposant.getMenuSecondaireInfoUser().setVisible(false);
                squeletteComposant.getMenuSecondaireLogging().setVisible(false);

                squeletteComposant.getMenuSecondaireAssets().setVisible(false);
                squeletteComposant.getMenuSecondaireRuntime().setVisible(false);
            }
        });

        runtimes=new Button("Runtimes");
        runtimes.setClassName("menu-principal-button");
        add(runtimes);
        runtimes.addClickListener(buttonClickEvent -> {

            if(!isActive(runtimes)) {
                active(runtimes);
                squeletteComposant.getMenuScondaireDeployement().setVisible(false);
                squeletteComposant.getMenuSecondaireInfoUser().setVisible(false);
                squeletteComposant.getMenuSecondaireAssets().setVisible(false);
                squeletteComposant.getMenuSecondaireLogging().setVisible(false);

                squeletteComposant.getMenuSecondaireRuntime().setVisible(true);
            }else {
                removeActive(runtimes);
                squeletteComposant.getMenuScondaireDeployement().setVisible(false);
                squeletteComposant.getMenuSecondaireInfoUser().setVisible(false);
                squeletteComposant.getMenuSecondaireAssets().setVisible(false);
                squeletteComposant.getMenuSecondaireRuntime().setVisible(false);
                squeletteComposant.getMenuSecondaireLogging().setVisible(false);

            }
        });

        logging=new Button("Logging");
        logging.setClassName("menu-principal-button");
        add(logging);
        logging.addClickListener(buttonClickEvent -> {

            if(!isActive(logging)) {
                active(logging);
                squeletteComposant.getMenuScondaireDeployement().setVisible(false);
                squeletteComposant.getMenuSecondaireInfoUser().setVisible(false);
                squeletteComposant.getMenuSecondaireAssets().setVisible(false);
                squeletteComposant.getMenuSecondaireRuntime().setVisible(false);
                squeletteComposant.getMenuSecondaireLogging().setVisible(true);

            }else {
                removeActive(logging);
                squeletteComposant.getMenuScondaireDeployement().setVisible(false);
                squeletteComposant.getMenuSecondaireInfoUser().setVisible(false);
                squeletteComposant.getMenuSecondaireAssets().setVisible(false);
                squeletteComposant.getMenuSecondaireRuntime().setVisible(false);
                squeletteComposant.getMenuSecondaireLogging().setVisible(false);

            }
        });

        add(logo);

        infoUser=new Button(userConnectedService.getUserConnected().getUserName(), VaadinIcon.USER.create());
        infoUser.setClassName("menu-principal-button-user");
        infoUser.addClickListener(buttonClickEvent -> {
            if(!isActive(infoUser)) {
                active(infoUser);
                squeletteComposant.getMenuScondaireDeployement().setVisible(false);
                squeletteComposant.getMenuSecondaireInfoUser().setVisible(true);
                squeletteComposant.getMenuSecondaireAssets().setVisible(false);
                squeletteComposant.getMenuSecondaireRuntime().setVisible(false);
                squeletteComposant.getMenuSecondaireLogging().setVisible(false);

            }else {
                removeActive(infoUser);
                squeletteComposant.getMenuScondaireDeployement().setVisible(false);
                squeletteComposant.getMenuSecondaireInfoUser().setVisible(false);
                squeletteComposant.getMenuSecondaireAssets().setVisible(false);
                squeletteComposant.getMenuSecondaireRuntime().setVisible(false);
                squeletteComposant.getMenuSecondaireLogging().setVisible(false);


            }
        });

        add(infoUser);

    }
    private boolean isActive(Button button){
        return button.getClassNames().contains("active");
    }
    private void removeActive(Button button) {

        if(button.getClassNames().contains("active")){
            button.getClassNames().remove("active");
        }
    }
    private void active(Button button){
        removeActive(infoUser);
        removeActive(deployement);
        removeActive(assets);
        removeActive(runtimes);
        removeActive(logging);

        button.getClassNames().add("active");
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public Button getDeployement() {
        return deployement;
    }

    public void setDeployement(Button deployement) {
        this.deployement = deployement;
    }

    public Button getInfoUser() {
        return infoUser;
    }

    public void setInfoUser(Button infoUser) {
        this.infoUser = infoUser;
    }

    public UserConnectedService getUserConnectedService() {
        return userConnectedService;
    }

    public void setUserConnectedService(UserConnectedService userConnectedService) {
        this.userConnectedService = userConnectedService;
    }
}
