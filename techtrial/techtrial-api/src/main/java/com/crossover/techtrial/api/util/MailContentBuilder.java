package com.crossover.techtrial.api.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {
 
	@Autowired
    private TemplateEngine templateEngine;
 
    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
 
    public String build(String templateName, Map<String, Object> parameters) {
        Context context = new Context();
        for (String key : parameters.keySet()) {
            context.setVariable(key, parameters.get(key));
		}
        
        return templateEngine.process(templateName, context);
    }
 
}