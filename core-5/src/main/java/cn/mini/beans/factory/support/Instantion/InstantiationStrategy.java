package cn.mini.beans.factory.support.Instantion;

import cn.mini.beans.BeansException;
import cn.mini.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;
}
