package com.mrd.drools.business.service;

import com.alibaba.fastjson.JSONObject;

public interface TdRuleColumnService {

    JSONObject selectList(JSONObject param) throws Exception;

    JSONObject addColumn(JSONObject param) throws Exception;

    JSONObject updateColumn(JSONObject param) throws Exception;

    JSONObject onLine(JSONObject param) throws Exception;

    JSONObject offLine(JSONObject param) throws Exception;

    JSONObject selectUsedColumnList(JSONObject param) throws Exception;

    JSONObject selectConditionList(JSONObject param) throws Exception;

    JSONObject selectResultList(JSONObject param) throws Exception;

}
