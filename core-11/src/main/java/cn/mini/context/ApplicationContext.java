package cn.mini.context;

import cn.mini.beans.factory.HierarchicalBeanFactory;
import cn.mini.beans.factory.ListableBeanFactory;
import cn.mini.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {

}
