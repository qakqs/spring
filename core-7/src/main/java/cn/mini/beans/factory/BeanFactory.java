package cn.mini.beans.factory;

import cn.mini.beans.BeansException;

public interface BeanFactory {

    public Object getBean(String name) throws BeansException;

    public Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> clzz) throws BeansException;
}
