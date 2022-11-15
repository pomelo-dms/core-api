package cn.ikangjia.pomelo.api.controller;

import cn.ikangjia.pomelo.api.model.ResultVO;
import cn.ikangjia.pomelo.api.query.PageQuery;
import cn.ikangjia.pomelo.api.vo.DataSourceVO;
import cn.ikangjia.pomelo.api.vo.TestConnectionVO;
import cn.ikangjia.pomelo.domain.entity.DataSourceDO;
import cn.ikangjia.pomelo.service.DataSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/5 22:41
 */
@Api(tags = "数据源操作")
@RestController
@RequestMapping("/api/v1/dataSource")
public class DataSourceController {

    private final DataSourceService dataSourceService;

    public DataSourceController(DataSourceService dataSourceService) {
        this.dataSourceService = dataSourceService;
    }

    @ApiOperation(value = "根据 dataSourceId 查询数据源的详情")
    @GetMapping("/{dataSourceId}")
    public ResultVO<DataSourceDO> getDataSource(@PathVariable Long dataSourceId) {
        return Optional.ofNullable(dataSourceService.getDataSource(dataSourceId))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "数据源列表查询")
    @GetMapping
    public ResultVO<DataSourceVO> listDataSource(Integer pageSize, Integer pageNum, String keyword){
        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageSize(pageSize);
        pageQuery.setPageNum(pageNum);
        pageQuery.setKeyword(keyword);
        return Optional.ofNullable(dataSourceService.listDataSource(pageQuery))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "更新数据源信息")
    @PutMapping
    public ResultVO<Boolean> updateDataSource(@RequestBody DataSourceDO dataSourceDO){
        return Optional.ofNullable(dataSourceService.updateDataSource(dataSourceDO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "删除数据源")
    @DeleteMapping("/{dataSourceId}")
    public ResultVO<Boolean> removeDataSource(@PathVariable Long dataSourceId){
        return Optional.ofNullable(dataSourceService.removeDataSource(dataSourceId))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "批量删除数据源")
    @DeleteMapping("/batchDelete")
    public ResultVO<Boolean> removeDataSourceBatch(List<Long> dataSourceIdList){
        return Optional.ofNullable(dataSourceService.removeDataSourceBatch(dataSourceIdList))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "添加数据源")
    @PostMapping
    public ResultVO<Boolean> saveDataSource(@RequestBody DataSourceDO dataSourceDO){
        return Optional.ofNullable(dataSourceService.saveDataSource(dataSourceDO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    /**
     * 测试数据源的连通性
     *
     * @param dataSourceDO 数据源信息
     * @return 测试结果
     */
    @ApiOperation(value = "测试数据源的连通性")
    @PostMapping("/testConnection")
    public ResultVO<TestConnectionVO> testConnection(@RequestBody DataSourceDO dataSourceDO){
        return Optional.ofNullable(dataSourceService.testConnection(dataSourceDO))
                .map(ResultVO::success)
                .orElseThrow();
    }
}
