package cn.mini.beans.factory.support;

import cn.mini.beans.BeansException;
import cn.mini.beans.factory.config.BeanDefinition;
import cn.mini.beans.factory.support.Instantion.CglibSubclassingInstantiationStrategy;
import cn.mini.beans.factory.support.Instantion.InstantiationStrategy;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    InstantiationStrategy instantiation = new CglibSubclassingInstantiationStrategy();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {

        Constructor constructorUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] constructors = beanClass.getConstructors();
        for ( Constructor constructor : constructors ) {
            if (null != args && constructor.getParameterTypes().length == args.length){
                constructorUse = constructor;
                break;
            }
        }
        return  instantiation.instantiate(beanDefinition, beanName, constructorUse, args);
    }
}
