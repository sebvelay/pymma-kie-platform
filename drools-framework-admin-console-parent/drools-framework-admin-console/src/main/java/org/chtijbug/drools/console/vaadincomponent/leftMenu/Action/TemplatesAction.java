package org.chtijbug.drools.console.vaadincomponent.leftMenu.Action;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.chtijbug.drools.console.vaadincomponent.componentperso.ComboBoxPerso;
import org.chtijbug.drools.console.view.TemplateView;
import org.chtijbug.guvnor.server.jaxrs.model.PlatformProjectData;

public class TemplatesAction extends VerticalLayout {

    private Button refresh;

    private Button duplicate;

    private Button edit;

    private ComboBoxPerso<PlatformProjectData> spaceSelection;

    public TemplatesAction(TemplateView templateView){

        setClassName("leftMenu-global-action");


        spaceSelection = new ComboBoxPerso<>("Project",VaadinIcon.SEARCH.create());
        spaceSelection.getComboBox().setItems(templateView.getUserConnectedService().getUserConnected().getProjectResponses());
        spaceSelection.getComboBox().setItemLabelGenerator(PlatformProjectData::getName);
        spaceSelection.getComboBox().addValueChangeListener(valueChangeEvent -> {
            templateView.setDataProvider(spaceSelection.getComboBox());
        });
        add(spaceSelection);


        refresh =new Button("Refresh", VaadinIcon.ROTATE_LEFT.create());
        refresh.setClassName("leftMenu-global-button");
        add(refresh);
        refresh.addClickListener(buttonClickEvent -> {
            active(refresh);
            templateView.refreshList(spaceSelection.getComboBox());
        });

        duplicate =new Button("Duplicate",VaadinIcon.TOOLS.create());
        duplicate.setClassName("leftMenu-global-button");
        duplicate.setEnabled(false);
        add(duplicate);
        duplicate.addClickListener(buttonClickEvent -> {
            active(duplicate);
            templateView.duplicate();

        });
        edit =new Button("Edit",VaadinIcon.EDIT.create());
        edit.setEnabled(false);
        edit.setClassName("leftMenu-global-button");
        add(edit);
        edit.addClickListener(buttonClickEvent -> {
            active(edit);
            templateView.edit(spaceSelection.getComboBox());
        });
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
        removeActive(refresh);
        removeActive(duplicate);
        removeActive(edit);
        button.getClassNames().add("active");
    }
    public Button getRefresh() {
        return refresh;
    }

    public void setRefresh(Button refresh) {
        this.refresh = refresh;
    }

    public Button getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(Button duplicate) {
        this.duplicate = duplicate;
    }

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }
}
