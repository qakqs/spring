import cn.mini.beans.BeansException;
import cn.mini.beans.factory.config.BeanDefinition;
import cn.mini.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class TestSpring {

    @Test
    public void test() throws BeansException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class));
        beanFactory.registerBeanDefinition("queryDao", new BeanDefinition(QueryDao.class));
        UserService userService =(UserService) beanFactory.getBean("userService", "lalalal", beanFactory.getBean("queryDao"));
        userService.queryUserName();
    }

}
