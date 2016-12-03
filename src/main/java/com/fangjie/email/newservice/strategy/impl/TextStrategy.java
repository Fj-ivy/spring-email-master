package com.fangjie.email.newservice.strategy.impl;

import com.fangjie.email.config.TemplateConfig;
import com.fangjie.email.newservice.strategy.MailStrategy;
import com.fangjie.email.type.EmailType;
import com.fangjie.email.vo.EmailVO;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring4.SpringTemplateEngine;

/**
 * Created by fangjie04 on 2016/12/3.
 */
//@Component
public class TextStrategy implements MailStrategy {

    @Override
    public String message(EmailVO vo) {
        return vo.getEmailContent();
    }
}
