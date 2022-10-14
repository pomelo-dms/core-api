package cn.ikangjia.pomelo.api.dto;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/14 22:06
 */
@Data
public class TableDTO {
    private Long dataSourceId;
    private String databaseName;
    private String tableName;
}
