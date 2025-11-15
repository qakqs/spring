package common;

import cn.mini.beans.BeansException;
import cn.mini.beans.PropertyValue;
import cn.mini.beans.PropertyValues;
import cn.mini.beans.factory.ConfigurableListableBeanFactory;
import cn.mini.beans.factory.config.BeanDefinition;
import cn.mini.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
        System.out.println(111);
    }
}
