package cn.ikangjia.pomelo.api.controller;

import cn.ikangjia.pomelo.api.dto.TableCreateDTO;
import cn.ikangjia.pomelo.api.dto.TableDTO;
import cn.ikangjia.pomelo.api.model.ResultVO;
import net.sf.jsqlparser.schema.Table;
import org.springframework.web.bind.annotation.*;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/14 13:51
 */
@RestController
@RequestMapping("/api/v1/table")
public class TableController {

    @GetMapping
    public ResultVO<Void> listTables(Long dataSourceId, String databaseName) {
        return ResultVO.success(null);
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
