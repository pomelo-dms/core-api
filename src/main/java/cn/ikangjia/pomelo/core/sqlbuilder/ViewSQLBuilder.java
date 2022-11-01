package cn.ikangjia.pomelo.core.sqlbuilder;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/27 9:44
 */
public interface ViewSQLBuilder {
    String view_select = """
            select table_name from information_schema.tables
            where table_schema = '%s' and table_type in('VIEW', 'SYSTEM VIEW');
            """;
}
