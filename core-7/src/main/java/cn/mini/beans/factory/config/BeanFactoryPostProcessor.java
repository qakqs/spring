package cn.mini.beans.factory.config;

import cn.mini.beans.BeansException;

public interface BeanFactoryPostProcessor{
    void postProcessBeanFactory() throws BeansException;
}
