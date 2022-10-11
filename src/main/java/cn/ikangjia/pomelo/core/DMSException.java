package cn.ikangjia.pomelo.core;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/9 15:53
 */
public class DMSException extends RuntimeException{

    public DMSException(String message) {
        super(message);
    }

    public DMSException(String message, Throwable cause) {
        super(message, cause);
    }
}
