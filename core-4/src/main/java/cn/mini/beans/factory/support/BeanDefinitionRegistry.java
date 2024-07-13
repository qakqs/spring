package cn.mini.beans.factory.support;

import cn.mini.beans.factory.config.BeanDefinition;

interface  BeanDefinitionRegistry {
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
