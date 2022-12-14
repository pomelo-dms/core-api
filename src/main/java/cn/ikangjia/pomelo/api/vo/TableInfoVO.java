package cn.ikangjia.pomelo.api.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/22 9:55
 */
@Data
public class TableInfoVO {
    private String databaseName;
    private String tableName;
    private String tableType;
    private String dataLength;
    private String engine;
    private String createTime;
    private String updateTime;
    private String collation;
    private String comment;

    private String rowNum; // 数据行数
    private String tableDDL;
}
