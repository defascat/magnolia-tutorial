/**
 * 
 */
package org.defascat.tutorial;

import info.magnolia.event.EventBus;
import info.magnolia.jcr.util.PropertyUtil;
import info.magnolia.ui.api.app.AppContext;
import info.magnolia.ui.api.event.AdmincentralEventBus;
import info.magnolia.ui.api.message.Message;
import info.magnolia.ui.api.message.MessageType;
import info.magnolia.ui.api.overlay.AlertCallback;
import info.magnolia.ui.framework.action.AbstractRepositoryAction;
import info.magnolia.ui.vaadin.integration.jcr.JcrItemAdapter;
import info.magnolia.ui.vaadin.overlay.MessageStyleTypeEnum;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jcr.Node;
import javax.jcr.RepositoryException;

public class MarkWrongAction extends AbstractRepositoryAction<MarkWrongActionDefinition> {
    private final String reason;  
    
    @Inject
    private AppContext appContext;
    
    public MarkWrongAction(MarkWrongActionDefinition definition, JcrItemAdapter item, @Named(AdmincentralEventBus.NAME) EventBus eventBus) {
        super(definition, item, eventBus);
        reason = definition.getReason();
    }
    
    @Override
    protected void onExecute(JcrItemAdapter item) throws RepositoryException {
        Message message = new Message();
        message.setMessage(reason + "for node '" + item.getJcrItem().getPath() + "'");
        message.setType(MessageType.WARNING);
        
        appContext.sendUserMessage(
                PropertyUtil.getString((Node) item.getJcrItem(), "mgnl:createdBy"), message);
        appContext.openAlert(MessageStyleTypeEnum.INFO, 
                "Info", "The item '" + item.getJcrItem().getPath() + "' was marked as wrong", "Got it", new AlertCallback() {
            @Override
            public void onOk() {
            }
        });
    }
}