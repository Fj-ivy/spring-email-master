# spring-email-master
spring sends email by three methods

## 工具以及环境
    ### 工具：Intellij Idea
    ### JDK：1.8
    ### Spring 4.3.4.release
    ### maven:3.x
## 项目结构
### src
    #### 此项目是基于java的spring配置方式，下面介绍一个EmailApplicationContext这个配置类
    #### EmailApplicationContext：实现的功能与<beans></beans>一样，定义方法返回实例，相当于xml中的<bean id="" class=""></bean>
    
    #### config:主要是关于邮件配置，模板的配置，映射与properties文件
    #### constant:定义一些常量
    #### service:邮件发送的借口定义
    #### type:邮件发送的类型，包括：简单的html，velocity模版， thymeleaf模版三种类型
    #### vo:邮件信息的vo，包括，发件人，收件人，抄送人，密送人，邮件内容等
### resource
    #### properties：资源文件
    #### velocity:velocity模版文件
    #### thymeleaf:thymeleaf模版文件
### test
    #### 单元测试
