package org.chtijbug.drools.console.view;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.chtijbug.drools.console.vaadinComponent.componentView.GridLogging;
import org.chtijbug.drools.console.vaadinComponent.leftMenu.Action.ActionLogging;

@StyleSheet("css/accueil.css")
public class LoggingView extends VerticalLayout {

    public static final String pageName="Logging";

    private Label title;

    private GridLogging gridLogging;

    private ActionLogging actionLogging;

    public LoggingView(){

        title=new Label("Logging : ");

        add(title);

        gridLogging=new GridLogging();

        add(gridLogging);


        gridLogging.addSelectionListener(selectionEvent -> {

            if(selectionEvent.getFirstSelectedItem().isPresent()) {
                actionLogging.getViewAction().setEnabled(true);
            }else {
                actionLogging.getViewAction().setEnabled(false);
            }
        });
    }

    public static String getPageName() {
        return pageName;
    }

    public GridLogging getGridLogging() {
        return gridLogging;
    }

    public void setGridLogging(GridLogging gridLogging) {
        this.gridLogging = gridLogging;
    }

    public ActionLogging getActionLogging() {
        return actionLogging;
    }

    public void setActionLogging(ActionLogging actionLogging) {
        this.actionLogging = actionLogging;
    }

    public Label getTitle() {
        return title;
    }

    public void setTitle(Label title) {
        this.title = title;
    }
}
