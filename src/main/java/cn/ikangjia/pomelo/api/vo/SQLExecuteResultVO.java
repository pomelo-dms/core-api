package cn.ikangjia.pomelo.api.vo;

import cn.ikangjia.pomelo.core.entity.DataEntity;
import lombok.Data;

import java.util.List;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/21 16:31
 */
@Data
public class SQLExecuteResultVO {
    private List<DataEntity> dataEntityList;
    private boolean hasResult;
}
