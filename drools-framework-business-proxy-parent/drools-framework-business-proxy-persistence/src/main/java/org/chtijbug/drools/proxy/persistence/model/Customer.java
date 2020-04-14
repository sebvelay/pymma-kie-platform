package org.chtijbug.drools.proxy.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer {

    @Id
    @Indexed
    private String ID;

    @Indexed(unique = true)
    private String name;

    @DBRef
    private KieWorkbench kieWorkbench;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KieWorkbench getKieWorkbench() {
        return kieWorkbench;
    }

    public void setKieWorkbench(KieWorkbench kieWorkbench) {
        this.kieWorkbench = kieWorkbench;
    }
}
