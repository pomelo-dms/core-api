package cn.ikangjia.pomelo.manager.impl;

import cn.ikangjia.pomelo.api.query.DataQuery;
import cn.ikangjia.pomelo.core.ExecuteHandler;
import cn.ikangjia.pomelo.core.entity.CharacterSetEntity;
import cn.ikangjia.pomelo.core.entity.DataEntity;
import cn.ikangjia.pomelo.core.entity.Database;
import cn.ikangjia.pomelo.core.entity.DatabaseEntity;
import cn.ikangjia.pomelo.core.sqlbuilder.CommonSQL;
import cn.ikangjia.pomelo.core.sqlbuilder.DatabaseSQL;
import cn.ikangjia.pomelo.core.sqlbuilder.table.TableSQL;
import cn.ikangjia.pomelo.core.sqlbuilder.ViewSQL;
import cn.ikangjia.pomelo.manager.MySQLManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/13 14:54
 */
@Slf4j
@Service
public class MySQLManagerImpl implements MySQLManager {
    private final ExecuteHandler handler;

    public MySQLManagerImpl(ExecuteHandler handler) {
        this.handler = handler;
    }

    @Override
    public void createDatabase(long dataSourceId, DatabaseEntity database) {
        String databaseName = database.getDatabaseName();
        String characterSet = database.getCharacterSet();
        String sql;
        if (!StringUtils.hasText(characterSet) || "默认".equalsIgnoreCase(characterSet)) {
            sql = String.format(DatabaseSQL.database_create_1, databaseName);
        } else {
            String collation = database.getCollation();
            if (!StringUtils.hasText(collation) || "默认".equalsIgnoreCase(collation)) {
                sql = String.format(DatabaseSQL.database_create_2, databaseName, characterSet);
            } else {
                sql = String.format(DatabaseSQL.database_create_3, databaseName, characterSet, collation);
            }
        }
        log.info("创建数据库:{}", sql);
        handler.execute(sql);
    }

    @Override
    public void dropDatabase(long dataSourceId, String databaseName) {
        String sql = String.format(DatabaseSQL.database_drop, databaseName);
        log.info("删除数据库:{}", sql);
        handler.execute(sql);
    }

    @Override
    public void alterDatabase(long dataSourceId, String databaseName, String collation) {
        String sql = String.format(DatabaseSQL.database_alter, databaseName, collation);
        log.info("修改数据库:{}", sql);
        handler.execute(sql);
    }

    @Override
    public DatabaseEntity getDatabaseInfo(long dataSourceId, String databaseName) {
        String sql = String.format(DatabaseSQL.database_info, databaseName);
        List<Database> databaseList = handler.executeQuery(Database.class, sql);
        Database database = databaseList.get(0);

        DatabaseEntity databaseEntity = new DatabaseEntity();
        BeanUtils.copyProperties(database, databaseEntity);

        // 查询 MySQL 版本
        String version = handler.executeQueryString(DatabaseSQL.database_version, "version");
        databaseEntity.setVersion(version);

        // 查询 MySQL DDL
        String ddl = handler.executeQueryString(
                String.format(DatabaseSQL.database_ddl, databaseName),
                "Create Database"
        );
        databaseEntity.setDatabaseDDL(ddl);

        // 表数量
        String tableCount = handler.executeQueryString(
                String.format(DatabaseSQL.database_table_count, databaseName),
                "tableCount"
        );

        databaseEntity.setTableCount(tableCount);
        // 视图数量
        String viewCount = handler.executeQueryString(
                String.format(DatabaseSQL.database_view_count, databaseName),
                "viewCount"
        );
        databaseEntity.setViewCount(viewCount);
        // 存储过程数量

        return databaseEntity;
    }

    @Override
    public List<String> listDatabase(long dataSourceId, boolean systemTableShow) {
        return handler.executeQueryStrings("show databases;", "Database");
    }

    @Override
    public List<String> listTable(long dataSourceId, String databaseName) {
        return handler.executeQueryStrings(String.format(TableSQL.table_select, databaseName), "TABLE_NAME");
    }

    @Override
    public List<String> listView(long dataSourceId, String databaseName) {
        return handler.executeQueryStrings(String.format(ViewSQL.view_select, databaseName), "TABLE_NAME");
    }

    @Override
    public List<CharacterSetEntity> listCharacterSets(Long dataSourceId) {
        return handler.executeQuery(CharacterSetEntity.class, CommonSQL.select_character_Set);
    }

    @Override
    public List<String> listCollations(Long dataSourceId, String characterSet) {
        String sql = String.format(CommonSQL.select_collation, characterSet);
        return handler.executeQueryStrings(sql, "collation");
    }

    @Override
    public DataEntity showTableData(Long dataSourceId, DataQuery dataQuery) {
        String sql = String.format(TableSQL.table_select_data,
                dataQuery.getDatabaseName(),
                dataQuery.getTableName(),
                (dataQuery.getPageNum() - 1) * dataQuery.getPageSize(),
                dataQuery.getPageSize());
        return handler.executeQueryForData(sql);
    }

    @Override
    public Long countTableDataRows(Long dataSourceId, String databaseName, String tableName) {
        String sql = String.format(TableSQL.table_Row_count, databaseName, tableName);
        return Long.valueOf(handler.executeQueryString(sql, "total"));
    }
}
