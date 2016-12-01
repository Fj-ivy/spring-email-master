package com.fangjie.email.service;

import com.fangjie.email.vo.EmailVO;

/**
 * Created by fangjie04 on 2016/12/1.
 * 邮件发送服务
 */
public interface EmailSenderService {

    void sendEmailBySimpleText(EmailVO vo);
}
