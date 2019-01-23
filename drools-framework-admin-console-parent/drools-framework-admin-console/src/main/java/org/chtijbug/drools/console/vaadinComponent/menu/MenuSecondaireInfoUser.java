package org.chtijbug.drools.console.vaadinComponent.menu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.chtijbug.drools.console.service.UserConnectedService;
import org.chtijbug.drools.console.service.util.AppContext;

@StyleSheet("css/accueil.css")
public class MenuSecondaireInfoUser extends HorizontalLayout {

    private Button infoUser;

    private Button disconnect;

    private UserConnectedService userConnectedService;

    public MenuSecondaireInfoUser() {
        setVisible(false);

        userConnectedService= AppContext.getApplicationContext().getBean(UserConnectedService.class);

        setClassName("menu-secondaire-content");

        infoUser=new Button("Information User", VaadinIcon.INFO.create());
        infoUser.setClassName("menu-secondaire-button");
        add(infoUser);

        disconnect=new Button("Disconnect",VaadinIcon.SIGN_OUT.create());
        disconnect.setClassName("menu-secondaire-button");
        disconnect.addClickListener(buttonClickEvent -> {
            userConnectedService.disconnect();
            getUI().get().navigate("");
        });
        add(disconnect);

    }

    public Button getInfoUser() {
        return infoUser;
    }

    public void setInfoUser(Button infoUser) {
        this.infoUser = infoUser;
    }

    public Button getDisconnect() {
        return disconnect;
    }

    public void setDisconnect(Button disconnect) {
        this.disconnect = disconnect;
    }
}
