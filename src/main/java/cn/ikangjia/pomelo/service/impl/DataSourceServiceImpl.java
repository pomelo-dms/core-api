package cn.ikangjia.pomelo.service.impl;

import cn.ikangjia.pomelo.api.query.PageQuery;
import cn.ikangjia.pomelo.api.vo.DataSourceVO;
import cn.ikangjia.pomelo.api.vo.TestConnectionVO;
import cn.ikangjia.pomelo.core.MySQLDataSourceUtil;
import cn.ikangjia.pomelo.domain.entity.DataSourceDO;
import cn.ikangjia.pomelo.domain.mapper.DataSourceMapper;
import cn.ikangjia.pomelo.service.DataSourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/5 09:57
 */
@Service
public class DataSourceServiceImpl implements DataSourceService {
    private final DataSourceMapper dataSourceMapper;

    public DataSourceServiceImpl(DataSourceMapper dataSourceMapper) {
        this.dataSourceMapper = dataSourceMapper;
    }

    @Override
    public DataSourceDO getDataSource(Long dataSourceId) {
        return dataSourceMapper.selectById(dataSourceId);
    }

    @Override
    public DataSourceVO listDataSource(PageQuery pageQuery) {
        IPage<DataSourceDO> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        QueryWrapper<DataSourceDO> wrapper = new QueryWrapper<>();

        if (StringUtils.hasText(pageQuery.getKeyword())) {
            wrapper.like("name", pageQuery.getKeyword());
        }
        IPage<DataSourceDO> selectPage = dataSourceMapper.selectPage(page, wrapper);

        DataSourceVO result = new DataSourceVO();
        result.setDataSourceDOList(selectPage.getRecords());
        result.setTotal(dataSourceMapper.selectCount(null));
        return result;
    }

    @Override
    public Boolean updateDataSource(DataSourceDO dataSourceDO) {
        return dataSourceMapper.updateById(dataSourceDO) == 1;
    }

    @Override
    public Boolean removeDataSource(Long dataSourceId) {
        return dataSourceMapper.deleteById(dataSourceId) == 1;
    }

    @Override
    public Boolean removeDataSourceBatch(List<Long> dataSourceIdList) {
        dataSourceMapper.deleteBatchIds(dataSourceIdList);
        return true;
    }

    @Override
    public Boolean saveDataSource(DataSourceDO dataSourceDO) {
        dataSourceMapper.insert(dataSourceDO);
        return true;
    }

    @Override
    public TestConnectionVO testConnection(DataSourceDO dataSourceDO) {
        TestConnectionVO result = new TestConnectionVO();
        switch (dataSourceDO.getType()) {
            case "1":
                try {
                    Boolean connected = MySQLDataSourceUtil.testConnection(dataSourceDO);
                    result.setConnected(connected);
                    result.setMsg("可成功连接");
                } catch (SQLException e) {
                    result.setConnected(false);
                    result.setMsg(e.getMessage());
                }
                return result;
            case "2":
                throw new RuntimeException("暂不支持的数据库类型");
        };
        return result;
    }
}
