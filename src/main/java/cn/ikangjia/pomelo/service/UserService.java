package cn.ikangjia.pomelo.service;

import cn.ikangjia.pomelo.api.dto.system.UserLoginDTO;
import cn.ikangjia.pomelo.api.vo.UserVO;
import cn.ikangjia.pomelo.domain.entity.UserDO;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/8 9:07
 */
public interface UserService {

    UserDO getUser(Long id);

    UserVO doLogin(UserLoginDTO userLoginDTO);
}
