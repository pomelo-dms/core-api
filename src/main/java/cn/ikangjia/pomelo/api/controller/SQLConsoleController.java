package cn.ikangjia.pomelo.api.controller;

import cn.ikangjia.pomelo.api.dto.sqlconsole.ExecuteDTO;
import cn.ikangjia.pomelo.api.model.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/15 20:43
 */
@Api(tags = "SQL 控制台操作")
@RestController
@RequestMapping("/sqlConsole")
public class SQLConsoleController {

    @ApiOperation("执行 SQL 接口")
    @PostMapping("/execute")
    public ResultVO<Void> executeSQL(@RequestBody ExecuteDTO executeDTO) {
        System.out.println("executeDTO = " + executeDTO);
        return null;
    }

}
