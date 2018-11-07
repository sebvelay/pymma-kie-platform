package org.chtijbug.drools.console.service.model.kie;

public class GitRepositoryResponse {
    private String jobId;
    private String status;
    private GitRepository repository;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GitRepository getRepository() {
        return repository;
    }

    public void setRepository(GitRepository repository) {
        this.repository = repository;
    }
}
