package com.mrd.drools.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.mrd.drools.business.service.TdRuleColumnService;
import com.mrd.drools.business.service.TdRuleService;
import com.xzy.cm.mvc.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TdRuleController extends BaseController{
    @Autowired
    @Qualifier(value = "service/rules/opera")
    private TdRuleService tdRuleService;

    @RequestMapping(value = "/rules/select_list")
    @ResponseBody
    public JSONObject selectList(@RequestBody JSONObject param) throws Exception{
        return tdRuleService.selectList(param);
    }

    @RequestMapping(value = "/rules/add_rule")
    @ResponseBody
    public JSONObject addColumn(@RequestBody JSONObject param) throws Exception{
        return tdRuleService.addRule(param);
    }

    @RequestMapping(value = "/rules/update_rule")
    @ResponseBody
    public JSONObject updateColumn(@RequestBody JSONObject param) throws Exception{
        return tdRuleService.updateRule(param);
    }

    @RequestMapping(value = "/rules/on_line")
    @ResponseBody
    public JSONObject onLine(@RequestBody JSONObject param) throws Exception{
        return tdRuleService.onLine(param);
    }

    @RequestMapping(value = "/rules/off_line")
    @ResponseBody
    public JSONObject offLine(@RequestBody JSONObject param) throws Exception{
        return tdRuleService.offLine(param);
    }

    @RequestMapping(value = "/rules/get_rule_info")
    @ResponseBody
    public JSONObject getRuleInfo(@RequestBody JSONObject param) throws Exception{
        return tdRuleService.getRuleInfo(param);
    }
}
