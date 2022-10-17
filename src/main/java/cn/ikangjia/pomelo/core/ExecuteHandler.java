package cn.ikangjia.pomelo.core;

import cn.ikangjia.pomelo.core.entity.DataEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL 执行器
 *
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/9 15:06
 */
@Slf4j
@Component
public class ExecuteHandler {
    private final JdbcThreadLocal jdbcThreadLocal;

    public ExecuteHandler(JdbcThreadLocal jdbcThreadLocal) {
        this.jdbcThreadLocal = jdbcThreadLocal;
    }

    /**
     * 可用于执行任何 SQL 语句，返回一个 boolean 值，表明执行该 SQL 语句是否返回了 ResultSet
     *
     * @param sql sql 语句
     * @return 执行结果有 ResultSet 则返回 true，没有则返回 false
     */
    public boolean execute(String sql) {
        try (Statement statement = this.getStatement()) {
            return statement.execute(sql);
        } catch (SQLException e) {
            throw new DMSException(e.getMessage(), e);
        }
    }

    /**
     * 执行 INSERT、UPDATE 或 DELETE 语句以及 SQL DDL（数据定义语言）语句
     *
     * @param sql 要执行的 sql 语句
     * @return 执行结果。INSERT、UPDATE 或 DELETE 返回受影响行数，DDL 返回 0
     */
    public Integer executeUpdate(String sql) {
        try (Statement statement = this.getStatement()) {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DMSException(e.getMessage(), e);
        }
    }

    public <T> List<T> executeQuery(Class<T> t, String sql, String... params) {
        try (PreparedStatement statement = this.getPrepareStatement(sql, params)) {
            ResultSet rs = statement.executeQuery();
            return doObjectResult(t, rs);
        } catch (SQLException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询某表的数据
     *
     * @param sql    sql
     * @param params 参数
     * @return 数据
     */
    public DataEntity executeQueryForData(String sql, String... params) {
        DataEntity result = new DataEntity();
        List<String> columnNameList = new ArrayList<>();
        List<Map<String, Object>> dataListMap;

        try (PreparedStatement statement = this.getPrepareStatement(sql, params)) {
            ResultSet rs = statement.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNameList.add(metaData.getColumnLabel(i));
            }
            dataListMap = doMapResult(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        result.setDataMapList(dataListMap);
        result.setColumnNameList(columnNameList);
        return result;
    }

//    public List<Object> executeQuery(Class t, String sql, String... params) {
//        try (PreparedStatement statement = this.getPrepareStatement(sql, params)) {
//            ResultSet rs = statement.executeQuery();
//            return doObjectResult(t, rs);
//        } catch (SQLException | InstantiationException | IllegalAccessException e) {
//            throw new RuntimeException(e.getMessage(), e);
//        }
//    }

    public List<Map<String, Object>> executeQuery(String sql, String... params) {
        try (PreparedStatement statement = this.getPrepareStatement(sql, params)) {
            ResultSet rs = statement.executeQuery();
            return doMapResult(rs);
        } catch (SQLException e) {
            throw new DMSException(e.getMessage(), e);
        }
    }


    private List<Map<String, Object>> doMapResult(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        List<Map<String, Object>> result = new ArrayList<>(columnCount);
        while (rs.next()) {
            Map<String, Object> temMap = new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                temMap.put(metaData.getColumnLabel(i + 1), rs.getObject(i + 1));
            }
            result.add(temMap);
        }
        return result;
    }

    private <T> List<T> doObjectResult(Class<T> t, ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        Field[] fields = t.getDeclaredFields();

        List<T> result = new ArrayList<>(columnCount);
        while (rs.next()) {
            T obj = t.getDeclaredConstructor().newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(obj, String.valueOf(rs.getObject(field.getName())));
            }
            result.add(obj);
        }
        return result;
    }

    private Statement getStatement() {
        Connection context = jdbcThreadLocal.getContext();
        try {
            return context.createStatement();
        } catch (SQLException e) {
            log.error("获取 Statement 失败{}", e.getMessage());
            throw new DMSException(e.getMessage(), e);
        }
    }

    private PreparedStatement getPrepareStatement(String sqlTemplate, String... params) {
        Connection context = jdbcThreadLocal.getContext();
        try {
            PreparedStatement statement = context.prepareStatement(sqlTemplate);
            if (params == null || params.length == 0) {
                return statement;
            }
            for (int i = 0, j = params.length; i < j; i++) {
                statement.setObject(i + 1, params[i]);
            }
            return statement;
        } catch (SQLException e) {
            log.error("获取 PrepareStatement 失败{}", e.getMessage());
            throw new DMSException(e.getMessage(), e);
        }
    }

}