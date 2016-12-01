package com.fangjie.email;

import com.fangjie.email.config.MailConfig;
import com.fangjie.email.service.EmailSenderService;
import com.fangjie.email.vo.EmailVO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by fangjie04 on 2016/12/1.
 */

public class ConfigTest extends BaseEmailTest {

    private AnnotationConfigApplicationContext context = null;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext(EmailApplicationContext.class);
    }

    @Test
    public void testConfig() {
        MailConfig mailConfig = context.getBean(MailConfig.class);
        List<String> receivers = mailConfig.getMailReceivers();
        assertEquals(2, receivers.size());
    }

    @Test
    public void testSendEmail() {
        EmailSenderService senderService = context.getBean(EmailSenderService.class);
        EmailVO vo = new EmailVO();
        vo.setCc(new String[]{});
        vo.setBcc(new String[]{});
        vo.setSubject("[主题][致亲爱的一封邮件]");
        vo.setEmailContent("走不去的是人生，忘不了的是真情");
        vo.setReceivers(new String[]{"920299441@qq.com","631294101@qq.com"});
        vo.setSender("fangjiewd@126.com");
        senderService.sendEmailBySimpleText(vo);
    }
}
