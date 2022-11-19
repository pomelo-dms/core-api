package cn.ikangjia.pomelo.api.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/19 09:10
 */
@Data
public class DataOperateDTO {
    private Long dataSourceId;
    private String databaseName;
    private String tableName;

    private Map<String, List<Map<String, Object>>> insertData;

    private Map<String, List<Map<String, Object>>> updateData;

    // 更新的数据的原始数据
    private Map<String, List<Map<String, Object>>> updateOriginData;

    private Map<String, List<Map<String, Object>>> deleteData;
}
