package org.defascat.tutorial;

import com.vaadin.data.validator.AbstractStringValidator;

public class LengthValidator extends AbstractStringValidator {
    private final int length;
    
    public LengthValidator(int length, String errorMessage) {
        super(errorMessage);
        this.length = length;
    }
    
    @Override
    protected boolean isValidValue(String value) {
        return value != null && value.trim().length() >= length;
    }
}