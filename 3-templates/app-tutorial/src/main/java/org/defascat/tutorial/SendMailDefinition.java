package org.defascat.tutorial;

import info.magnolia.ui.api.action.ConfiguredActionDefinition;

public class SendMailDefinition extends ConfiguredActionDefinition{
    public SendMailDefinition() {
        setImplementationClass(SendMail.class);
    }
}