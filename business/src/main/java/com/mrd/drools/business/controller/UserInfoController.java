package com.mrd.drools.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.mrd.drools.business.service.UserInfoService;
import com.xzy.cm.mvc.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserInfoController extends BaseController{
    @Autowired
    @Qualifier(value = "service/user/operate")
    private UserInfoService userInfoService;

    @RequestMapping(value = "/user/login")
    @ResponseBody
    public JSONObject login(@RequestBody JSONObject param) throws Exception{
        return userInfoService.login(param);
    }

    @RequestMapping(value = "/user/get_info")
    @ResponseBody
    public JSONObject getUserInfo(@RequestHeader(value="X-Token") String token) throws Exception{
        return userInfoService.getUserInfo(token);
    }

    @RequestMapping(value = "/user/select_list")
    @ResponseBody
    public JSONObject selectList(@RequestBody JSONObject param) throws Exception{
        return userInfoService.selectList(param);
    }


}
