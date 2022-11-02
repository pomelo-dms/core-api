package cn.ikangjia.pomelo.manager.impl;

import cn.ikangjia.pomelo.core.ExecuteHandler;
import cn.ikangjia.pomelo.core.entity.CharacterSetEntity;
import cn.ikangjia.pomelo.core.entity.DatabaseEntity;
import cn.ikangjia.pomelo.core.sqlbuilder.CommonSQLBuilder;
import cn.ikangjia.pomelo.core.sqlbuilder.DatabaseSQLBuilder;
import cn.ikangjia.pomelo.core.sqlbuilder.TableSQLBuilder;
import cn.ikangjia.pomelo.core.sqlbuilder.ViewSQLBuilder;
import cn.ikangjia.pomelo.manager.MySQLManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            sql = String.format(DatabaseSQLBuilder.database_create_1, databaseName);
        } else {
            String collation = database.getCollation();
            if (!StringUtils.hasText(collation) || "默认".equalsIgnoreCase(collation)) {
                sql = String.format(DatabaseSQLBuilder.database_create_2, databaseName, characterSet);
            } else {
                sql = String.format(DatabaseSQLBuilder.database_create_3, databaseName, characterSet, collation);
            }
        }
        log.info("创建数据库:{}", sql);
        handler.execute(sql);
    }

    @Override
    public void dropDatabase(long dataSourceId, String databaseName) {
        String sql = String.format(DatabaseSQLBuilder.database_drop, databaseName);
        log.info("删除数据库:{}", sql);
        handler.execute(sql);
    }

    @Override
    public void alterDatabase(long dataSourceId, String databaseName, String collationName) {

    }

    @Override
    public DatabaseEntity getDatabaseInfo(long dataSourceId, String databaseName) {
        return null;
    }

    @Override
    public List<String> listDatabase(long dataSourceId, boolean systemTableShow) {
        List<Map<String, Object>> query = handler.executeQuery("show databases;");
        return query.stream()
                .map(map -> map.get("Database"))
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listTable(long dataSourceId, String databaseName) {
        List<Map<String, Object>> query = handler.executeQuery(String.format(TableSQLBuilder.table_select, databaseName));
        return query.stream()
                .map(map -> map.get("TABLE_NAME"))
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> listView(long dataSourceId, String databaseName) {
        List<Map<String, Object>> query = handler.executeQuery(String.format(ViewSQLBuilder.view_select, databaseName));
        return query.stream()
                .map(map -> map.get("TABLE_NAME"))
                .map(String::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<CharacterSetEntity> listCharacterSets(Long dataSourceId) {
        return handler.executeQuery(CharacterSetEntity.class, CommonSQLBuilder.select_character_Set);
    }

    @Override
    public List<String> listCollations(Long dataSourceId, String characterSet) {
        String sql = String.format(CommonSQLBuilder.select_collation, characterSet);
        List<Map<String, Object>> mapList = handler.executeQuery(sql);
        return mapList.stream()
                .map(map -> map.get("collation"))
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}
