package org.chtijbug.drools.console.vaadinComponent.leftMenu.Action;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.chtijbug.drools.console.vaadinComponent.Squelette.SqueletteComposant;

public class RuntimesAction extends VerticalLayout {

  //  private Button addRuntime;


    public RuntimesAction(SqueletteComposant squeletteComposant){

        setClassName("leftMenu-global-action");

/**
        addRuntime=new Button("add runtime", VaadinIcon.PLUS.create());
        addRuntime.setClassName("leftMenu-global-button");
        add(addRuntime);

        DialogPerso dialog=new DialogPerso();
        dialog.add(new AddRuntime(dialog,squeletteComposant));

        addRuntime.addClickListener(buttonClickEvent -> {
            active(addRuntime);

            dialog.open();
        });
 **/
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
       // removeActive(addRuntime);
        button.getClassNames().add("active");
    }
}
