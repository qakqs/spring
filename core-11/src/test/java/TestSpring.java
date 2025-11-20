import bean.UserService;
import cn.mini.beans.BeansException;
import cn.mini.context.support.ClassPathXmlApplicationContext;
import event.CustomEvent;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class TestSpring {

    @Test
    public void test_event() throws BeansException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));
                UserService userService1 = applicationContext.getBean("userService", UserService.class);
        String s = userService1.getuId();
        System.out.println(s);

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
