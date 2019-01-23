package org.chtijbug.drools.console.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.chtijbug.drools.console.vaadinComponent.ComponentPerso.TextFieldPerso;

@StyleSheet("css/accueil.css")
public class AddRuntime extends VerticalLayout {

    private TextFieldPerso host;

    private TextFieldPerso port;

    private TextFieldPerso runtimeName;

    private Button testConnexion;

    private Label label;

    public AddRuntime(Dialog dialog){

        setClassName("creation-runtime-content");

        label=new Label("Add Runtime");
        label.setClassName("creation-runtime-title");
        add(label);

        runtimeName=new TextFieldPerso("Runtime name","", VaadinIcon.USER.create());
        runtimeName.getTextField().setRequired(true);
        runtimeName.getTextField().setValueChangeMode(ValueChangeMode.EAGER);
        runtimeName.getTextField().addValueChangeListener(textFieldStringComponentValueChangeEvent -> {
           verify();
        });
        add(runtimeName);

        host=new TextFieldPerso("Host","111.111.1.111",VaadinIcon.HARDDRIVE.create());
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
           dialog.close();
        });
    }

    public void verify(){
        if(runtimeName.getTextField().isInvalid()||runtimeName.getTextField().getValue().isEmpty()||runtimeName.getTextField().getValue()==null&&
                port.getTextField().isInvalid()||port.getTextField().getValue().isEmpty()||port.getTextField().getValue()==null&&
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

    public TextFieldPerso getRuntimeName() {
        return runtimeName;
    }

    public void setRuntimeName(TextFieldPerso runtimeName) {
        this.runtimeName = runtimeName;
    }

    public Button getTestConnexion() {
        return testConnexion;
    }

    public void setTestConnexion(Button testConnexion) {
        this.testConnexion = testConnexion;
    }
}
