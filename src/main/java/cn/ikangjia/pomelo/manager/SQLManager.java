package cn.ikangjia.pomelo.manager;

import cn.ikangjia.pomelo.core.entity.SQLResultEntity;

import java.util.List;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/21 14:10
 */
public interface SQLManager {
    List<SQLResultEntity> executeSQLBatch(Long dataSourceId, String databaseName, List<String> sqlList);
}
