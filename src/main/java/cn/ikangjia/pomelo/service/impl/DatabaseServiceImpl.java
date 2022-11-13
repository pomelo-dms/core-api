package cn.ikangjia.pomelo.service.impl;

import cn.ikangjia.pomelo.api.dto.DatabaseAddDTO;
import cn.ikangjia.pomelo.api.dto.DatabaseAlterDTO;
import cn.ikangjia.pomelo.api.vo.DataSourceVO;
import cn.ikangjia.pomelo.api.vo.DatabaseInfoVO;
import cn.ikangjia.pomelo.api.vo.TreeVO;
import cn.ikangjia.pomelo.common.util.TreeUtil;
import cn.ikangjia.pomelo.core.entity.CharacterSetEntity;
import cn.ikangjia.pomelo.core.entity.DatabaseEntity;
import cn.ikangjia.pomelo.domain.entity.DataSourceDO;
import cn.ikangjia.pomelo.domain.mapper.DataSourceMapper;
import cn.ikangjia.pomelo.manager.MySQLManager;
import cn.ikangjia.pomelo.service.DatabaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    private final DataSourceMapper dataSourceMapper;

    public DatabaseServiceImpl(MySQLManager mySQLManager, DataSourceMapper dataSourceMapper) {
        this.mySQLManager = mySQLManager;
        this.dataSourceMapper = dataSourceMapper;
    }

    @Override
    public List<TreeVO> listDatabases(Long dataSourceId) {
        List<String> dbNameList = mySQLManager.listDatabase(dataSourceId, true);
        return TreeUtil.buildLevel0(dataSourceId, dbNameList);
    }

    @Override
    public DatabaseInfoVO getDatabaseInfo(Long dataSourceId, String databaseName) {
        // 获取数据源信息
        DataSourceDO dataSourceDO = dataSourceMapper.selectById(dataSourceId);
        dataSourceDO.setPassword("");

        // 获取 mysql 相关信息
        DatabaseInfoVO result = new DatabaseInfoVO();
        result.setDataSourceDO(dataSourceDO);

        DatabaseEntity databaseInfo = mySQLManager.getDatabaseInfo(dataSourceId, databaseName);
        BeanUtils.copyProperties(databaseInfo, result);
        System.out.println(result);
        return result;
    }

    @Override
    public Boolean addDatabase(DatabaseAddDTO addDTO) {
        DatabaseEntity database = new DatabaseEntity();
        database.setDatabaseName(addDTO.getDatabaseName());
        database.setCharacterSet(addDTO.getCharacterSet());
        database.setCollation(addDTO.getCollation());
        mySQLManager.createDatabase(addDTO.getDataSourceId(), database);
        return true;
    }

    @Override
    public Boolean dropDatabase(Long dataSourceId, String databaseName) {
        mySQLManager.dropDatabase(dataSourceId, databaseName);
        return true;
    }

    @Override
    public Boolean alterDatabase(DatabaseAlterDTO alterDTO) {
        mySQLManager.alterDatabase(alterDTO.getDataSourceId(), alterDTO.getDatabaseName(), alterDTO.getCollation());
        return true;
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
