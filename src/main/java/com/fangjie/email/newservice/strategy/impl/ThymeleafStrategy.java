package com.fangjie.email.newservice.strategy.impl;

import com.fangjie.email.config.TemplateConfig;
import com.fangjie.email.newservice.strategy.MailStrategy;
import com.fangjie.email.vo.EmailVO;
import com.google.common.base.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.swing.*;

/**
 * Created by fangjie04 on 2016/12/3.
 */

public class ThymeleafStrategy implements MailStrategy {

    private Context context;
    private String templateName;

    public ThymeleafStrategy(Context context, String templateName) {
        this.context = context;
        this.templateName = templateName;
    }

    public ThymeleafStrategy setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public String message(EmailVO vo) {
        SpringTemplateEngine springTemplateEngine = this.springTemplateEngine();
        String content = springTemplateEngine.process(this.templateName, context);
        return content;
    }


    private ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/");
        resolver.setTemplateMode("HTML5");
        resolver.setCharacterEncoding(Charsets.UTF_8.toString());
        return resolver;
    }

    private SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.addTemplateResolver(emailTemplateResolver());
        return springTemplateEngine;
    }
}
