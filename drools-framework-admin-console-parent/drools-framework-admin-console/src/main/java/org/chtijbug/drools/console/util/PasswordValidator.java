package org.chtijbug.drools.console.util;

import com.vaadin.flow.data.validator.RegexpValidator;

public class PasswordValidator extends RegexpValidator {

    private static final String PATTERN = "[a-zA-Z0-9_@./*#&+-]{4,30}";

    public PasswordValidator(String errorMessage) {
        super(errorMessage, "[a-zA-Z0-9_@./#*&+-]{4,30}", true);
    }
}
