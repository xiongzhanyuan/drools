package com.mrd.drools.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.mrd.drools.business.service.TdRuleColumnService;
import com.xzy.cm.mvc.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TdRuleColumnController extends BaseController{
    @Autowired
    @Qualifier(value = "service/column/operate")
    private TdRuleColumnService tdRuleColumnService;

    @RequestMapping(value = "/column/select_list")
    @ResponseBody
    public JSONObject selectList(@RequestBody JSONObject param) throws Exception{
        return tdRuleColumnService.selectList(param);
    }

    @RequestMapping(value = "/column/add_column")
    @ResponseBody
    public JSONObject addColumn(@RequestBody JSONObject param) throws Exception{
        return tdRuleColumnService.addColumn(param);
    }

    @RequestMapping(value = "/column/update_column")
    @ResponseBody
    public JSONObject updateColumn(@RequestBody JSONObject param) throws Exception{
        return tdRuleColumnService.updateColumn(param);
    }

    @RequestMapping(value = "/column/on_line")
    @ResponseBody
    public JSONObject onLine(@RequestBody JSONObject param) throws Exception{
        return tdRuleColumnService.onLine(param);
    }

    @RequestMapping(value = "/column/off_line")
    @ResponseBody
    public JSONObject offLine(@RequestBody JSONObject param) throws Exception{
        return tdRuleColumnService.offLine(param);
    }

    @RequestMapping(value = "/column/select_used_list")
    @ResponseBody
    public JSONObject selectUsedColumnList(@RequestBody JSONObject param) throws Exception{
        return tdRuleColumnService.selectUsedColumnList(param);
    }

    @RequestMapping(value = "/column/select_condition_list")
    @ResponseBody
    public JSONObject selectConditionList(@RequestBody JSONObject param) throws Exception{
        return tdRuleColumnService.selectConditionList(param);
    }


    @RequestMapping(value = "/column/select_result_list")
    @ResponseBody
    public JSONObject selectResultList(@RequestBody JSONObject param) throws Exception{
        return tdRuleColumnService.selectResultList(param);
    }



}
