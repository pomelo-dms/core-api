package cn.ikangjia.pomelo.core.mysql.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/14 16:04
 */
@Data
public class DataEntity {
    private List<String> columnNameList;
    private List<Map<String, Object>> dataMapList;
}
