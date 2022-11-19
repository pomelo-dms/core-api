package cn.ikangjia.pomelo.manager;

import cn.ikangjia.pomelo.api.dto.table.TableCreateDTO;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/15 21:06
 */
public interface TableManager {
    void createTable(long dataSourceId, TableCreateDTO tableCreateDTO);

    Boolean clearTable(Long dataSourceId, String databaseName, String tableName);

    Boolean truncateTable(Long dataSourceId, String databaseName, String tableName);

    Boolean dropTable(Long dataSourceId, String databaseName, String tableName);
}
