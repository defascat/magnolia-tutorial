package org.defascat.tutorial;

import com.vaadin.data.Item;
import info.magnolia.event.EventBus;
import info.magnolia.jcr.util.PropertyUtil;
import info.magnolia.ui.admincentral.dialog.action.SaveConfigDialogAction;
import info.magnolia.ui.api.app.AppContext;
import info.magnolia.ui.api.app.SubAppEventBus;
import info.magnolia.ui.api.message.Message;
import info.magnolia.ui.api.message.MessageType;
import info.magnolia.ui.form.EditorCallback;
import info.magnolia.ui.form.EditorValidator;
import info.magnolia.ui.vaadin.integration.jcr.JcrItemAdapter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

public class MarkWrongWithDialogSaveAction extends SaveConfigDialogAction {
    @Inject
    public MarkWrongWithDialogSaveAction(MarkWrongWithDialogSaveDefinition definition, final Item item, final EditorValidator validator, 
            final EditorCallback callback, final @Named(SubAppEventBus.NAME) EventBus eventBus, final AppContext appContext) {
        super(definition, item, validator, new EditorCallback() {

            @Override
            public void onCancel() {
                if(callback != null) {
                    callback.onCancel();
                }
            }

            @Override
            public void onSuccess(String actionName) {
                try {
                    if(callback != null) {
                        callback.onSuccess(actionName);
                    }
                    
                    final Node node = (Node) ((JcrItemAdapter)item).getJcrItem();
                    
                    Message message = new Message();
                    message.setMessage("Created reason '" + item.getItemProperty("reason").getValue() +
                            "' for node '" + node.getPath() + "'");
                    message.setType(MessageType.WARNING);
                    
                    appContext.sendUserMessage(PropertyUtil.getString(node, "mgnl:createdBy"), message);
                } catch (RepositoryException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }, eventBus);
    }
}