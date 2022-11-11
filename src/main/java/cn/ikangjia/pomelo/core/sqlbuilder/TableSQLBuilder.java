package cn.ikangjia.pomelo.core.sqlbuilder;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/26 21:09
 */
public interface TableSQLBuilder {
    String table_select = """
            select table_name from information_schema.tables
            where table_schema = '%s' and table_type = 'BASE TABLE';
            """;

    String table_select_data = """
            select * from %s.%s where 1 = 1 limit %s, %s;
            """;

    String table_Row_count = """
            select count(*) as total from %s.%s where 1 = 1;
            """;
}
