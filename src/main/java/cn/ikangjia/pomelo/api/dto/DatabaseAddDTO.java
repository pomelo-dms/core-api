package cn.ikangjia.pomelo.api.dto;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/14 13:57
 */
@Data
public class DatabaseAddDTO {
    private Long dataSourceId;
    private String databaseName;
    private String collationName;
    private String characterName;
}
