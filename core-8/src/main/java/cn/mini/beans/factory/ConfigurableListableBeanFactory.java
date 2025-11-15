package cn.mini.beans.factory;

import cn.mini.beans.BeansException;
import cn.mini.beans.factory.config.AutowireCapableBeanFactory;
import cn.mini.beans.factory.config.BeanDefinition;
import cn.mini.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory , AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;
}
