package cn.ikangjia.pomelo.service.impl;

import cn.ikangjia.pomelo.api.vo.TreeVO;
import cn.ikangjia.pomelo.common.util.TreeUtil;
import cn.ikangjia.pomelo.manager.MySQLManager;
import cn.ikangjia.pomelo.service.TableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/26 21:03
 */
@Slf4j
@Service
public class TableServiceImpl implements TableService {

    private final MySQLManager mySQLManager;

    public TableServiceImpl(MySQLManager mySQLManager) {
        this.mySQLManager = mySQLManager;
    }
    @Override
    public List<TreeVO> listTree2Table(Long dataSourceId, String databaseName) {
        // 查询出指定数据源下的指定数据库下的所有的表名称列表
        List<String> tableNameList = mySQLManager.listTable(dataSourceId, databaseName);
        // 构造树形数据返并回
        return TreeUtil.buildLevel2(dataSourceId, databaseName, tableNameList, 0);
    }
}
