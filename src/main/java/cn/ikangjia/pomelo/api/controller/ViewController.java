package cn.ikangjia.pomelo.api.controller;

import cn.ikangjia.pomelo.api.model.ResultVO;
import cn.ikangjia.pomelo.api.vo.TreeVO;
import cn.ikangjia.pomelo.service.ViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/27 9:25
 */
@RestController
@RequestMapping("/api/v1/view")
public class ViewController {

    private final ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @GetMapping("/tree")
    public ResultVO<List<TreeVO>> listTree2View(Long dataSourceId, String databaseName) {
        return Optional.ofNullable(viewService.listTree2View(dataSourceId, databaseName))
                .map(ResultVO::success)
                .orElseThrow();
    }
}
