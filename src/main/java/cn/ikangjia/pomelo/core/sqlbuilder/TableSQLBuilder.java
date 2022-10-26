package cn.ikangjia.pomelo.core.sqlbuilder;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/26 21:09
 */
public interface TableSQLBuilder {
    String table_select = """
            select table_name from information_schema.tables
            where table_schema = '%s';
            """;
}
