package org.defascat.tutorial;

import info.magnolia.objectfactory.Components;
import info.magnolia.rendering.template.TemplateAvailability;
import info.magnolia.rendering.template.configured.ConfiguredTemplateDefinition;
import javax.inject.Inject;

public class HomePageDefinition extends ConfiguredTemplateDefinition {
    @Inject
    public HomePageDefinition(TemplateAvailability templateAvailability) {
        super(templateAvailability);
    }

    public HomePageDefinition() {
        super(Components.getComponent(TemplateAvailability.class));
    }
}