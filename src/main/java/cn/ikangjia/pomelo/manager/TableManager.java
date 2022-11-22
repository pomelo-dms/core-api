package cn.ikangjia.pomelo.manager;

import cn.ikangjia.pomelo.api.dto.table.RenameDTO;
import cn.ikangjia.pomelo.api.dto.table.TableCreateDTO;
import cn.ikangjia.pomelo.api.vo.TableInfoVO;
import cn.ikangjia.pomelo.core.entity.TableEntity;

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

    Boolean renameTable(Long dataSourceId, RenameDTO renameDTO);

    TableInfoVO getTableInfo(Long dataSourceId, String databaseName, String tableName);
}
