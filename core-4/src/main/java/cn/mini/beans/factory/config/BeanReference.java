package cn.mini.beans.factory.config;

public class BeanReference {
    private String beanName;
    public BeanReference(String name){
        this.beanName = name;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
