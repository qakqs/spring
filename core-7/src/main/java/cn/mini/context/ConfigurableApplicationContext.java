package cn.mini.context;

import cn.mini.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{
    // 刷新容器
    void refresh() throws BeansException;

}
