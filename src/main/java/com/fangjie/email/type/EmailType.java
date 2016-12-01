package com.fangjie.email.type;

/**
 * Created by fangjie04 on 2016/12/1.
 *
 * 三种不同主题的email类型
 * HTML:HTML渲染邮件的显示
 * VELOCITY：velocity模版引擎实现邮件的渲染
 * THYMELEAF：thymeleaf模版引擎实现邮件的渲染
 */
public enum EmailType {
    HTML,
    VELOCITY,
    THYMELEAF;
}
