package com.mrd.drools.business.service;

import com.alibaba.fastjson.JSONObject;
import com.mrd.drools.business.entity.TdRule;
import com.mrd.drools.business.entity.TdRuleWithBLOBs;

import java.util.List;

public interface TdRuleService {
    List<TdRuleWithBLOBs> selectTdRuleList(Integer flagDelete) throws Exception;

    JSONObject selectList(JSONObject param) throws Exception;

    JSONObject addRule(JSONObject param) throws Exception;

    JSONObject updateRule(JSONObject param) throws Exception;

    JSONObject onLine(JSONObject param) throws Exception;

    JSONObject offLine(JSONObject param) throws Exception;

    JSONObject getRuleInfo(JSONObject param) throws Exception;

}
