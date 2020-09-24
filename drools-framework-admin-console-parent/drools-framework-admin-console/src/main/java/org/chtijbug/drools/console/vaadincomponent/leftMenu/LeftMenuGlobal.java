package org.chtijbug.drools.console.vaadincomponent.leftMenu;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@StyleSheet("css/accueil.css")
public class LeftMenuGlobal extends VerticalLayout {

    private Boolean visibility=true;

    private InformationStructure informationStructure;

    private VerticalLayout contentAction;


    public LeftMenuGlobal(){

        setClassName("leftMenu-global-content");

        informationStructure=new InformationStructure();
        add(informationStructure);

        contentAction=new VerticalLayout();
        contentAction.setClassName("leftMenu-content-action");
        add(contentAction);


        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setClassName("leftMenu-global-infoEntreprise");
        add(horizontalLayout);

        Anchor aproposFooter=new Anchor("https://pymma-software.heron-software.com/","A propos");
        aproposFooter.setClassName("footer-button");
        horizontalLayout.add(aproposFooter);

        Anchor contactFooter=new Anchor("https://pymma-software.heron-software.com/contact","Contact");
        contactFooter.setClassName("footer-button");
        horizontalLayout.add(contactFooter);

    }

    public InformationStructure getInformationStructure() {
        return informationStructure;
    }

    public void setInformationStructure(InformationStructure informationStructure) {
        this.informationStructure = informationStructure;
    }

    public VerticalLayout getContentAction() {
        return contentAction;
    }

    public void setContentAction(VerticalLayout contentAction) {
        this.contentAction = contentAction;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }
}
