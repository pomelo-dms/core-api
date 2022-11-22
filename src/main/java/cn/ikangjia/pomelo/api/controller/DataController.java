package cn.ikangjia.pomelo.api.controller;

import cn.ikangjia.pomelo.api.dto.DataOperateDTO;
import cn.ikangjia.pomelo.api.model.ResultVO;
import cn.ikangjia.pomelo.service.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/11 9:38
 */

@Api(tags = "数据操作")
@RestController
@RequestMapping("/data")
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @ApiOperation("操作表数据")
    public ResultVO<Boolean> operateData(@RequestBody DataOperateDTO dataOperateDTO) {
        return Optional.ofNullable(dataService.operateData(dataOperateDTO))
                .map(ResultVO::success)
                .orElseThrow();
    }

}
