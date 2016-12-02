package com.fangjie.email.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by fangjie04 on 2016/12/1.
 */

@Component
public class TemplateConfig {

    @Value("${velocity.vm.name}")
    private String velocityTemplateName ;

    public String getVelocityTemplateName() {
        return velocityTemplateName;
    }

    public void setVelocityTemplateName(String velocityTemplateName) {
        this.velocityTemplateName = velocityTemplateName;
    }
}
