package org.chtijbug.drools.console;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


@SuppressWarnings("serial")
public class DroolsAdminConsoleMainView extends VerticalLayout {


    private ContextMenu contextMenu;

    private VerticalLayout actionView;




    public DroolsAdminConsoleMainView() {


        contextMenu = new ContextMenu();
        this.add(contextMenu);
        Component target = this;
        contextMenu.setTarget(target);

        contextMenu.addItem("Deployment",
                e -> {
                    getUI().get().navigate("Deployment");
                });

        contextMenu.addItem("AssetUpdate",
                e -> {
                    getUI().get().navigate("AssetUpdate");

                });

        actionView = new VerticalLayout();

        //this.add(actionView);
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }

    public VerticalLayout getActionView() {
        return actionView;
    }

    public void setActionView(VerticalLayout actionView) {
        this.add(actionView);
        this.actionView = actionView;
    }


}