package cn.mini.beans.factory.support;

import cn.mini.beans.BeansException;
import cn.mini.beans.factory.DisposableBean;
import cn.mini.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();
    Map<String, DisposableBean> disposableBeans = new HashMap<>();
    protected static final Object NULL_OBJECT = new Object();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void destroySingletons() {
        Object[] disposableBeanNames = this.disposableBeans.keySet().toArray();
        for (int i = 0; i < disposableBeanNames.length; i++) {
            String beanName = disposableBeanNames[i].toString();
            DisposableBean remove = disposableBeans.remove(beanName);
            try {
                remove.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);

    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    @Override
    public void registerDisposableBean(String beanName, DisposableBean disposableBean) {
        disposableBeans.put(beanName, disposableBean);
    }

}
