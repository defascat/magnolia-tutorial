package org.defascat.magnolia;

import info.magnolia.module.ModuleLifecycle;
import info.magnolia.module.ModuleLifecycleContext;
import info.magnolia.module.model.ModuleDefinition;
import info.magnolia.objectfactory.Components;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is optional and represents the configuration for the demo-module module.
 * By exposing simple getter/setter/adder methods, this bean can be configured via content2bean
 * using the properties and node from <tt>config:/modules/demo-module</tt>.
 * If you don't need this, simply remove the reference to this class in the module descriptor xml.
 */
public class DemoModule implements ModuleLifecycle {
    private String greeting;
    
    protected Logger log = LoggerFactory.getLogger(getClass());
    
    @Inject
    NumberProvider numberProvider;
    
    @Override
    public void start(ModuleLifecycleContext mlc) {
        final ModuleDefinition currentModuleDefinition = mlc.getCurrentModuleDefinition();
        log.warn(String.format("Module %s[%s] called start in phase %d", 
                currentModuleDefinition.getName(), currentModuleDefinition.getVersion(), 
                mlc.getPhase()));
        log.warn("Greeting: " + getGreeting() + " " + numberProvider.getValue());
    }

    @Override
    public void stop(ModuleLifecycleContext mlc) {
        final ModuleDefinition currentModuleDefinition = mlc.getCurrentModuleDefinition();
        log.warn(String.format("Module %s[%s] called stщз in phase %d", 
                currentModuleDefinition.getName(), currentModuleDefinition.getVersion(), 
                mlc.getPhase()));
        log.warn("Greeting: " + getGreeting());
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
