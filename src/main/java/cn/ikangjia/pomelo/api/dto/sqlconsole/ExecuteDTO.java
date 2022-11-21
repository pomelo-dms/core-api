package cn.ikangjia.pomelo.api.dto.sqlconsole;

import lombok.Data;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/21 13:47
 */
@Data
public class ExecuteDTO {
    private Long dataSourceId;
    private String databaseName;
    private String sqlStr;

}
