package org.chtijbug.drools.console.vaadinComponent.Squelette;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.chtijbug.drools.console.vaadinComponent.leftMenu.LeftMenuGlobal;
import org.chtijbug.drools.console.vaadinComponent.menu.MenuPrincipal;
import org.chtijbug.drools.console.vaadinComponent.menu.MenuScondaireDeployement;
import org.chtijbug.drools.console.vaadinComponent.menu.MenuSecondaireInfoUser;

@StyleSheet("css/accueil.css")
public class SqueletteComposant extends VerticalLayout {

    private MenuPrincipal menuPrincipal;

    private LeftMenuGlobal leftMenuGlobal;

    private MenuScondaireDeployement menuScondaireDeployement;

    private MenuSecondaireInfoUser menuSecondaireInfoUser;

    private VerticalLayout content;

    private VerticalLayout infoPage;

    public SqueletteComposant(){
        setClassName("squelette-composant-contentAll");

        menuPrincipal=new MenuPrincipal(this);
        add(menuPrincipal);




        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setClassName("squelette-component-horizontal");
        add(horizontalLayout);

        content=new VerticalLayout();
        content.setClassName("squelette-component-content");

        menuScondaireDeployement=new MenuScondaireDeployement();
        content.add(menuScondaireDeployement);

        menuSecondaireInfoUser=new MenuSecondaireInfoUser();
        content.add(menuSecondaireInfoUser);

        infoPage=new VerticalLayout();
        infoPage.setClassName("squelette-component-infoPage");
        content.add(infoPage);

        leftMenuGlobal=new LeftMenuGlobal();
        horizontalLayout.add(leftMenuGlobal);

        horizontalLayout.add(content);
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
}
