package com.fangjie.email.service.impl;

import com.fangjie.email.config.TemplateConfig;
import com.fangjie.email.constant.EmailConstant;
import com.fangjie.email.service.EmailSenderService;
import com.fangjie.email.vo.EmailVO;
import com.google.common.base.Charsets;
import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by fangjie04 on 2016/12/1.
 */
@Service(value = "emailSenderService")
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private TemplateConfig templateConfig;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Override
    public void sendEmailBySimpleText(EmailVO vo)throws Exception {
        // 发送Email消息实例
        // SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        // 抄送
        if (vo.getCc().length > 0) {
            helper.setCc(vo.getCc());
        }
        // 密送
        if (vo.getBcc().length > 0) {
            helper.setBcc(vo.getBcc());
        }
        // 设置发送时间
        helper.setSentDate(new Date());
        // 邮件发送者
        helper.setFrom(vo.getSender());
        // 邮件接受者
        helper.setTo(vo.getReceivers());
        // 邮件主题
        helper.setSubject(vo.getSubject());
        // 邮件内容
        helper.setText(vo.getEmailContent());

        mailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailByHTMLText(EmailVO vo) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        if (vo.getCc().length > 0) {
            helper.setCc(vo.getCc());
        }
        if (vo.getBcc().length > 0) {
            helper.setBcc(vo.getBcc());
        }
        helper.setFrom(vo.getSender());
        helper.setTo(vo.getReceivers());
        helper.setSubject(vo.getSubject());
        helper.setSentDate(new Date());
        // true表示发送的是html消息
        helper.setText(vo.getEmailContent(), true);
        for (ClassPathResource resource : vo.getClassPathResource()) {
            String fileName = resource.getFilename();
            helper.addInline(fileName.substring(0, fileName.lastIndexOf(".")), resource);
        }

        mailSender.send(mimeMessage);

    }

    @Override
    public void sendEmailBySimpleTextAndAttachment(EmailVO vo, boolean isHtmlText) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // true表明这个是一个multipart类型的消息,设置消息编码格式为UTF8
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, Charsets.UTF_8.toString());

        if (vo.getCc().length > 0) {
            helper.setCc(vo.getCc());
        }
        if (vo.getBcc().length > 0) {
            helper.setBcc(vo.getBcc());
        }

        helper.setFrom(vo.getSender());
        helper.setTo(vo.getReceivers());
        helper.setSubject(vo.getSubject());
        helper.setSentDate(new Date());
        helper.setText(vo.getEmailContent(), isHtmlText);

        for (ClassPathResource resource : vo.getClassPathResource()) {
            helper.addInline(resource.getFilename(), resource);
        }

        for (File file : vo.getAttachFile()) {
            FileSystemResource resource = new FileSystemResource(file);
            helper.addAttachment(file.getName(), resource);
        }
        mailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailByVelocity(EmailVO vo,VelocityContext ctx) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        if (vo.getCc().length > 0) {
            helper.setCc(vo.getCc());
        }
        if (vo.getBcc().length > 0) {
            helper.setBcc(vo.getBcc());
        }
        helper.setFrom(vo.getSender());
        helper.setTo(vo.getReceivers());
        helper.setSubject(vo.getSubject());
        helper.setSentDate(new Date());
        Template template = velocityEngine.getTemplate(templateConfig.getVelocityTemplateName(), Charsets.UTF_8.toString());

        StringWriter sw = new StringWriter();
        template.merge(ctx, sw);
        helper.setText(sw.toString(), true);


        mailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailByThymeleaf(EmailVO vo,Context context) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        if (vo.getCc().length > 0) {
            helper.setCc(vo.getCc());
        }
        if (vo.getBcc().length > 0) {
            helper.setBcc(vo.getBcc());
        }
        helper.setFrom(vo.getSender());
        helper.setTo(vo.getReceivers());
        helper.setSubject(vo.getSubject());
        helper.setSentDate(new Date());

        String content = springTemplateEngine.process(templateConfig.getThymeleafTemplateName(),
                context);
        helper.setText(content, true);
        mailSender.send(mimeMessage);
    }
}
