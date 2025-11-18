package cn.mini.beans;


public class BeansException extends RuntimeException  {
    private static final long serialVersionUID = -56178313330468078L;

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
