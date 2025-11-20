package cn.mini.context;

import cn.mini.beans.BeansException;
import cn.mini.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
