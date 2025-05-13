package cn.mini.beans;

import java.io.Serial;

public class BeansException extends Exception {
    @Serial
    private static final long serialVersionUID = -56178313330468078L;

    public BeansException() {
        super();
    }
    public BeansException(String message) {
        super(message);
    }
    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
    public BeansException(Throwable cause) {
        super(cause);
    }

}
