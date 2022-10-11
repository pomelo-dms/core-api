package cn.ikangjia.pomelo.service;

import cn.ikangjia.pomelo.domain.entity.UserDO;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/8 9:07
 */
public interface UserService {

    UserDO getUser(Long id);

    String doLogin(UserDO userDO);
}
