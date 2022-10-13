package cn.ikangjia.pomelo.manager;

import cn.ikangjia.pomelo.core.mysql.entity.DatabaseEntity;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/13 14:48
 */
public interface MySQLManager {
    void createDatabase(long dataSourceId, DatabaseEntity database);

    void dropDatabase(long dataSourceId, String databaseName);

    void alterDatabase(long dataSourceId, String databaseName, String collationName);

    void getDatabaseInfo(long dataSourceId, String databaseName);

    List<String> listDatabase(long dataSourceId, boolean systemTableShow);
}
