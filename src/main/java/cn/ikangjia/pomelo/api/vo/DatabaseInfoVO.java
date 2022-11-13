package cn.ikangjia.pomelo.api.vo;

import cn.ikangjia.pomelo.domain.entity.DataSourceDO;
import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/10 14:55
 */
@Data
public class DatabaseInfoVO {
    private DataSourceDO dataSourceDO;

    private String databaseName;
    private String collation;
    private String characterSet;

    private String version;

    private String databaseDDL;

    private String sqlPath;

    private String tableCount;
    private String viewCount;
    private String procedureCount;
    private String functionCount;
}
