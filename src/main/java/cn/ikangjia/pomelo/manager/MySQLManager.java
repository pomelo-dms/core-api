package cn.ikangjia.pomelo.manager;

import cn.ikangjia.pomelo.api.query.DataQuery;
import cn.ikangjia.pomelo.core.entity.CharacterSetEntity;
import cn.ikangjia.pomelo.core.entity.DataEntity;
import cn.ikangjia.pomelo.core.entity.DatabaseEntity;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/13 14:48
 */
public interface MySQLManager {
    void createDatabase(long dataSourceId, DatabaseEntity database);

    void dropDatabase(long dataSourceId, String databaseName);

    void alterDatabase(long dataSourceId, String databaseName, String characterSet, String collation);

    DatabaseEntity getDatabaseInfo(long dataSourceId, String databaseName);

    List<String> listDatabase(long dataSourceId, boolean systemDatabaseShow);

    List<String> listTable(long dataSourceId, String databaseName);

    List<String> listView(long dataSourceId, String databaseName);

    List<CharacterSetEntity> listCharacterSets(Long dataSourceId);

    List<String> listCollations(Long dataSourceId, String characterSet);

    DataEntity showTableData(Long dataSourceId, DataQuery dataQuery);

    Long countTableDataRows(Long dataSourceId, String databaseName, String tableName);

}
