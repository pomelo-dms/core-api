package cn.ikangjia.pomelo.service.impl;

import cn.ikangjia.pomelo.api.dto.DatabaseAddDTO;
import cn.ikangjia.pomelo.api.dto.DatabaseAlterDTO;
import cn.ikangjia.pomelo.api.vo.TreeVO;
import cn.ikangjia.pomelo.common.util.TreeUtil;
import cn.ikangjia.pomelo.core.entity.CharacterSetEntity;
import cn.ikangjia.pomelo.core.entity.DatabaseEntity;
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
    public List<TreeVO> listDatabases(Long dataSourceId) {
        List<String> dbNameList = mySQLManager.listDatabase(dataSourceId, true);
        return TreeUtil.buildLevel0(dataSourceId, dbNameList);
    }

    @Override
    public DatabaseEntity getDatabaseInfo(Long dataSourceId, String databaseName) {
        return null;
    }

    @Override
    public Boolean addDatabase(DatabaseAddDTO addDTO) {
        DatabaseEntity database = new DatabaseEntity();
        database.setDatabaseName(addDTO.getDatabaseName());
        database.setCharacterName(addDTO.getCharacterSet());
        database.setCollationName(addDTO.getCollation());
        mySQLManager.createDatabase(addDTO.getDataSourceId(), database);
        return true;
    }

    @Override
    public Boolean dropDatabase(Long dataSourceId, String databaseName) {
        return false;
    }

    @Override
    public Boolean alterDatabase(DatabaseAlterDTO alterDTO) {
        return false;
    }

    @Override
    public List<TreeVO> listTree1(Long dataSourceId, String databaseName) {
        return TreeUtil.buildLevel1(dataSourceId, databaseName);
    }

    @Override
    public List<CharacterSetEntity> getCharacterSets(Long dataSourceId) {
        CharacterSetEntity defaultCharacterSet = new CharacterSetEntity();
        defaultCharacterSet.setCharacterSet("默认");
        defaultCharacterSet.setDefaultCollation("默认");
        defaultCharacterSet.setDefaultCollation("这是默认的字符集");
        List<CharacterSetEntity> characterSetEntityList = mySQLManager.listCharacterSets(dataSourceId);
        characterSetEntityList.add(0, defaultCharacterSet);
        return characterSetEntityList;
    }

    @Override
    public List<String> getCollations(Long dataSourceId, String characterSet) {
        List<String> collationList = mySQLManager.listCollations(dataSourceId, characterSet);
        collationList.add(0, "默认");
        return collationList;
    }
}
