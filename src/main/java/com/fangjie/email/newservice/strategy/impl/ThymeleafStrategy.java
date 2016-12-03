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

    private SpringTemplateEngine springTemplateEngine;
    private Context context;
    private String templateName;

    public ThymeleafStrategy setSpringTemplateEngine(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
        return this;
    }

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
        String content = this.springTemplateEngine.process(this.templateName, context);
        return content;
    }
}
