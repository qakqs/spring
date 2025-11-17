package cn.mini.beans.factory;

public interface InitializingBean {

    /**
     * bean 输里属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
