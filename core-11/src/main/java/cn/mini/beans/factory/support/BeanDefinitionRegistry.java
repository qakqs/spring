package cn.mini.beans.factory.support;

import cn.mini.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    public Boolean containsBeanDefinition(String beanName);
}
