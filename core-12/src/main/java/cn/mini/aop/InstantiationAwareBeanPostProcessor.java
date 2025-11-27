package cn.mini.aop;

import cn.mini.beans.BeansException;
import cn.mini.beans.factory.config.BeanPostProcessor;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;
}
