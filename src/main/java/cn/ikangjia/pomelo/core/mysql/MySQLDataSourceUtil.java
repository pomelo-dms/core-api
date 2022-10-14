package cn.ikangjia.pomelo.core.mysql;

import cn.ikangjia.pomelo.domain.entity.DataSourceDO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/9 8:52
 */
public class MySQLDataSourceUtil {
    private static final String URL_TEMPLATE = "jdbc:mysql://%s:%s";

    /**
     * 获取 MySQL 类型的数据源连接
     *
     * @param dataSourceDO 数据源参数
     * @return 数据库连接
     * @throws SQLException 创建失败的异常
     */
    public static Connection getConnection(DataSourceDO dataSourceDO) throws SQLException {
        String username = dataSourceDO.getUsername();
        String password = dataSourceDO.getPassword();
        String host = dataSourceDO.getHost();
        String port = dataSourceDO.getPort();

        String url = String.format(URL_TEMPLATE, host, port);
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 测试 MySQL 数据源信息是否正确
     *
     * @param dataSourceDO 数据源参数
     * @return 数据库连接
     * @throws SQLException 创建失败的异常
     */
    public static Boolean testConnection(DataSourceDO dataSourceDO) throws SQLException {
        return testConnection(dataSourceDO.getHost(), dataSourceDO.getPort(), dataSourceDO.getUsername(), dataSourceDO.getPassword());
    }

    private static Boolean testConnection(String host, String port, String username, String password) throws SQLException {
        String url = String.format(URL_TEMPLATE, host, port);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return statement.execute("select 1");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
