package org.defascat.tutorial;

import info.magnolia.module.mail.MailModule;
import static info.magnolia.module.mail.MailTemplate.MAIL_HTML;
import info.magnolia.module.mail.MgnlMailFactory;
import info.magnolia.module.mail.templates.MailAttachment;
import info.magnolia.module.mail.templates.MgnlEmail;
import info.magnolia.module.mail.util.MailUtil;
import info.magnolia.ui.api.action.AbstractAction;
import info.magnolia.ui.api.action.ActionExecutionException;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendMail extends AbstractAction<SendMailDefinition> {
    public SendMail(SendMailDefinition definition) {
        super(definition);
    }
    
    @Override
    public void execute() throws ActionExecutionException {
        final MgnlMailFactory factory = MailModule.getInstance().getFactory();
        
        List<MailAttachment> attachments = MailUtil.createAttachmentList();
        attachments.add(new MailAttachment(new File("/magnolia/netbeans-8.0.2/nb/shortcuts.pdf"), 
                "file.pdf", "Test PDF file", "attachment") );
        
        try {
            MgnlEmail email = factory.getEmailFromType(new HashMap(), "freemarker", MAIL_HTML, attachments);
            email.setFrom("defascat@tut.by");
            email.setSubject("Test Email");
            email.setToList("andrey.p.panasyuk@gmail.com");
            email.setBody("Test <b>Body</b>");
            MailModule.getInstance().getHandler().sendMail(email);
        } catch (Exception ex) {
            throw new ActionExecutionException(ex);
        }
    }
}