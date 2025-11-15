import bean.UserDao;
import bean.UserService;
import cn.hutool.core.io.IoUtil;
import cn.mini.beans.BeansException;
import cn.mini.beans.PropertyValue;
import cn.mini.beans.PropertyValues;
import cn.mini.beans.factory.config.BeanDefinition;
import cn.mini.beans.factory.config.BeanFactoryPostProcessor;
import cn.mini.beans.factory.config.BeanReference;
import cn.mini.beans.factory.support.DefaultListableBeanFactory;
import cn.mini.beans.factory.xml.XmlBeanDefinitionReader;
import cn.mini.context.support.ClassPathXmlApplicationContext;
import cn.mini.core.io.DefaultResourceLoader;
import cn.mini.core.io.Resource;
import common.MyBeanFactoryPostProcessor;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestSpring {

    private DefaultResourceLoader resourceLoader;

    @Test
    public void test_classpath() throws IOException {
        resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.printf("<UNK>:%s\n", s);
    }

    @Test
    public void test_file() throws IOException {
        resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.printf("<UNK>:%s\n", s);
    }

    @Test
    public void test_url() throws IOException {
        // 加载慢
        resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.printf("<UNK>:%s\n", s);
    }


    @Test
    public void test_04() throws BeansException {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. bean.UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. bean.UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4. bean.UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. bean.UserService 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void test_xml_05() throws BeansException {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
        // 2. bean.UserDao 注册
    }

    @Test
    public void test_xml() throws BeansException {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String res = userService.queryUserInfo();
        System.out.println("测试结果：" + res);
    }

    @Test
    public void test_xl() throws BeansException {
        MyBeanFactoryPostProcessor app = new MyBeanFactoryPostProcessor();

        System.out.println(app.getClass().isAssignableFrom(BeanFactoryPostProcessor.class));

    }

}
