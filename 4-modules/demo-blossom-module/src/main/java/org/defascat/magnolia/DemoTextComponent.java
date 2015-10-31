package org.defascat.magnolia;

import info.magnolia.dam.app.ui.config.DamConfig;
import info.magnolia.module.blossom.annotation.TabFactory;
import info.magnolia.module.blossom.annotation.Template;
import info.magnolia.ui.form.config.TabBuilder;
import info.magnolia.ui.framework.config.UiConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Template(id = "demo-blossom:components/text", title = "Text")
@Controller
public class DemoTextComponent {
    @TabFactory("Content")
    public void contentTab(UiConfig cfg, DamConfig dam, TabBuilder tab) {
        tab.fields(
                cfg.fields.text("heading").label("Heading"),
                dam.fields.assetLink("image").label("Headline Image"),
                cfg.fields.richText("body").label("Text body").required()
        );
    }
 
    @RequestMapping("/text")
    public String render() {
        return "components/text.jsp";
    }
}