package cn.mini.beans.factory.support;

import cn.mini.beans.BeansException;
import cn.mini.beans.factory.BeansFactory;
import cn.mini.beans.factory.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeansFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object singleton = getSingleton(name);
        if (singleton != null){
            return singleton;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

}
