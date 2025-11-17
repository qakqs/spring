package cn.mini.beans.factory;

public interface DisposableBean {

    /*
    销毁
     */
    void destroy() throws Exception;

}
