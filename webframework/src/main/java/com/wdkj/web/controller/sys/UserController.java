package com.wdkj.web.controller.sys;

import com.wdkj.web.common.result.ResultUtils;
import com.wdkj.web.controller.common.BaseController;
import com.wdkj.web.model.sys.User;
import com.wdkj.web.service.sys.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author twj
 * @date 2018/9/19 15:07
 */
@RestController
public class UserController extends BaseController {

    @Resource
    UserService userService;

    @RequestMapping(value = "sys/user/list")
    public Map list(User user){
        return ResultUtils.succeed(userService.list(user));
    }
}
