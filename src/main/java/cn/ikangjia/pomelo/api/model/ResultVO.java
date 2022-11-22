package cn.ikangjia.pomelo.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/5 22:51
 */
@Getter
@Setter
public class ResultVO<T> {
    private Integer code;
    private T data;
    private String msg;

    public ResultVO() {
    }

    public ResultVO(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(0, data, "操作成功");
    }

    public static <T> ResultVO<T> error(String msg) {
        return new ResultVO<>(1, null, msg);
    }

}
