import bean.IUserService;
import bean.UserService;
import bean.UserServiceInterceptor;
import cn.mini.aop.AdvisedSupport;
import cn.mini.aop.TargetSource;
import cn.mini.aop.aspectj.AspectJExpressionPointcut;
import cn.mini.aop.framework.Cglib2AopProxy;
import cn.mini.aop.framework.JdkDynamicAopProxy;
import cn.mini.beans.BeansException;
import cn.mini.context.support.ClassPathXmlApplicationContext;
import event.CustomEvent;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TestSpring {

    @Test
    public void test_event() throws BeansException {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
    IUserService userService = applicationContext.getBean("userService", IUserService.class);
    System.out.println("测试结果：" + userService.queryUserInfo());

    }
}
