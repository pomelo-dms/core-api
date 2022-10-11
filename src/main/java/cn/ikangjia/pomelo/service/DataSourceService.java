package cn.ikangjia.pomelo.service;

import cn.ikangjia.pomelo.api.query.PageQuery;
import cn.ikangjia.pomelo.api.vo.DataSourceVO;
import cn.ikangjia.pomelo.domain.entity.DataSourceDO;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/5 09:56
 */
public interface DataSourceService {

    DataSourceDO getDataSource(Long dataSourceId);

    DataSourceVO listDataSource(PageQuery pageQuery);

    Boolean updateDataSource(DataSourceDO dataSourceDO);

    Boolean removeDataSource(Long dataSourceId);

    Boolean removeDataSourceBatch(List<Long> dataSourceIdList);

    Boolean saveDataSource(DataSourceDO dataSourceDO);

    /**
     * 测试 MySQL 数据源的连通性
     *
     * @param dataSourceDO 数据源信息
     * @return 测试结果
     */
    Boolean testConnection(DataSourceDO dataSourceDO);

}
