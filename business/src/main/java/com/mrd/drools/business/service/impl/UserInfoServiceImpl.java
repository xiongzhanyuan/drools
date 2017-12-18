package com.mrd.drools.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mrd.drools.business.entity.UserInfo;
import com.mrd.drools.business.mapper.UserInfoMapper;
import com.mrd.drools.business.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xiongzhanyuan on 2017/2/21.
 */
@Transactional
@Service(value = "service/user/operate")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public JSONObject login(JSONObject param) throws Exception {

        String userName = param.getString("userName");
        String password = param.getString("password");

        UserInfo userInfo = userInfoMapper.getUserInfoByUserName(userName);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "ok");
        jsonObject.put("token", userInfo.getId());
        return jsonObject;
    }

    public JSONObject getUserInfo(String id) throws Exception {

        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(userInfo);

        jsonObject.remove("password");

        return jsonObject;
    }

    public JSONObject selectList(JSONObject param) throws Exception {

        JSONObject pageJson = param.getJSONObject("page");

        PageHelper.startPage(pageJson.getIntValue("pageNo"), pageJson.getIntValue("pageSize")); // 核心分页代码

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(param.getString("userName"));
        userInfo.setRealName(param.getString("realName"));

        Page<UserInfo> page =  userInfoMapper.selectList(userInfo);

        for (UserInfo user : page.getResult()) {
            user.setPassword("");
        }

        JSONObject result = new JSONObject();
        result.put("list", page.getResult());
        result.put("totalCount", page.getTotal());
        result.put("pageSize", page.getPageSize());
        return result;
    }

}
