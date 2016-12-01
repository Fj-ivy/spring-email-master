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
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        if (vo.getCc().length > 0){
            simpleMailMessage.setCc(vo.getCc());
        }

        if (vo.getBcc().length > 0){
            simpleMailMessage.setBcc(vo.getBcc());
        }
        // 设置发送时间
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setFrom(vo.getSender());
        simpleMailMessage.setTo(vo.getReceivers());
        simpleMailMessage.setSubject(vo.getSubject());
        simpleMailMessage.setText(vo.getEmailContent());

        mailSender.send(simpleMailMessage);
    }
}
