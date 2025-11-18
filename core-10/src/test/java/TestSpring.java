import bean.UserService;
import cn.mini.beans.BeansException;
import cn.mini.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class TestSpring {

    @Test
    public void test() throws BeansException {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        // 2. 获取Bean对象调用方法
        UserService userService1 = applicationContext.getBean("userService", UserService.class);
        UserService userService2 = applicationContext.getBean("userService", UserService.class);

        String result = userService1.queryUserInfo();
        System.out.println("测试结果：" + result);

        System.out.println(userService1);
        System.out.println(userService2);
            System.out.println(userService1 + " 十六进制哈希：" + Integer.toHexString(userService1.hashCode()));
    System.out.println(ClassLayout.parseInstance(userService1).toPrintable());
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
