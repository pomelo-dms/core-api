package cn.ikangjia.pomelo.api.query;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/11 9:40
 */
@Data
public class DataQuery implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long dataSourceId;
    private String databaseName;
    private String tableName;


    private Integer pageNum;
    private Integer pageSize;
}
