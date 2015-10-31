package org.defascat.magnolia;

import info.magnolia.dam.app.ui.config.DamConfig;
import info.magnolia.module.blossom.annotation.Area;
import static info.magnolia.module.blossom.annotation.AreaType.LIST;
import info.magnolia.module.blossom.annotation.AvailableComponentClasses;
import info.magnolia.module.blossom.annotation.DialogFactory;
import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.ui.dialog.config.DialogBuilder;
import info.magnolia.ui.form.config.TabBuilder;
import info.magnolia.ui.framework.config.UiConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Template(id = "demo-blossom:pages/blossom-demo-page", title = "Blossom Demo Page")
public class BlossomDemoTemplate {
    @TabFactory("Content")
    public void demoPageProperties(UiConfig cfg, TabBuilder tab) {
        tab.fields(
                cfg.fields.text("headline").label("Headline").description("The text to use as a headline") 
        );
    }
 
    @RequestMapping("/blossom-demo-page")
    public String render() {
        return "pages/blossom-demo.ftl";
    }
 
    @Area(value="content", maxComponents = 4, type = LIST, title = "Content")
    @AvailableComponentClasses({DemoTextComponent.class})
    @Controller
    public static class ContentArea {

        @RequestMapping("/blossom-demo-page/content")
        public String render() {
            return "areas/contentArea.jsp";
        }
    }
}