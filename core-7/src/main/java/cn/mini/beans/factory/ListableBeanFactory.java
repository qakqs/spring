package cn.mini.beans.factory;

import cn.mini.beans.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {

    <T>Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    String[] getBeanDefinitionNames() throws BeansException;
}
