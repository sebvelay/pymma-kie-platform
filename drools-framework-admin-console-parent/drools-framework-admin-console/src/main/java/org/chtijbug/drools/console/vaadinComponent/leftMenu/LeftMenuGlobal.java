package org.chtijbug.drools.console.vaadinComponent.leftMenu;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@StyleSheet("css/accueil.css")
public class LeftMenuGlobal extends VerticalLayout {

    private Button actionOne;

    private Button actionTwo;

    private Boolean visibility=true;


    public LeftMenuGlobal(){

        setClassName("leftMenu-global-content");

        InformationStructure informationStructure=new InformationStructure();
        add(informationStructure);

        VerticalLayout verticalLayout=new VerticalLayout();
        verticalLayout.setClassName("leftMenu-global-action");
        add(verticalLayout);

        actionOne=new Button("ActionOne", VaadinIcon.TOOLS.create());
        actionOne.setClassName("leftMenu-global-button");
        verticalLayout.add(actionOne);
        actionOne.addClickListener(buttonClickEvent -> {
            active(actionOne);
        });

        actionTwo=new Button("ActionTwo",VaadinIcon.TOOLS.create());
        actionTwo.setClassName("leftMenu-global-button");
        verticalLayout.add(actionTwo);
        actionTwo.addClickListener(buttonClickEvent -> {
            active(actionTwo);

        });


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
    private boolean isActive(Button button){
        return button.getClassNames().contains("active");
    }
    private void removeActive(Button button) {

        if(button.getClassNames().contains("active")){
            button.getClassNames().remove("active");
        }
    }
    private void active(Button button){
        removeActive(actionOne);
        removeActive(actionTwo);

        button.getClassNames().add("active");
    }

    public Button getActionOne() {
        return actionOne;
    }

    public void setActionOne(Button actionOne) {
        this.actionOne = actionOne;
    }

    public Button getActionTwo() {
        return actionTwo;
    }

    public void setActionTwo(Button actionTwo) {
        this.actionTwo = actionTwo;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }
}
