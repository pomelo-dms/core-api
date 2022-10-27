package cn.ikangjia.pomelo.service;

import cn.ikangjia.pomelo.api.vo.TreeVO;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/26 20:59
 */
public interface ViewService {
    /**
     * 查询指定库下所有的视图
     *
     * @param dataSourceId 数据源 id
     * @param databaseName 数据库名称
     * @return 表列表
     */
    List<TreeVO> listTree2View(Long dataSourceId, String databaseName);
}
