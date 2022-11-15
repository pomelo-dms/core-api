package cn.ikangjia.pomelo.core.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/9 14:35
 */
@Data
public class TableEntity {

    private String dbName;
    private String tableName;

    private String characterSet;
    private String engine;

    private String comment;
}
