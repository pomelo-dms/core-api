package cn.ikangjia.pomelo.service;

import cn.ikangjia.pomelo.api.dto.sqlconsole.ExecuteDTO;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/21 13:49
 */
public interface SQLConsoleService {
    void executeSQL(ExecuteDTO executeDTO);
}
