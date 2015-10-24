package org.defascat.tutorial;

import info.magnolia.ui.form.field.definition.RichTextFieldDefinition;

public class ObsceneTextAreaDefinition extends RichTextFieldDefinition {
    private String words;

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}