package cn.ikangjia.pomelo.api.dto.table;

import lombok.Data;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/15 20:46
 */
@Data
public class TableCreateDTO {
    private Long dataSourceId;
    private String databaseName;

    private String tableName;
    private String comment;

    private List<ColumnCreateDTO> columnCreateDTOList;
}
