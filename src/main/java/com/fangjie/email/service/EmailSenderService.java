package com.fangjie.email.service;

import com.fangjie.email.vo.EmailVO;

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
    void sendEmailBySimpleText(EmailVO vo);

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
}
