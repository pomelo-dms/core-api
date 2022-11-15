package cn.ikangjia.pomelo.api.dto.table;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/15 20:48
 */
@Data
public class ColumnCreateDTO {
    // 列名称
    private String columnName;

    private String typeInfo;

    // 默认值
    private String defaultValue;

    // 注释
    private String comment;

    // 是否允许是空
    private boolean isNull;

    // 是否唯一
    private boolean unique;

    // 是否自增
    private boolean increment;

    // 是否是主键
    private boolean primaryKey;
}
