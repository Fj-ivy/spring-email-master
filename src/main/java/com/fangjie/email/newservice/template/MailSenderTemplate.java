package com.fangjie.email.newservice.template;

import com.fangjie.email.newservice.strategy.MailStrategy;
import com.fangjie.email.newservice.template.impl.MailSenderTemplateImpl;
import com.fangjie.email.type.EmailType;
import com.fangjie.email.vo.EmailVO;

import javax.mail.MessagingException;

/**
 * Created by fangjie04 on 2016/12/3.
 */
public interface MailSenderTemplate {

    /**
     * 发送邮件
     *
     * @param vo
     */
    void sendMail(EmailVO vo) throws MessagingException;

    /**
     * 设置邮件发送策略
     *
     * @param strategy
     * @return
     */
    public MailSenderTemplateImpl setStrategy(MailStrategy strategy);
}
