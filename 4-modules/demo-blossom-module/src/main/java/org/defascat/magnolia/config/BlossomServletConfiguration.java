package org.defascat.magnolia.config;

import info.magnolia.cms.beans.config.VirtualURIMapping;
import info.magnolia.module.blossom.annotation.Area;
import info.magnolia.module.blossom.annotation.DialogFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.module.blossom.annotation.VirtualURIMapper;
import info.magnolia.module.blossom.preexecution.BlossomHandlerMapping;
import info.magnolia.module.blossom.view.FreemarkerTemplateViewRenderer;
import info.magnolia.module.blossom.view.JspTemplateViewRenderer;
import info.magnolia.module.blossom.view.TemplateViewResolver;
import info.magnolia.module.blossom.view.UuidRedirectViewResolver;
import info.magnolia.module.blossom.web.BlossomHandlerMethodArgumentResolver;
import info.magnolia.module.blossom.web.BlossomRequestMappingHandlerAdapter;
import info.magnolia.templating.freemarker.Directives;
import info.magnolia.templating.functions.TemplatingFunctions;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Configuration class for the blossom servlet housing templates and beans used to render them.
 */
@Configuration
@ComponentScan(
        basePackages = {"org.defascat.magnolia"},
       includeFilters = {
                @ComponentScan.Filter(Template.class),
                @ComponentScan.Filter(Area.class),
                @ComponentScan.Filter(DialogFactory.class),
                @ComponentScan.Filter(VirtualURIMapper.class),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = VirtualURIMapping.class)
        })
public class BlossomServletConfiguration {

    /**
     * Handler adapter for &#64;RequestMapping style handler methods. Uses BlossomRequestMappingHandlerAdapter for
     * support of flash attributes in uuid redirect views.
     */
    @Bean
    public HandlerAdapter handlerAdapter() {

        BlossomRequestMappingHandlerAdapter handlerAdapter = new BlossomRequestMappingHandlerAdapter();
        handlerAdapter.setRedirectPatterns("website:*");

        handlerAdapter.setCustomArgumentResolvers(Collections.<HandlerMethodArgumentResolver>singletonList(new BlossomHandlerMethodArgumentResolver()));

        // For @Valid - JSR-303 Bean Validation API -->
        ConfigurableWebBindingInitializer bindingInitializer = new ConfigurableWebBindingInitializer();
        bindingInitializer.setValidator(validatorFactory());
        handlerAdapter.setWebBindingInitializer(bindingInitializer);

        return handlerAdapter;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactory() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Handler adapter for Controller interface style controllers. Required because these are used internally by the
     * pre-execution mechanism.
     */
    @Bean
    public HandlerAdapter controllerHandlerAdapter() {
        return new SimpleControllerHandlerAdapter();
    }

    /**
     * Handler mapping that delegates to other handler mappings. Required by the pre-execution mechanism and must be
     * ordered first.
     */
    @Bean
    public HandlerMapping blossomHandlerMapping() {
        BlossomHandlerMapping handlerMapping = new BlossomHandlerMapping();
        handlerMapping.setOrder(1);
        handlerMapping.setTargetHandlerMappings(new HandlerMapping[]{mappingHandlerMapping()});
        return handlerMapping;
    }

    /**
     * Handler mapping for &#64;RequestMapping style handler methods.
     */
    @Bean
    public RequestMappingHandlerMapping mappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new RequestMappingHandlerMapping();
        handlerMapping.setOrder(2);
        handlerMapping.setUseSuffixPatternMatch(false);
        return handlerMapping;
    }

    /**
     * View resolver for uuid redirect views.
     */
    @Bean
    public UuidRedirectViewResolver uuidRedirectViewResolver() {
        UuidRedirectViewResolver resolver = new UuidRedirectViewResolver();
        resolver.setOrder(1);
        return resolver;
    }

    /**
     * View resolver for JSP views.
     */
    @Bean
    public TemplateViewResolver jspTemplateViewResolver() {
        TemplateViewResolver resolver = new TemplateViewResolver();
        resolver.setOrder(2);
        resolver.setPrefix("/templates/demo-blossom-module/");
        resolver.setViewNames("*.jsp");
        JspTemplateViewRenderer viewRenderer = new JspTemplateViewRenderer();
        // Uncomment this line to use DAM templating functions in JSPs
        // viewRenderer.addContextAttribute("damfn", DamTemplatingFunctions.class);
        resolver.setViewRenderer(viewRenderer);
        return resolver;
    }

    /**
     * View resolver for Freemarker views.
     */
    @Bean
    public TemplateViewResolver freemarkerTemplateViewResolver() {
        TemplateViewResolver resolver = new TemplateViewResolver();
        resolver.setOrder(3);
        resolver.setPrefix("/demo-blossom-module/");
        resolver.setViewNames("*.ftl");
        FreemarkerTemplateViewRenderer viewRenderer = new FreemarkerTemplateViewRenderer();
        viewRenderer.addContextAttribute("cms", Directives.class);
        viewRenderer.addContextAttribute("cmsfn", TemplatingFunctions.class);
        // Uncomment this line to use DAM templating functions in Freemarker
        // viewRenderer.addContextAttribute("damfn", DamTemplatingFunctions.class);
        resolver.setViewRenderer(viewRenderer);
        return resolver;
    }

/*
    If you're only going to use freemarker you can remove the two view view rendered above and use the commented out
    method below instead. Using it you dont' have to return the .ftl extension in controllers.

    @Bean
    public TemplateViewResolver freemarkerTemplateViewResolver() {
        TemplateViewResolver resolver = new TemplateViewResolver();
        resolver.setOrder(2);
        resolver.setPrefix("/demo-blossom-module/");
        resolver.setSuffix(".ftl");
        FreemarkerTemplateViewRenderer viewRenderer = new FreemarkerTemplateViewRenderer();
        viewRenderer.addContextAttribute("cms", Directives.class);
        viewRenderer.addContextAttribute("cmsfn", TemplatingFunctions.class);
        // Uncomment this line to use DAM templating functions in Freemarker
        // viewRenderer.addContextAttribute("damfn", DamTemplatingFunctions.class);
        resolver.setViewRenderer(viewRenderer);
        return resolver;
    }
*/
}
