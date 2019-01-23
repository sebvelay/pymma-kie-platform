package org.chtijbug.drools.console.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.InputStreamFactory;
import com.vaadin.flow.server.StreamResource;
import org.chtijbug.drools.console.vaadinComponent.login.LoginComponent;
import org.chtijbug.drools.console.vaadinComponent.login.TextInfoComponent;

import java.io.InputStream;

@Route("")
@StyleSheet("css/accueil.css")
public class LoginView extends VerticalLayout {

    private TextInfoComponent textInfoComponent;

    private LoginComponent loginComponent;

    private Image logo;

    private VerticalLayout verticalLayout;

    public LoginView(){

        setClassName("global-layout");


        HorizontalLayout contentImage=new HorizontalLayout();
        contentImage.setClassName("content-image");
        add(contentImage);

        verticalLayout=new VerticalLayout();
        verticalLayout.setClassName("login-global");
        add(verticalLayout);

        HorizontalLayout contentImage2=new HorizontalLayout();
        contentImage2.setClassName("content-image2");
        add(contentImage2);

        Anchor aproposFooter=new Anchor("https://pymma-software.heron-software.com/","A propos");
        aproposFooter.setClassName("footer-button");
        contentImage2.add(aproposFooter);

        Anchor contactFooter=new Anchor("https://pymma-software.heron-software.com/contact","Contact");
        contactFooter.setClassName("footer-button");
        contentImage2.add(contactFooter);

        InputStreamFactory inputStreamFactory=new InputStreamFactory() {
            @Override
            public InputStream createInputStream() {
                return getClass().getResourceAsStream("/images/pymma.png");
            }
        };

        StreamResource str=new StreamResource("logo",inputStreamFactory);

        logo=new Image(str,"");
        logo.setClassName("login-logoPresentation");
        contentImage.add(logo);

        HorizontalLayout horizontalLayout=new HorizontalLayout();
        horizontalLayout.setClassName("login-global-content");
        verticalLayout.add(horizontalLayout);

        textInfoComponent=new TextInfoComponent();
        horizontalLayout.add(textInfoComponent);

        loginComponent=new LoginComponent();
        horizontalLayout.add(loginComponent);
    }

    public TextInfoComponent getTextInfoComponent() {
        return textInfoComponent;
    }

    public void setTextInfoComponent(TextInfoComponent textInfoComponent) {
        this.textInfoComponent = textInfoComponent;
    }

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

    public void setLoginComponent(LoginComponent loginComponent) {
        this.loginComponent = loginComponent;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }
}
