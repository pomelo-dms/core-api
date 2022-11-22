package cn.ikangjia.pomelo.core.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/9 14:35
 */
@Data
public class TableEntity {
    private String databaseName;
    private String tableName;
    private String tableType;
    private String dataLength;
    private String engine;
    private String createTime;
    private String updateTime;
    private String collation;
    private String comment;
}
