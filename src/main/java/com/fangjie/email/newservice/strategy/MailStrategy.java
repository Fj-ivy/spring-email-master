package com.fangjie.email.newservice.strategy;

import com.fangjie.email.type.EmailType;
import com.fangjie.email.vo.EmailVO;
import org.springframework.mail.MailSender;

/**
 * Created by fangjie04 on 2016/12/3.
 */
public interface MailStrategy {

    String message(EmailVO vo);
}
