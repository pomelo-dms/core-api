package cn.ikangjia.pomelo.manager.impl;

import cn.ikangjia.pomelo.core.ExecuteHandler;
import cn.ikangjia.pomelo.core.constants.TableConstants;
import cn.ikangjia.pomelo.core.entity.DatabaseEntity;
import cn.ikangjia.pomelo.core.sqlbuilder.TableSQLBuilder;
import cn.ikangjia.pomelo.core.sqlbuilder.ViewSQLBuilder;
import cn.ikangjia.pomelo.manager.MySQLManager;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/13 14:54
 */
@Service
public class MySQLManagerImpl implements MySQLManager {
    private final ExecuteHandler handler;

    public MySQLManagerImpl(ExecuteHandler handler) {
        this.handler = handler;
    }

    @Override
    public void createDatabase(long dataSourceId, DatabaseEntity database) {
        List<Map<String, Object>> query = handler.executeQuery("show databases;");
        System.out.println(query);

    }

    @Override
    public void dropDatabase(long dataSourceId, String databaseName) {


    }

    @Override
    public void alterDatabase(long dataSourceId, String databaseName, String collationName) {

    }

    @Override
    public void getDatabaseInfo(long dataSourceId, String databaseName) {

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
}
