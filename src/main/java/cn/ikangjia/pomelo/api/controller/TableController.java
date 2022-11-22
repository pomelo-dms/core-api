package cn.ikangjia.pomelo.api.controller;

import cn.ikangjia.pomelo.api.dto.table.RenameDTO;
import cn.ikangjia.pomelo.api.dto.table.TableDTO;
import cn.ikangjia.pomelo.api.dto.table.TableCreateDTO;
import cn.ikangjia.pomelo.api.model.ResultVO;
import cn.ikangjia.pomelo.api.query.DataQuery;
import cn.ikangjia.pomelo.api.vo.TableInfoVO;
import cn.ikangjia.pomelo.api.vo.TreeVO;
import cn.ikangjia.pomelo.api.vo.data.DataShowVO;
import cn.ikangjia.pomelo.service.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/14 13:51
 */
@Api(tags = "表操作")
@RestController
@RequestMapping("/api/v1/table")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @ApiOperation(value = "树形结构三级信息查询：表列表")
    @GetMapping("/tree")
    public ResultVO<List<TreeVO>> listTables(Long dataSourceId, String databaseName) {
        return Optional.ofNullable(tableService.listTree2Table(dataSourceId, databaseName))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "表对象信息查询")
    @GetMapping("/tableInfo")
    public ResultVO<TableInfoVO> getTableInfo(TableDTO tableDTO) {
        return Optional.of(tableService.getTableInfo(tableDTO))
                .map(ResultVO::success)
                .get();
    }

    @ApiOperation(value = "删除表")
    @DeleteMapping
    public ResultVO<Boolean> dropTable(TableDTO tableDTO) {
        return Optional.ofNullable(tableService.dropTable(tableDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }
//
//    @ApiOperation(value = "修改表：更难更难")
//    @PutMapping
//    public ResultVO<Void> alterTable(TableDTO tableDTO) {
//        return ResultVO.success(null);
//    }

    @ApiOperation(value = "创建表：真难真难")
    @PostMapping
    public ResultVO<String> createTable(@RequestBody TableCreateDTO tableCreateDTO) {
        return Optional.ofNullable(tableService.createTable(tableCreateDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "获取列字段类型信息")
    @GetMapping("/dataType")
    public ResultVO<List<String>> listDataTypes() {
        return Optional.ofNullable(tableService.listDataTypes())
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "截断表")
    @DeleteMapping("/truncate")
    public ResultVO<Boolean> truncateTable(TableDTO tableDTO) {
        return Optional.ofNullable(tableService.truncateTable(tableDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "清空表")
    @DeleteMapping("/clear")
    public ResultVO<Boolean> clearTable(TableDTO tableDTO) {
        return Optional.ofNullable(tableService.clearTable(tableDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }
//
//    @ApiOperation(value = "获取建表 DDL 语句")
//    @GetMapping("/ddl")
//    public ResultVO<Void> getTableDDLSQL(TableDTO tableDTO) {
//        return ResultVO.success(null);
//    }
//
//    @ApiOperation(value = "创建相似表（复制表）")
//    @PostMapping("/similarTable")
//    public ResultVO<Void> createSimilarTable(TableDTO tableDTO) {
//        return ResultVO.success(null);
//    }

    @ApiOperation(value = "重命名表")
    @PutMapping("/rename")
    public ResultVO<Boolean> renameTable(@RequestBody RenameDTO renameDTO) {
        return Optional.ofNullable(tableService.renameTable(renameDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "表数据查询（打开表）")
    @GetMapping("/data")
    public ResultVO<DataShowVO> showData(Long dataSourceId, String databaseName, String tableName, Integer pageSize, Integer pageNum) {
        DataQuery dataQuery = new DataQuery();
        dataQuery.setDataSourceId(dataSourceId);
        dataQuery.setTableName(tableName);
        dataQuery.setDatabaseName(databaseName);
        dataQuery.setPageNum(pageNum);
        dataQuery.setPageSize(pageSize);
        return Optional.ofNullable(tableService.showData(dataQuery.getDataSourceId(), dataQuery))
                .map(ResultVO::success)
                .orElseThrow();
    }
}
