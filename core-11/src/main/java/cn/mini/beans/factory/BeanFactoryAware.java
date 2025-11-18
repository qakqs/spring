package cn.mini.beans.factory;

import cn.mini.beans.BeansException;

public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
