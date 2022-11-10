package cn.ikangjia.pomelo.core.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/10 15:48
 */
@Data
public class Database {
    private String databaseName;
    private String collation;
    private String characterSet;

    private String sqlPath;
}
