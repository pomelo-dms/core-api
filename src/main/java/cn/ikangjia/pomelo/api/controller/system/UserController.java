package cn.ikangjia.pomelo.api.controller.system;

import cn.ikangjia.pomelo.api.model.ResultVO;
import cn.ikangjia.pomelo.api.vo.UserVO;
import cn.ikangjia.pomelo.common.util.VerificationCode;
import cn.ikangjia.pomelo.domain.entity.UserDO;
import cn.ikangjia.pomelo.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/8 9:23
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResultVO<UserDO> getUser(@PathVariable Long id) {
        return Optional.ofNullable(userService.getUser(id))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @PostMapping("/doLogin")
    public ResultVO<UserVO> getUser(HttpServletRequest  request, @RequestBody UserDO userDO) {
//        String inputCode = userDO.getCode();
//        HttpSession session = request.getSession();
//        String originalCode = (String)session.getAttribute("code");
//        if (!StringUtils.hasText(originalCode)) {
//            throw new RuntimeException("Session 不支持");
//        }
//        if (!StringUtils.hasText(inputCode)) {
//            return ResultVO.error("请输入验证码");
//        }
//        if (!originalCode.equalsIgnoreCase(inputCode)) {
//            throw new RuntimeException("验证码错误");
//        }
        return Optional.ofNullable(userService.doLogin(userDO))
                .map(ResultVO::success)
                .orElseThrow();
    }

    @GetMapping("/code")
    public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession();
        session.setAttribute("code", text);
        VerificationCode.output(image,resp.getOutputStream());
    }
}
