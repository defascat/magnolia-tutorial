package org.defascat.tutorial;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Field;
import info.magnolia.dam.api.AssetProviderRegistry;
import info.magnolia.dam.app.ui.field.factory.AssetsEnabledRichTextFieldFactory;
import info.magnolia.i18nsystem.SimpleTranslator;
import info.magnolia.ui.api.app.AppController;
import info.magnolia.ui.api.context.UiContext;
import java.util.StringTokenizer;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;


public class ObsceneTextAreaFactory extends AssetsEnabledRichTextFieldFactory {
    @Inject
    public ObsceneTextAreaFactory(ObsceneTextAreaDefinition definition, Item relatedFieldItem, AppController appController, UiContext uiContext, SimpleTranslator i18n, AssetProviderRegistry assetProviderRegistry) {
        super(definition, relatedFieldItem, appController, uiContext, i18n, assetProviderRegistry);
    }

    @Override
    protected Field<String> createFieldComponent() {
        final Field<String> field = super.createFieldComponent();
        field.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if(event.getProperty().getValue() == null) {
                    return;
                }
                
                String originalValue = event.getProperty().getValue().toString();
                String value = originalValue;
                
                StringTokenizer tokenizer = new StringTokenizer(((ObsceneTextAreaDefinition)definition).getWords(), ";,");
                while(tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    value = value.replaceAll("(?i)" + token, StringUtils.repeat("*", token.length()));
                }
                
                if(!originalValue.equals(value)) {
                    event.getProperty().setValue(value);
                }
            }
        });
        return field;
    }
    
    
    
}