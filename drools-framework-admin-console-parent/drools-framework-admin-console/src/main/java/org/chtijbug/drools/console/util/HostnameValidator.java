package org.chtijbug.drools.console.util;

import com.vaadin.flow.data.validator.RegexpValidator;

public class HostnameValidator extends RegexpValidator {

    private static final String PATTERN ="(^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$|^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$)";

    public HostnameValidator(String errorMessage) {
        super(errorMessage, PATTERN, true);
    }
}