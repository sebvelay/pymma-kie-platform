package org.chtijbug.drools.console.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.communication.PushMode;
import org.chtijbug.drools.console.vaadincomponent.Squelette.SqueletteComposant;

import java.util.Optional;

@Push(PushMode.AUTOMATIC)
@StyleSheet("css/accueil.css")
@HtmlImport("frontend://styles/shared-styles.html")
@Route("accueil")
public class AccueilView extends SqueletteComposant implements BeforeEnterObserver {


    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        if (getUserConnectedService().getUserConnected()==null) {
            beforeEnterEvent.rerouteTo(LoginView.class);
            Optional<UI> theUI = UI.getCurrent().getUI();
            if (theUI.isPresent()) {
                theUI.get().getPage().executeJavaScript("window.alert($0)", "Session expiré, veuillez-vous reconnecter");
            }
        }

    }
}
