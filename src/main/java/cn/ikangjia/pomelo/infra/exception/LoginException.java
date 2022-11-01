package cn.ikangjia.pomelo.infra.exception;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/1 8:56
 */
public class LoginException extends RuntimeException{
    public LoginException(String message) {
        super(message);
    }
}
