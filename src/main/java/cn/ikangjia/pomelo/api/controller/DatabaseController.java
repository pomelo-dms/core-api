package cn.ikangjia.pomelo.api.controller;

import cn.ikangjia.pomelo.api.dto.DatabaseAddDTO;
import cn.ikangjia.pomelo.api.dto.DatabaseAlterDTO;
import cn.ikangjia.pomelo.api.model.ResultVO;
import cn.ikangjia.pomelo.core.entity.DatabaseEntity;
import cn.ikangjia.pomelo.service.DatabaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/13 15:17
 */
@RestController
@RequestMapping("/api/v1/database")
public class DatabaseController {
    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping
    public ResultVO<List<String>> listDatabases(Long dataSourceId) {
        return ResultVO.success(databaseService.listDatabases(dataSourceId));
    }

    @GetMapping("/{databaseName}")
    public ResultVO<DatabaseEntity> getDatabaseInfo(Long dataSourceId, @PathVariable String databaseName) {
        return Optional.ofNullable(databaseService.getDatabaseInfo(dataSourceId, databaseName))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @PostMapping
    public ResultVO<Boolean> addDatabase(@RequestBody DatabaseAddDTO addDTO) {
        return Optional.ofNullable(databaseService.addDatabase(addDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @DeleteMapping("/{databaseName}")
    public ResultVO<Boolean> dropDatabase(Long dataSourceId, @PathVariable String databaseName) {
        return Optional.ofNullable(databaseService.dropDatabase(dataSourceId, databaseName))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @PutMapping
    public ResultVO<Boolean> alterDatabase(@RequestBody DatabaseAlterDTO alterDTO) {
        return Optional.ofNullable(databaseService.alterDatabase(alterDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }
}
