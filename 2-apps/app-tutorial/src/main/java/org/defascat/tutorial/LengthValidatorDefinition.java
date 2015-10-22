package org.defascat.tutorial;

import info.magnolia.ui.form.validator.definition.ConfiguredFieldValidatorDefinition;

public class LengthValidatorDefinition extends ConfiguredFieldValidatorDefinition {
    private int length;
    
    public LengthValidatorDefinition() {
        setFactoryClass(LengthValidatorFactory.class);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}