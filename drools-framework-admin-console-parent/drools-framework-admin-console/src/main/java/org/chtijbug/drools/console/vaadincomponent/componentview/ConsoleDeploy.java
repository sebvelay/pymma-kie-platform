package org.chtijbug.drools.console.vaadincomponent.componentview;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ConsoleDeploy extends HorizontalLayout {

    private static String activeClass = "active";

    private transient VerticalLayout outilsBar;

    private transient VerticalLayout content;

    private transient HorizontalLayout titleContent;

    private transient VerticalLayout logContent;

    private transient Button menu;

    private transient Button clear;

    private transient Label title;

    public ConsoleDeploy() {

        setClassName("console-content-all");
        setVisible(false);

        outilsBar = new VerticalLayout();
        outilsBar.setClassName("console-outilsBar-content");
        add(outilsBar);

        menu = new Button("", VaadinIcon.MENU.create());
        menu.setClassName("console-button");
        menu.addClickListener(buttonClickEvent -> {
            if (isActive()) {
                removeActive();
                content.setVisible(false);
            } else {
                active();
                content.setVisible(true);
            }
        });
        outilsBar.add(menu);

        clear = new Button("", VaadinIcon.PANEL.create());
        clear.setClassName("console-button");
        clear.addClickListener(buttonClickEvent ->
                logContent.removeAll()
        );
        outilsBar.add(clear);

        content = new VerticalLayout();
        content.setClassName("console-content");
        add(content);
        content.setVisible(false);


        titleContent = new HorizontalLayout();
        titleContent.setClassName("console-content-title");
        content.add(titleContent);

        title = new Label("Console : ");
        title.setClassName("console-title");
        titleContent.add(title);

        logContent = new VerticalLayout();
        logContent.setClassName("console-log-content");
        content.add(logContent);
    }

    private boolean isActive() {
        return getClassNames().contains(activeClass);
    }

    private void removeActive() {

        if (getClassNames().contains(activeClass)) {
            getClassNames().remove(activeClass);
        }
    }

    private void active() {
        removeActive();

        getClassNames().add(activeClass);
    }

    public void setTtile(String nameProject) {
        title.setText("Console : " + nameProject);
    }

    public VerticalLayout getLogContent() {
        return logContent;
    }

    public void setLogContent(VerticalLayout logContent) {
        this.logContent = logContent;
    }
}
