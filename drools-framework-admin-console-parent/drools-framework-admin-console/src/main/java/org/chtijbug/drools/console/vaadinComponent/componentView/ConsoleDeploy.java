package org.chtijbug.drools.console.vaadinComponent.componentView;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.page.Push;
import org.chtijbug.drools.console.AddLog;

import java.util.Date;

public class ConsoleDeploy extends HorizontalLayout {

    public VerticalLayout outilsBar;

    public VerticalLayout content;

    public HorizontalLayout titleContent;

    public VerticalLayout logContent;

    public Button menu;

    public Button clear;

    private Label title;

    public ConsoleDeploy(){

        setClassName("console-content-all");
        setVisible(false);

        outilsBar=new VerticalLayout();
        outilsBar.setClassName("console-outilsBar-content");
        add(outilsBar);

        menu=new Button("",VaadinIcon.MENU.create());
        menu.setClassName("console-button");
        menu.addClickListener(buttonClickEvent -> {
            if(isActive()){
                removeActive();
                content.setVisible(false);
            }else {
                active();
                content.setVisible(true);
            }
        });
        outilsBar.add(menu);

        clear=new Button("", VaadinIcon.PANEL.create());
        clear.setClassName("console-button");
        clear.addClickListener(buttonClickEvent -> {
           logContent.removeAll();
        });
        outilsBar.add(clear);

        content=new VerticalLayout();
        content.setClassName("console-content");
        add(content);
        content.setVisible(false);


        titleContent=new HorizontalLayout();
        titleContent.setClassName("console-content-title");
        content.add(titleContent);

        title=new Label("Console : ");
        title.setClassName("console-title");
        titleContent.add(title);

        logContent=new VerticalLayout();
        logContent.setClassName("console-log-content");
        content.add(logContent);
    }
    private boolean isActive(){
        return getClassNames().contains("active");
    }

    private void removeActive() {

        if(getClassNames().contains("active")){
            getClassNames().remove("active");
        }
    }
    private void active(){
        removeActive();

        getClassNames().add("active");
    }

    public void setTtile(String nameProject){
        title.setText("Console : "+nameProject);
    }

    public VerticalLayout getLogContent() {
        return logContent;
    }

    public void setLogContent(VerticalLayout logContent) {
        this.logContent = logContent;
    }
}
