package org.chtijbug.drools.console.service.model.kie;

public class KieServerJobStatus {
    private String type;
    private String msg;
    private String result;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("KieServerJobStatus{");
        sb.append("type='").append(type).append('\'');
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", result='").append(result).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
