package cn.mini.context.support;

import cn.mini.beans.BeansException;
import cn.mini.beans.factory.ConfigurableListableBeanFactory;
import cn.mini.beans.factory.config.BeanFactoryPostProcessor;
import cn.mini.beans.factory.config.BeanPostProcessor;
import cn.mini.beans.factory.support.ApplicationContextAwareProcessor;
import cn.mini.context.ApplicationEvent;
import cn.mini.context.ApplicationListener;
import cn.mini.context.ConfigurableApplicationContext;
import cn.mini.context.event.ApplicationEventMulticaster;
import cn.mini.context.event.ContextClosedEvent;
import cn.mini.context.event.ContextRefreshedEvent;
import cn.mini.context.event.SimpleApplicationEventMulticaster;
import cn.mini.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {

        //0. 创建bean工厂
        refreshBeanFactory();

        //1. 获取bean工厂
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //2. 添加beanProcessor
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //3. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        //4. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //5. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        //6. 注册钩子函数
        registerShutdownHook();

        //7. 初始化事件发布
        initApplicationEventMulticaster();

        //8. 注册事件监听器
        registerListeners();

        //9. 发布容器刷新完成事件
        finishRefresh();


    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);

    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        Map<String, BeanPostProcessor> beanPostProcessorMap = getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    protected abstract void refreshBeanFactory() throws BeansException;

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() throws BeansException {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> clzz) throws BeansException {
        return getBeanFactory().getBean(name, clzz);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));

    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();

        publishEvent(new ContextClosedEvent(this));

        getBeanFactory().destroySingletons();
    }


}
