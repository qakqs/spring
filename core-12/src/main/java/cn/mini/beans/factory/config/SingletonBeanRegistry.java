package cn.mini.beans.factory.config;

import cn.mini.beans.factory.DisposableBean;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void destroySingletons();

    void registerSingleton(String beanName, Object singletonObject);

    void registerDisposableBean(String beanName, DisposableBean disposableBean);
}
