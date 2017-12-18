package com.mrd.drools.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mrd.drools.business.entity.TdRule;
import com.mrd.drools.business.entity.TdRuleColumn;
import com.mrd.drools.business.entity.TdRuleWithBLOBs;
import com.mrd.drools.business.mapper.TdRuleMapper;
import com.mrd.drools.business.service.TdRuleService;
import com.xzy.cm.common.helper.JOHelper;
import com.xzy.cm.common.helper.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "service/rules/opera")
public class TdRuleServiceImpl implements TdRuleService{

    @Autowired
    private TdRuleMapper tdRuleMapper;

    public List<TdRuleWithBLOBs> selectTdRuleList(Integer flagDelete) throws Exception {
        return tdRuleMapper.selectTdRuleList(flagDelete);
    }

    public JSONObject selectList(JSONObject param) throws Exception {
        JSONObject pageJson = param.getJSONObject("page");

        PageHelper.startPage(pageJson.getIntValue("pageNo"), pageJson.getIntValue("pageSize")); // 核心分页代码

        TdRuleWithBLOBs tdRule = new TdRuleWithBLOBs();
        tdRule.setName(param.getString("name"));

        Page<TdRuleWithBLOBs> page =  tdRuleMapper.selectList(tdRule);

        JSONObject result = new JSONObject();
        result.put("list", page.getResult());
        result.put("totalCount", page.getTotal());
        result.put("pageSize", page.getPageSize());
        return result;
    }

    public JSONObject addRule(JSONObject param) throws Exception {
        TdRuleWithBLOBs tdRule = JOHelper.jo2class(param, TdRuleWithBLOBs.class);
        tdRule.setId(UUIDHelper.getUUID());
        tdRule.setFlagDelete(0);
        tdRule.setCreateTime(new Date());
        tdRule.setModifyTime(new Date());

        tdRuleMapper.insert(tdRule);

        JSONObject result = new JSONObject();
        result.put("data", "添加成功");
        result.put("status", 1);
        return result;
    }

    public JSONObject updateRule(JSONObject param) throws Exception {
        TdRuleWithBLOBs tdRule = JOHelper.jo2class(param, TdRuleWithBLOBs.class);
        tdRule.setModifyTime(new Date());
        tdRuleMapper.updateByPrimaryKeyWithBLOBs(tdRule);
        JSONObject result = new JSONObject();
        result.put("data", "修改成功");
        result.put("status", 1);
        return result;
    }

    public JSONObject onLine(JSONObject param) throws Exception {
        TdRuleWithBLOBs tdRule = JOHelper.jo2class(param, TdRuleWithBLOBs.class);
        tdRule.setModifyTime(new Date());
        tdRule.setFlagDelete(0);
        tdRuleMapper.updateByPrimaryKeySelective(tdRule);
        JSONObject result = new JSONObject();
        result.put("data", "修改成功");
        result.put("status", 1);
        return result;
    }

    public JSONObject offLine(JSONObject param) throws Exception {
        TdRuleWithBLOBs tdRule = JOHelper.jo2class(param, TdRuleWithBLOBs.class);
        tdRule.setModifyTime(new Date());
        tdRule.setFlagDelete(1);
        tdRuleMapper.updateByPrimaryKeySelective(tdRule);
        JSONObject result = new JSONObject();
        result.put("data", "修改成功");
        result.put("status", 1);
        return result;
    }

    public JSONObject getRuleInfo(JSONObject param) throws Exception {

        TdRuleWithBLOBs tdRuleWithBLOBs = tdRuleMapper.selectByPrimaryKey(param.getString("id"));
        JSONObject jsonObject = JOHelper.obj2Json(tdRuleWithBLOBs);
        jsonObject.put("contentJson", JSONObject.parse(tdRuleWithBLOBs.getContent()));

        return jsonObject;
    }
}
