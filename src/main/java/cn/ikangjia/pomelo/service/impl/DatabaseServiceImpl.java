package cn.ikangjia.pomelo.service.impl;

import cn.ikangjia.pomelo.manager.MySQLManager;
import cn.ikangjia.pomelo.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/13 15:20
 */
@Slf4j
@Service
public class DatabaseServiceImpl implements DatabaseService {
    private final MySQLManager mySQLManager;

    public DatabaseServiceImpl(MySQLManager mySQLManager) {
        this.mySQLManager = mySQLManager;
    }

    @Override
    public List<String> listDatabases(Long dataSourceId) {
        return mySQLManager.listDatabase(dataSourceId, true);
    }
}
