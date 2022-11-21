package cn.ikangjia.pomelo.service.impl;

import cn.ikangjia.pomelo.api.dto.sqlconsole.ExecuteDTO;
import cn.ikangjia.pomelo.core.entity.SQLResultEntity;
import cn.ikangjia.pomelo.manager.SQLManager;
import cn.ikangjia.pomelo.service.SQLConsoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author kangjia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/21 14:06
 */
@Slf4j
@Service
public class SQLConsoleServiceImpl implements SQLConsoleService {

    private final SQLManager sqlManager;

    public SQLConsoleServiceImpl(SQLManager sqlManager) {
        this.sqlManager = sqlManager;
    }

    @Override
    public List<SQLResultEntity> executeSQL(ExecuteDTO executeDTO) {
        Long dataSourceId = executeDTO.getDataSourceId();
        String databaseName = executeDTO.getDatabaseName();
        String sqlStr = executeDTO.getSqlStr();

        // 拆分并校验 SQL 语句
        List<String> sqlList = handleSQLStr(sqlStr);
        List<SQLResultEntity> sqlResultEntityList = sqlManager.executeSQLBatch(dataSourceId, databaseName, sqlList);

        // 结果进一步处理
        System.out.println(sqlResultEntityList);
    }

    private List<String> handleSQLStr(String sqlStr) {
        return Arrays.stream(sqlStr.split(";")).toList();
    }
}
