package org.chtijbug.drools.console.vaadinComponent.componentView;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.chtijbug.drools.console.service.RuntimeService;
import org.chtijbug.drools.console.service.model.ReturnPerso;
import org.chtijbug.drools.console.service.util.AppContext;
import org.chtijbug.drools.console.vaadinComponent.ComponentPerso.TextFieldPerso;
import org.chtijbug.drools.console.vaadinComponent.Squelette.SqueletteComposant;
import org.chtijbug.drools.proxy.persistence.model.RuntimePersist;
import org.kie.server.api.model.KieServerInfo;

import java.util.List;

@StyleSheet("css/accueil.css")
public class AddRuntime extends VerticalLayout {

    private TextFieldPerso host;

    private TextFieldPerso port;

    private Button testConnexion;

    private Label label;

    private RuntimeService runtimeService;

    public AddRuntime(Dialog dialog, SqueletteComposant squeletteComposant){

        runtimeService = AppContext.getApplicationContext().getBean(RuntimeService.class);

        setClassName("creation-runtime-content");

        label=new Label("Add Runtime");
        label.setClassName("creation-runtime-title");
        add(label);

        host=new TextFieldPerso("Host","http://111.111.1.111",VaadinIcon.HARDDRIVE.create());
        host.getTextField().setRequired(true);
        host.getTextField().setValueChangeMode(ValueChangeMode.EAGER);
        host.getTextField().addValueChangeListener(textFieldStringComponentValueChangeEvent -> {
            verify();
        });

        add(host);

        port=new TextFieldPerso("Port","",VaadinIcon.INBOX.create());
        port.getTextField().setRequired(true);
        port.getTextField().setValueChangeMode(ValueChangeMode.EAGER);
        port.getTextField().addValueChangeListener(textFieldStringComponentValueChangeEvent -> {
            verify();
        });


        add(port);

        testConnexion=new Button("Tester connexion");
        testConnexion.setEnabled(false);
        testConnexion.setClassName("login-application-connexion");
        add(testConnexion);
        testConnexion.addClickListener(buttonClickEvent -> {

            ReturnPerso<KieServerInfo> returnPerso= runtimeService.verifyIfKieServerExist(
                    host.getTextField().getValue()+":"+port.getTextField().getValue()
            );

            if(returnPerso.getaBoolean()){
                Notification.show(returnPerso.getError());
                runtimeService.saveRuntime(new RuntimePersist(
                        returnPerso.getBody().getName(),
                        returnPerso.getBody().getVersion(),host.getTextField().getValue()+":"+port.getTextField().getValue()
                        ));

                List<RuntimePersist> runtimePersists=runtimeService.getRuntimeRepository().findAll();
                squeletteComposant.getLeftMenuGlobal().getInformationStructure().actualiseKieServer(runtimePersists!=null?runtimePersists.size():0);
                dialog.close();
            }else {
                Notification.show(returnPerso.getError());
                testConnexion.setClassName("login-application-connexion-error");
                testConnexion.setIcon(VaadinIcon.ROTATE_LEFT.create());
            }
        });
    }

    public void verify(){
        if(port.getTextField().isInvalid()||port.getTextField().getValue().isEmpty()||port.getTextField().getValue()==null&&
                host.getTextField().isInvalid()||host.getTextField().getValue().isEmpty()||host.getTextField().getValue()==null){
            testConnexion.setEnabled(false);
        }else {
            testConnexion.setEnabled(true);
        }
    }


    public TextFieldPerso getHost() {
        return host;
    }

    public void setHost(TextFieldPerso host) {
        this.host = host;
    }

    public TextFieldPerso getPort() {
        return port;
    }

    public void setPort(TextFieldPerso port) {
        this.port = port;
    }

    public Button getTestConnexion() {
        return testConnexion;
    }

    public void setTestConnexion(Button testConnexion) {
        this.testConnexion = testConnexion;
    }
}
