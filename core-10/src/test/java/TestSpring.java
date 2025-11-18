import bean.UserService;
import cn.mini.beans.BeansException;
import cn.mini.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class TestSpring {

    @Test
    public void test_10() throws BeansException {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        // 2. 获取Bean对象调用方法
        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
       // UserService userService02 = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService01);
       // System.out.println(userService02);

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
