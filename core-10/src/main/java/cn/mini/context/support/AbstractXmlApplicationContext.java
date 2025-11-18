package cn.mini.context.support;

import cn.mini.beans.BeansException;
import cn.mini.beans.factory.support.DefaultListableBeanFactory;
import cn.mini.beans.factory.xml.XmlBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    protected  void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    };

        protected abstract String[] getConfigLocations();

}
