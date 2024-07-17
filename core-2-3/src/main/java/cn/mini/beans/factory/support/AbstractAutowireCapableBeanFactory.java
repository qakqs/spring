package cn.mini.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.mini.beans.BeansException;
import cn.mini.beans.PropertyValue;
import cn.mini.beans.PropertyValues;
import cn.mini.beans.factory.config.BeanDefinition;
import cn.mini.beans.factory.config.BeanReference;
import cn.mini.beans.factory.support.Instantion.CglibSubclassingInstantiationStrategy;
import cn.mini.beans.factory.support.Instantion.InstantiationStrategy;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

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
        for (Constructor constructor : constructors) {
            if (null != args && constructor.getParameterTypes().length == args.length) {
                constructorUse = constructor;
                break;
            }
        }
        return instantiation.instantiate(beanDefinition, beanName, constructorUse, args);
    }

    protected void applyPropertyValues(Object bean, String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();

            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                Object value = propertyValue.getValue();
                String name = propertyValue.getName();
                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setProperty(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }
}
