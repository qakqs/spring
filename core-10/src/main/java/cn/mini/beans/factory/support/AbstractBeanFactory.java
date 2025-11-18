package cn.mini.beans.factory.support;

import cn.mini.beans.BeansException;
import cn.mini.beans.factory.BeanFactory;
import cn.mini.beans.factory.FactoryBean;
import cn.mini.beans.factory.config.BeanDefinition;
import cn.mini.beans.factory.config.BeanPostProcessor;
import cn.mini.beans.factory.config.ConfigurableBeanFactory;
import cn.mini.util.ClassUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    /**
     * BeanPostProcessors to apply in createBean
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();


    /**
     * ClassLoader to resolve bean class names with, if necessary
     */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public <T> T getBean(String name, Class<T> clzz) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);

    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }


    protected <T> T doGetBean(String name, Object[] args) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean1 = createBean(name, beanDefinition, args);

        return (T)getObjectForBeanInstance(bean1, name) ;
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        Object obj = getCachedObjectForFactoryBean(beanName);
        if (obj == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            obj = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return obj;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

}
