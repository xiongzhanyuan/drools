package com.mrd.drools.business.service;

import com.alibaba.fastjson.JSONObject;

public interface RiskService {


    JSONObject check(JSONObject param) throws Exception;

    JSONObject reload(JSONObject param) throws Exception;

}
