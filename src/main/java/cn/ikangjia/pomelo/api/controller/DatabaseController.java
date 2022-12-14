package cn.ikangjia.pomelo.api.controller;

import cn.ikangjia.pomelo.api.dto.DatabaseAddDTO;
import cn.ikangjia.pomelo.api.dto.DatabaseAlterDTO;
import cn.ikangjia.pomelo.api.model.ResultVO;
import cn.ikangjia.pomelo.api.vo.DatabaseInfoVO;
import cn.ikangjia.pomelo.api.vo.TreeVO;
import cn.ikangjia.pomelo.core.entity.CharacterSetEntity;
import cn.ikangjia.pomelo.service.DatabaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/13 15:17
 */
@Api(tags = "数据库操作")
@RestController
@RequestMapping("/api/v1/database")
public class DatabaseController {
    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @ApiOperation(value = "树形结构一级信息查询")
    @GetMapping("/tree0")
    public ResultVO<List<TreeVO>> listDatabases(Long dataSourceId) {
        return ResultVO.success(databaseService.listDatabases(dataSourceId));
    }

    @ApiOperation(value = "树形结构二级信息查询")
    @GetMapping("/tree1")
    public ResultVO<List<TreeVO>> listTree1(String databaseName, Long dataSourceId) {
        return ResultVO.success(databaseService.listTree1(dataSourceId, databaseName));
    }

    @ApiOperation(value = "获取数据库的对象信息")
    @GetMapping("/{databaseName}")
    public ResultVO<DatabaseInfoVO> getDatabaseInfo(Long dataSourceId, @PathVariable String databaseName) {
        return Optional.ofNullable(databaseService.getDatabaseInfo(dataSourceId, databaseName))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "获取数据库字符集列表")
    @GetMapping("/characterSet")
    public ResultVO<List<CharacterSetEntity>> getCharacterSets(Long dataSourceId) {
        return Optional.ofNullable(databaseService.getCharacterSets(dataSourceId))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "获取指定字符集的校验规则")
    @GetMapping("/collation")
    public ResultVO<List<String>> getCharacterSets(Long dataSourceId, String characterSet) {
        return Optional.ofNullable(databaseService.getCollations(dataSourceId, characterSet))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "添加数据库")
    @PostMapping
    public ResultVO<Boolean> addDatabase(@RequestBody DatabaseAddDTO addDTO) {
        return Optional.ofNullable(databaseService.addDatabase(addDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "删除数据库")
    @DeleteMapping("/{databaseName}")
    public ResultVO<Boolean> dropDatabase(Long dataSourceId, @PathVariable String databaseName) {
        return Optional.ofNullable(databaseService.dropDatabase(dataSourceId, databaseName))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "更新数据库（修改字符集）")
    @PutMapping
    public ResultVO<Boolean> alterDatabase(@RequestBody DatabaseAlterDTO alterDTO) {
        return Optional.ofNullable(databaseService.alterDatabase(alterDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @ApiOperation(value = "获取数据库默认的字符集和校验规则")
    @GetMapping("/defaultCharacter")
    public ResultVO<DatabaseAlterDTO> defaultCharacter(Long dataSourceId, String databaseName) {
        return Optional.ofNullable(databaseService.defaultCharacter(dataSourceId, databaseName))
                .map(ResultVO::success)
                .orElseThrow();
    }
}
