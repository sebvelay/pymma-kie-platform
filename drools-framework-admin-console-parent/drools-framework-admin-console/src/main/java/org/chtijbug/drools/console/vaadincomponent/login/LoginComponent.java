package org.chtijbug.drools.console.vaadincomponent.login;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@StyleSheet("css/accueil.css")
public class LoginComponent extends HorizontalLayout {

    private FormulaireComposant layoutLeft;

    private FormulaireInfoComposant layoutRight;

    public LoginComponent(){

        setClassName("login-formulaire-globalLayout");

        layoutLeft=new FormulaireComposant();
        layoutRight=new FormulaireInfoComposant();

        add(layoutLeft);
        add(layoutRight);
    }

    public FormulaireComposant getLayoutLeft() {
        return layoutLeft;
    }

    public void setLayoutLeft(FormulaireComposant layoutLeft) {
        this.layoutLeft = layoutLeft;
    }

    public FormulaireInfoComposant getLayoutRight() {
        return layoutRight;
    }

    public void setLayoutRight(FormulaireInfoComposant layoutRight) {
        this.layoutRight = layoutRight;
    }
}
