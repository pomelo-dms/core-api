package cn.ikangjia.pomelo.core.sqlbuilder;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/26 21:09
 */
public interface TableSQL {
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

    /*
     * https://www.yiibai.com/mysql/create-table.html
     * CREATE TABLE [IF NOT EXISTS] table_name(
     *         column_list
     * ) engine=table_type;
     *
     * 列区域：
     * column_name data_type[size] [NOT NULL|NULL] [DEFAULT value]
     * [AUTO_INCREMENT]
     *
     * PRIMARY KEY (col1,col2,...)
     *
     * 示例：
     ** CREATE TABLE IF NOT EXISTS tasks (
      *    task_id INT(11) NOT NULL AUTO_INCREMENT,
      *    subject VARCHAR(45) DEFAULT NULL,
      *    start_date DATE DEFAULT NULL,
      *    end_date DATE DEFAULT NULL,
      *    description VARCHAR(200) DEFAULT NULL,
      *    PRIMARY KEY (task_id)
      *  ) ENGINE=InnoDB;
      *
     */

    String table_create = """
            create table %s (
            
            ) engine=%s;
            """;
    String table_column = """
            %s %s %s
            """;
}
