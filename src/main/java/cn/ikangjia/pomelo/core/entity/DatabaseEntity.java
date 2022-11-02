package cn.ikangjia.pomelo.core.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/9 14:35
 */
@Data
public class DatabaseEntity {
    private String databaseName;
    private String collation;
    private String characterSet;

    private String version;

    private String databaseDDL;

    private String sqlPath;

    private String tableCount;
    private String viewCount;
    private String procedureCount;
    private String functionCount;
}
