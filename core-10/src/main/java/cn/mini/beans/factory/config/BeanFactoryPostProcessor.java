package cn.mini.beans.factory.config;

import cn.mini.beans.BeansException;
import cn.mini.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor{
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
