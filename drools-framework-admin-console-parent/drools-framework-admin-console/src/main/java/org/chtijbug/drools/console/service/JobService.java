package org.chtijbug.drools.console.service;

import com.vaadin.flow.component.UI;
import org.chtijbug.drools.console.AddLog;
import org.chtijbug.drools.console.service.model.kie.JobStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn("applicationContext")
public class JobService {

    private static final Logger logger = LoggerFactory.getLogger(JobService.class);

    @Autowired
    private KieRepositoryService kieRepositoryService;


    public void executeWrite(String url, String username, String password, AddLog workOnGoingView, String jobID, UI ui) {

        String isJobDone = "NO";
        while ("NO".equals(isJobDone)) {
            JobStatus jobStatus = kieRepositoryService.getStatusJobID(url,
                    username,
                    password, jobID);
            if ("DUPLICATE_RESOURCE".equals(jobStatus.getStatus())
                    || "SUCCESS".equals(jobStatus.getStatus())) {
                isJobDone = "YES";
                if (ui!= null) {
                    workOnGoingView.addRow("JobID=" + jobID + " finished", ui);
                }
            } else if ("ACCEPTED".equals(jobStatus.getStatus())
                    || ("APPROVED".equals(jobStatus.getStatus()))) {
                try {
                    synchronized (this) {
                        if (ui!= null) {
                            workOnGoingView.addRow("JobID=" + jobID + " not yet finished", ui);
                        }
                        this.wait(1000);
                    }
                } catch (InterruptedException e) {
                    logger.error("executeWrite", e);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
