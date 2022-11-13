package cn.ikangjia.pomelo.api.vo.data;

import cn.ikangjia.pomelo.core.entity.DataEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/11 9:47
 */
@Data
public class DataShowVO {

    // 数据
    private DataEntity dataEntity;

    // 表数据总行数
    private Long total;
}
