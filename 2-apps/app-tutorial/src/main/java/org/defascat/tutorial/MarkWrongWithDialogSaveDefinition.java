package org.defascat.tutorial;

import info.magnolia.ui.admincentral.dialog.action.SaveConfigDialogActionDefinition;
import info.magnolia.ui.api.action.ConfiguredActionDefinition;

public class MarkWrongWithDialogSaveDefinition extends SaveConfigDialogActionDefinition {
    public MarkWrongWithDialogSaveDefinition() {
        setImplementationClass(MarkWrongWithDialogSaveAction.class);
    } 
}