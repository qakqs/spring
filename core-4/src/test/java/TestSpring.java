import cn.mini.beans.BeansException;
import cn.mini.beans.PropertyValue;
import cn.mini.beans.PropertyValues;
import cn.mini.beans.factory.config.BeanDefinition;
import cn.mini.beans.factory.config.BeanReference;
import cn.mini.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class TestSpring {

    @Test
    public void test() throws BeansException {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. UserDao 注册
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(QueryDao.class)
        );
        // 3. UserService 设置属性[uId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference(" userDao")));
        // 4. UserService 注入 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}
