package cn.ikangjia.pomelo.service;

import cn.ikangjia.pomelo.api.dto.DatabaseAddDTO;
import cn.ikangjia.pomelo.api.dto.DatabaseAlterDTO;
import cn.ikangjia.pomelo.api.vo.TreeVO;
import cn.ikangjia.pomelo.core.entity.DatabaseEntity;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/13 15:19
 */
public interface DatabaseService {
    List<TreeVO> listDatabases(Long dataSourceId);

    DatabaseEntity getDatabaseInfo(Long dataSourceId, String databaseName);

    Boolean addDatabase(DatabaseAddDTO addDTO);

    Boolean dropDatabase(Long dataSourceId, String databaseName);

    Boolean alterDatabase(DatabaseAlterDTO alterDTO);

    List<TreeVO> listTree1(Long dataSourceId, String databaseName);
}
