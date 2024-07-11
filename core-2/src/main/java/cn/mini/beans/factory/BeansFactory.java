package cn.mini.beans.factory;

import cn.mini.beans.BeansException;

public interface BeansFactory {

    public Object getBean(String name) throws BeansException;
}
