package com.mrd.drools.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.mrd.drools.business.fact.CheckResult;
import com.mrd.drools.business.service.RiskService;
import com.mrd.drools.utils.KieUtils;
import com.xzy.cm.common.helper.JOHelper;
import com.xzy.cm.mvc.controller.BaseController;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class RiskRuleController extends BaseController {

    @Autowired
    @Qualifier(value = "service/risk/opera")
    private RiskService riskService;


    @ResponseBody
    @RequestMapping("/risk/check")
    public JSONObject check(@RequestBody JSONObject param) throws Exception {

        return riskService.check(param);
    }

    @ResponseBody
    @RequestMapping("/risk/reload")
    public JSONObject reload(@RequestBody JSONObject param) throws Exception {

        return riskService.reload(param);
    }
}
