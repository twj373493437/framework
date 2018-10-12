package com.wdkj.ftest.controller.sys;

import com.wdkj.web.common.result.ResultUtils;
import com.wdkj.web.model.sys.User;
import com.wdkj.web.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author twj
 * @date 2018/9/19 15:05
 */
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    /**
     *
     * @param username
     * @param password
     * @param verCode 验证码
     * @return
     */
    @RequestMapping("sys/login/login")
    public Map login(String username, String password, String verCode){

        User user = new User();
        user = userService.getOne(user);

        return ResultUtils.succeed("ok", user);
    }
}
