import bean.UserDao;
import bean.UserService;
import cn.hutool.core.io.IoUtil;
import cn.mini.beans.BeansException;
import cn.mini.beans.PropertyValue;
import cn.mini.beans.PropertyValues;
import cn.mini.beans.factory.config.BeanDefinition;
import cn.mini.beans.factory.config.BeanReference;
import cn.mini.beans.factory.support.DefaultListableBeanFactory;
import cn.mini.beans.factory.xml.XmlBeanDefinitionReader;
import cn.mini.context.support.ClassPathXmlApplicationContext;
import cn.mini.core.io.DefaultResourceLoader;
import cn.mini.core.io.Resource;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestSpring {

    @Test
    public void test() throws BeansException {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
        System.out.println("ApplicationContextAware：" + userService.getApplicationContext());
        System.out.println("BeanFactoryAware：" + userService.getBeanFactory());
    }

    public static void main(String[] args) {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String res = userService.queryUserInfo();
        System.out.println("测试结果：" + res);

    }
}
