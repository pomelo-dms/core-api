package cn.ikangjia.pomelo.api.controller;

import cn.ikangjia.pomelo.api.dto.TableCreateDTO;
import cn.ikangjia.pomelo.api.dto.TableDTO;
import cn.ikangjia.pomelo.api.model.ResultVO;
import cn.ikangjia.pomelo.api.vo.TreeVO;
import cn.ikangjia.pomelo.service.TableService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/14 13:51
 */
@RestController
@RequestMapping("/api/v1/table")
public class TableController {

    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping("/tree")
    public ResultVO<List<TreeVO>> listTables(Long dataSourceId, String databaseName) {
        return Optional.ofNullable(tableService.listTree2Table(dataSourceId, databaseName))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @GetMapping("/tableInfo")
    public ResultVO<Void> getTableInfo(TableDTO tableDTO) {
        return ResultVO.success(null);
    }

    @DeleteMapping
    public ResultVO<Void> dropTable(TableDTO tableDTO) {
        return ResultVO.success(null);
    }

    @PutMapping
    public ResultVO<Void> alterTable(TableDTO tableDTO) {
        return ResultVO.success(null);
    }

    @PostMapping
    public ResultVO<Void> createTable(TableCreateDTO tableCreateDTO) {
        return ResultVO.success(null);
    }

    @DeleteMapping("/truncate")
    public ResultVO<Void> truncateTable(Long dataSourceId, String databaseName) {
        return ResultVO.success(null);
    }

    @DeleteMapping("/clear")
    public ResultVO<Void> clearTable(TableDTO tableDTO) {
        return ResultVO.success(null);
    }

    @GetMapping("/ddl")
    public ResultVO<Void> getTableDDLSQL(TableDTO tableDTO) {
        return ResultVO.success(null);
    }

    @PostMapping("/similarTable")
    public ResultVO<Void> createSimilarTable(TableDTO tableDTO) {
        return ResultVO.success(null);
    }

    @PutMapping("/rename")
    public ResultVO<Void> renameTable(TableDTO tableDTO) {
        return ResultVO.success(null);
    }
}
