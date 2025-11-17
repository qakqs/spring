package cn.mini.beans;

import java.io.Serial;

public class BeansException extends RuntimeException  {
    @Serial
    private static final long serialVersionUID = -56178313330468078L;

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
