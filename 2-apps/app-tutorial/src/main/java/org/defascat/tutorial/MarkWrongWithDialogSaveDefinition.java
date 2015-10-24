package org.defascat.tutorial;

import info.magnolia.ui.admincentral.dialog.action.SaveConfigDialogActionDefinition;

public class MarkWrongWithDialogSaveDefinition extends SaveConfigDialogActionDefinition {
    public MarkWrongWithDialogSaveDefinition() {
        setImplementationClass(MarkWrongWithDialogSaveAction.class);
    } 
}