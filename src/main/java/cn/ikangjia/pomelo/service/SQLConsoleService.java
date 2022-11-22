package cn.ikangjia.pomelo.service;

import cn.ikangjia.pomelo.api.dto.sqlconsole.ExecuteDTO;
import cn.ikangjia.pomelo.core.entity.SQLResultEntity;

import java.util.List;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/21 13:49
 */
public interface SQLConsoleService {
    List<SQLResultEntity> executeSQL(ExecuteDTO executeDTO);
}
