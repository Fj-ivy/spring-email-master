package com.fangjie.email.newservice.template.impl;

import com.fangjie.email.newservice.strategy.MailStrategy;
import com.fangjie.email.newservice.strategy.impl.TextStrategy;
import com.fangjie.email.newservice.template.MailSenderTemplate;
import com.fangjie.email.type.EmailType;
import com.fangjie.email.vo.EmailVO;
import com.google.common.base.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * Created by fangjie04 on 2016/12/3.
 */
@Component
public class MailSenderTemplateImpl implements MailSenderTemplate {

    protected static final EmailType DEFAULT_EMAIL_TYPE = EmailType.TEXT;

    @Autowired
    private JavaMailSender mailSender;

    private MailStrategy strategy;

    @Override
    public MailSenderTemplateImpl setStrategy(MailStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public void sendMail(EmailVO vo) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        // 第二个参数表示这个是mulipart类型的
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, Charsets.UTF_8.toString());
        boolean isHtmlText = true;

        if (this.strategy instanceof TextStrategy) {
            isHtmlText = false;
        }

        this.emailMessage(helper, vo);

        // true表示发送的是html消息
        helper.setText(this.sendContent(vo), isHtmlText);

        // 显示是内置图片等
        if (vo.getClassPathResource() != null && vo.getClassPathResource().length > 0) {
            for (ClassPathResource resource : vo.getClassPathResource()) {
                String fileName = resource.getFilename();
                helper.addInline(fileName.substring(0, fileName.lastIndexOf(".")), resource);
            }
        }

        // 附件
        if (vo.getAttachFile() != null && vo.getAttachFile().length > 0) {
            for (File file : vo.getAttachFile()) {
                FileSystemResource resource = new FileSystemResource(file);
                helper.addAttachment(file.getName(), resource);
            }
        }

        mailSender.send(mimeMessage);

    }

    private void emailMessage(MimeMessageHelper helper, EmailVO vo) throws MessagingException {
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
    }

    private String sendContent(EmailVO vo) {
        return this.strategy.message(vo).toString();
    }
}
