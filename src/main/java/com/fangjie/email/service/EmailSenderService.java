package com.fangjie.email.service;

import com.fangjie.email.vo.EmailVO;
import org.apache.velocity.VelocityContext;
import org.thymeleaf.context.Context;

/**
 * Created by fangjie04 on 2016/12/1.
 * 邮件发送服务
 */
public interface EmailSenderService {

    /**
     * 发送简单的文本格式
     *
     * @param vo
     */
    void sendEmailBySimpleText(EmailVO vo) throws Exception;

    /**
     * 发送html格式的消息
     *
     * @param vo
     */
    void sendEmailByHTMLText(EmailVO vo) throws Exception;

    /**
     * 发送邮件：简单文本或者HTML，附件
     *
     * @param vo
     */
    void sendEmailBySimpleTextAndAttachment(EmailVO vo, boolean isHtmlText) throws Exception;

    /**
     * velocity模版引擎发送
     *
     * @param vo
     */
    void sendEmailByVelocity(EmailVO vo, VelocityContext ctx) throws Exception;

    /**
     * thymeleaf模版引擎
     *
     * @param vo
     * @throws Exception
     */
    void sendEmailByThymeleaf(EmailVO vo, Context context) throws Exception;

}
