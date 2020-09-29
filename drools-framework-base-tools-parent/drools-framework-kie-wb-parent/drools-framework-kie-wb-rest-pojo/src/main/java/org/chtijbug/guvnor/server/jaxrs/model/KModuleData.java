package org.chtijbug.guvnor.server.jaxrs.model;

public class KModuleData {

    private String kbaseToInclude;

    private String kbase;

    public KModuleData() {
    }

    public String getKbaseToInclude() {
        return kbaseToInclude;
    }

    public void setKbaseToInclude(String kbaseToInclude) {
        this.kbaseToInclude = kbaseToInclude;
    }

    public String getKbase() {
        return kbase;
    }

    public void setKbase(String kbase) {
        this.kbase = kbase;
    }
}
