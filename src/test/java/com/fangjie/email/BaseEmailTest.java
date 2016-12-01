package com.fangjie.email;

import com.fangjie.email.EmailApplicationContext;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by fangjie04 on 2016/12/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EmailApplicationContext.class})
public abstract class BaseEmailTest {
}
