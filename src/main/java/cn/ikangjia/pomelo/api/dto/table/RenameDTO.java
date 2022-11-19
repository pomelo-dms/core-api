package cn.ikangjia.pomelo.api.dto.table;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/19 09:22
 */
@Data
public class RenameDTO {
    private Long dataSourceId;
    private String databaseName;
    private String tableName;
    private String oldTableName;
}
