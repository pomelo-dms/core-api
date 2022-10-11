package cn.ikangjia.pomelo.core.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/9 15:00
 */
@Data
public class ColumnEntity {
    private String tableName;
    private String columnName;

    private String dataType;
}
