package cn.ikangjia.pomelo.infra.exception;

import cn.ikangjia.pomelo.api.model.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/8 20:29
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResultVO<String> handlerLoginException(Exception e) {
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(-1);
        resultVO.setMsg(e.getMessage());
        return resultVO;
    }

    @ExceptionHandler(Exception.class)
    public ResultVO<Object> handlerException(Exception e) {
        return Optional.of(e.getMessage())
                .map(ResultVO::error)
                .orElse(ResultVO.error("服务器内部错误"));
    }
}
