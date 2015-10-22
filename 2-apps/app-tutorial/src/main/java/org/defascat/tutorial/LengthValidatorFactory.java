package org.defascat.tutorial;

import com.vaadin.data.Validator;
import info.magnolia.ui.form.validator.definition.EmailValidatorDefinition;
import info.magnolia.ui.form.validator.factory.AbstractFieldValidatorFactory;

public class LengthValidatorFactory extends AbstractFieldValidatorFactory<LengthValidatorDefinition>{
    public LengthValidatorFactory(LengthValidatorDefinition definition) {
        super(definition);
    }
        
    @Override
    public Validator createValidator() {
        return new LengthValidator(definition.getLength(), definition.getErrorMessage());
    }
}