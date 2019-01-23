package org.chtijbug.drools.console.vaadinComponent.menu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@StyleSheet("css/accueil.css")
public class MenuSecondaireAssets extends HorizontalLayout {

    private Button details;

    public MenuSecondaireAssets(){
        setVisible(false);

        setClassName("menu-secondaire-content");

        details=new Button("Détails", VaadinIcon.INFO.create());
        details.setClassName("menu-secondaire-button");
        add(details);
        details.addClickListener(buttonClickEvent -> {
            getUI().get().navigate("AssetDetail");
        });
    }

    public Button getDetails() {
        return details;
    }

    public void setDetails(Button details) {
        this.details = details;
    }
}
