package com.mrd.drools.business.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by xiongzhanyuan on 2017/9/6.
 */
public interface UserInfoService {

    JSONObject login(JSONObject param) throws Exception;

    JSONObject getUserInfo(String id) throws Exception;

    JSONObject selectList(JSONObject param) throws Exception;

}
