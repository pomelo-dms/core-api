package cn.ikangjia.pomelo.api.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/15 20:28
 */
@Data
@ApiModel("用户登录实体类")
public class UserLoginDTO {
    @ApiModelProperty(value = "用户名", example = "admin")
    private String account;

    @ApiModelProperty(value = "密码", example = "admin")
    private String password;

    @ApiModelProperty(value = "验证码", example = "9527")
    private String code;
}
