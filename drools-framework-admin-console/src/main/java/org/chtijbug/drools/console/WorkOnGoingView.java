package org.chtijbug.drools.console;

import com.vaadin.ui.PopupView;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class WorkOnGoingView extends PopupView {

    private Table table;


    public WorkOnGoingView() {
        super("Logging", new VerticalLayout());


        //this.

        VerticalLayout verticalLayout = (VerticalLayout) this.getContent().getPopupComponent();
        this.table = new Table("Logging");
        table = new Table();
        table.setSizeFull();
        table.setPageLength(0);
        table.setSelectable(false);
        table.setColumnCollapsingAllowed(false);
        table.setColumnReorderingAllowed(false);
        table.setImmediate(true);
        table.setNullSelectionAllowed(false);
        table.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        table.addContainerProperty("Message", String.class, null);
        verticalLayout.addComponent(table);
    }

    public void addRow(String textToAdd) {
        String[] cells = new String[1];
        cells[0] = textToAdd;
        table.addItem(cells, "Message");
    }
}
