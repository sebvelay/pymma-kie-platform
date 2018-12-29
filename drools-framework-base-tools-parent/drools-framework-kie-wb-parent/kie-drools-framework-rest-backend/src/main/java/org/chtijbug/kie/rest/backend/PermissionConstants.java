package org.chtijbug.kie.rest.backend;

public interface PermissionConstants {

    public static final String REST_ROLE = "rest-all";
    public static final String REST_PROJECT_ROLE = "rest-project";
    public static final String REST_DEPLOYMENT_ROLE = "rest-deployment";
    public static final String REST_PROCESS_ROLE = "rest-process";
    public static final String REST_PROCESS_RO_ROLE = "rest-process-read-only";
    public static final String REST_TASK_ROLE = "rest-task";
    public static final String REST_TASK_RO_ROLE = "rest-task-read-only";
    public static final String REST_QUERY_ROLE = "rest-query";
    public static final String REST_CLIENT_ROLE = "rest-client";
    public static final String ADMIN_ROLE = "admin";
    public static final String ANALYST_ROLE = "analyst";
    public static final String KIEMGMT_ROLE = "kiemgmt";
    public static final String ADMIN_GROUP_ROLE = "admingroup";


    public static String tableauChaine[] = {ADMIN_GROUP_ROLE, KIEMGMT_ROLE, ANALYST_ROLE, ADMIN_ROLE, REST_CLIENT_ROLE, REST_QUERY_ROLE, REST_TASK_RO_ROLE, REST_TASK_ROLE, REST_PROCESS_RO_ROLE, REST_ROLE, REST_PROJECT_ROLE, REST_DEPLOYMENT_ROLE, REST_PROCESS_ROLE};

}
