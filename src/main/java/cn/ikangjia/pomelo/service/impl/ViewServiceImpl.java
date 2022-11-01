package cn.ikangjia.pomelo.service.impl;

import cn.ikangjia.pomelo.api.vo.TreeVO;
import cn.ikangjia.pomelo.common.util.TreeUtil;
import cn.ikangjia.pomelo.manager.MySQLManager;
import cn.ikangjia.pomelo.service.ViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/27 9:33
 */
@Slf4j
@Service
public class ViewServiceImpl implements ViewService {

    private final MySQLManager mySQLManager;

    public ViewServiceImpl(MySQLManager mySQLManager) {
        this.mySQLManager = mySQLManager;
    }


    @Override
    public List<TreeVO> listTree2View(Long dataSourceId, String databaseName) {
        List<String> viewNameList = mySQLManager.listView(dataSourceId, databaseName);

        return TreeUtil.buildLevel2(dataSourceId, databaseName, viewNameList, 1);
    }
}
