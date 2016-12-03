package com.fangjie.email.newservice.strategy.impl;

import com.fangjie.email.newservice.strategy.MailStrategy;
import com.fangjie.email.vo.EmailVO;
import org.springframework.stereotype.Component;

/**
 * Created by fangjie04 on 2016/12/3.
 */
//@Component
public class HTMLStrategy implements MailStrategy {
    @Override
    public String message(EmailVO vo) {
        return vo.getEmailContent();
    }
}
