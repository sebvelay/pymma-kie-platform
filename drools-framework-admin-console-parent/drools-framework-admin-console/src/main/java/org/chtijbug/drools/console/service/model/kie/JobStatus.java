package org.chtijbug.drools.console.service.model.kie;

import java.util.Arrays;
import java.util.Date;

public class JobStatus {
    private String status;
    private String jobId;
    private String spaceName;
    private String projectName;
    private String result;
    private Date lastModified;
    private String detailedResult[];
    private String branchName;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("JobStatus{");
        sb.append("status='").append(status).append('\'');
        sb.append(", jobId='").append(jobId).append('\'');
        sb.append(", spaceName='").append(spaceName).append('\'');
        sb.append(", projectName='").append(projectName).append('\'');
        sb.append(", result='").append(result).append('\'');
        sb.append(", lastModified=").append(lastModified);
        sb.append(", detailedResult=").append(detailedResult == null ? "null" : Arrays.asList(detailedResult).toString());
        sb.append('}');
        return sb.toString();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String[] getDetailedResult() {
        return detailedResult;
    }

    public void setDetailedResult(String[] detailedResult) {
        this.detailedResult = detailedResult;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
