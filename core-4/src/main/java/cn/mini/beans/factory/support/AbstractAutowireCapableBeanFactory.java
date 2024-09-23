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
import java.util.Objects;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    InstantiationStrategy instantiation = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
            applyPropertyValues(bean, beanName, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {

        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return instantiation.instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    protected void applyPropertyValues(Object bean, String beanName, BeanDefinition beanDefinition) throws BeansException {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if(Objects.isNull(propertyValues)) {
                return;
            }
            for (int i = 0; i < propertyValues.getPropertyValues().length; i++) {
                String name = propertyValues.getPropertyValues()[i].getName();
                Object value = propertyValues.getPropertyValues()[i].getValue();

                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);

            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }
}
