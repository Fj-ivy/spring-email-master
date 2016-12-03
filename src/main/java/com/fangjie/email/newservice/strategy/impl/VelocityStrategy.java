package com.fangjie.email.newservice.strategy.impl;

import com.fangjie.email.config.TemplateConfig;
import com.fangjie.email.constant.EmailConstant;
import com.fangjie.email.newservice.strategy.MailStrategy;
import com.fangjie.email.vo.EmailVO;
import com.google.common.base.Charsets;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.StringWriter;

/**
 * Created by fangjie04 on 2016/12/3.
 */


public class VelocityStrategy implements MailStrategy {

    private VelocityContext velocityContext;
    private String templateName;

    // 两种注入方式
    public VelocityStrategy(VelocityContext velocityContext, String templateName) {
        this.velocityContext = velocityContext;
        this.templateName = templateName;
    }

    public VelocityStrategy setVelocityContext(VelocityContext velocityContext) {
        this.velocityContext = velocityContext;
        return this;
    }

    @Override
    public String message(EmailVO vo) {
        Template template = this.ve().getTemplate(this.templateName, Charsets.UTF_8.toString());
        StringWriter sw = new StringWriter();
        template.merge(velocityContext, sw);
        return sw.toString();
    }

    private VelocityEngine ve() {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, EmailConstant.CLASSPATH);
        ve.setProperty(EmailConstant.CLASSPATH_RESOURCE_LOADER_CLASS, ClasspathResourceLoader.class.getName());
        ve.init();
        return ve;
    }
}
