package org.chtijbug.drools.console.service.model;

public class ReturnPerso<T> {

    private Boolean aBoolean;

    private String error;

    private T body;

    public ReturnPerso(Boolean aBoolean, String error,T body) {
        this.aBoolean = aBoolean;
        this.error = error;
        this.body=body;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
