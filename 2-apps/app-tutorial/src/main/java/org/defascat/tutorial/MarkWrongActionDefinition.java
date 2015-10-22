package org.defascat.tutorial;

import info.magnolia.ui.contentapp.detail.action.AbstractItemActionDefinition;

public class MarkWrongActionDefinition extends AbstractItemActionDefinition {
    private String reason;
    
    public MarkWrongActionDefinition() {
        setImplementationClass(MarkWrongAction.class);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}