package cn.ikangjia.pomelo.service;

import cn.ikangjia.pomelo.api.dto.table.RenameDTO;
import cn.ikangjia.pomelo.api.dto.table.TableDTO;
import cn.ikangjia.pomelo.api.dto.table.TableCreateDTO;
import cn.ikangjia.pomelo.api.query.DataQuery;
import cn.ikangjia.pomelo.api.vo.TableInfoVO;
import cn.ikangjia.pomelo.api.vo.TreeVO;
import cn.ikangjia.pomelo.api.vo.data.DataShowVO;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/26 20:59
 */
public interface TableService {
    /**
     * 查询指定库下所有的表
     *
     * @param dataSourceId 数据源 id
     * @param databaseName 数据库名称
     * @return 表列表
     */
    List<TreeVO> listTree2Table(Long dataSourceId, String databaseName);

    DataShowVO showData(Long dataSourceId, DataQuery dataQuery);

    String createTable(TableCreateDTO tableCreateDTO);

    List<String> listDataTypes();

    Boolean clearTable(TableDTO tableDTO);

    Boolean truncateTable(TableDTO tableDTO);

    Boolean dropTable(TableDTO tableDTO);

    Boolean renameTable(RenameDTO renameDTO);

    TableInfoVO getTableInfo(TableDTO tableDTO);
}
