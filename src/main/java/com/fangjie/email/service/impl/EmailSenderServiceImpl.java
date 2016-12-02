package com.fangjie.email.service.impl;

import com.fangjie.email.service.EmailSenderService;
import com.fangjie.email.vo.EmailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by fangjie04 on 2016/12/1.
 */
@Service(value = "emailSenderService")
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmailBySimpleText(EmailVO vo) {
        // 发送Email消息实例
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 抄送
        if (vo.getCc().length > 0){
            simpleMailMessage.setCc(vo.getCc());
        }
        // 密送
        if (vo.getBcc().length > 0){
            simpleMailMessage.setBcc(vo.getBcc());
        }
        // 设置发送时间
        simpleMailMessage.setSentDate(new Date());
        // 邮件发送者
        simpleMailMessage.setFrom(vo.getSender());
        // 邮件接受者
        simpleMailMessage.setTo(vo.getReceivers());
        // 邮件主题
        simpleMailMessage.setSubject(vo.getSubject());
        // 邮件内容
        simpleMailMessage.setText(vo.getEmailContent());

        mailSender.send(simpleMailMessage);
    }
}
