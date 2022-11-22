package cn.ikangjia.pomelo.api.dto;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/14 13:56
 */
@Data
public class DatabaseAlterDTO {
    private Long dataSourceId;
    private String databaseName;
    private String characterSet;
    private String collation;
}
