package com.mrd.drools.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mrd.drools.business.entity.TdRuleColumn;
import com.mrd.drools.business.mapper.TdRuleColumnMapper;
import com.mrd.drools.business.service.TdRuleColumnService;
import com.xzy.cm.common.helper.JOHelper;
import com.xzy.cm.common.helper.UUIDHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by xiongzhanyuan on 2017/2/21.
 */
@Transactional
@Service(value = "service/column/operate")
public class TdRuleColumnServiceImpl implements TdRuleColumnService {

    @Autowired
    private TdRuleColumnMapper tdRuleColumnMapper;


    public JSONObject selectList(JSONObject param) throws Exception {

        JSONObject pageJson = param.getJSONObject("page");

        PageHelper.startPage(pageJson.getIntValue("pageNo"), pageJson.getIntValue("pageSize")); // 核心分页代码

        TdRuleColumn tdRuleColumn = new TdRuleColumn();
        tdRuleColumn.setName(param.getString("name"));

        Page<TdRuleColumn> page =  tdRuleColumnMapper.selectList(tdRuleColumn);

        JSONObject result = new JSONObject();
        result.put("list", page.getResult());
        result.put("totalCount", page.getTotal());
        result.put("pageSize", page.getPageSize());
        return result;
    }

    public JSONObject addColumn(JSONObject param) throws Exception {
        TdRuleColumn tdRuleColumn = JOHelper.jo2class(param, TdRuleColumn.class);
        tdRuleColumn.setId(UUIDHelper.getUUID());
        tdRuleColumn.setFlagDelete(0);
        tdRuleColumn.setCreateTime(new Date());
        tdRuleColumn.setModifyTime(new Date());

        tdRuleColumnMapper.insert(tdRuleColumn);

        JSONObject result = new JSONObject();
        result.put("data", "添加成功");
        result.put("status", 1);
        return result;
    }

    public JSONObject updateColumn(JSONObject param) throws Exception {
        TdRuleColumn tdRuleColumn = JOHelper.jo2class(param, TdRuleColumn.class);
        tdRuleColumn.setModifyTime(new Date());
        tdRuleColumnMapper.updateByPrimaryKey(tdRuleColumn);
        JSONObject result = new JSONObject();
        result.put("data", "修改成功");
        result.put("status", 1);
        return result;
    }

    public JSONObject onLine(JSONObject param) throws Exception {
        TdRuleColumn tdRuleColumn = JOHelper.jo2class(param, TdRuleColumn.class);
        tdRuleColumn.setModifyTime(new Date());
        tdRuleColumnMapper.updateByPrimaryKeySelective(tdRuleColumn);
        JSONObject result = new JSONObject();
        result.put("data", "修改成功");
        result.put("status", 1);
        return result;
    }

    public JSONObject offLine(JSONObject param) throws Exception {
        TdRuleColumn tdRuleColumn = JOHelper.jo2class(param, TdRuleColumn.class);
        tdRuleColumn.setModifyTime(new Date());
        tdRuleColumnMapper.updateByPrimaryKeySelective(tdRuleColumn);
        JSONObject result = new JSONObject();
        result.put("data", "修改成功");
        result.put("status", 1);
        return result;
    }

    public JSONObject selectUsedColumnList(JSONObject param) throws Exception {

        PageHelper.startPage(1, 99999); // 核心分页代码

        TdRuleColumn tdRuleColumn = new TdRuleColumn();
        tdRuleColumn.setFlagDelete(0);
        tdRuleColumn.setType(param.getInteger("type"));

        Page<TdRuleColumn> page =  tdRuleColumnMapper.selectList(tdRuleColumn);

        JSONObject result = new JSONObject();
        result.put("list", page.getResult());
        result.put("totalCount", page.getTotal());
        result.put("pageSize", page.getPageSize());
        return result;
    }

    public JSONObject selectConditionList(JSONObject param) throws Exception {
        PageHelper.startPage(1, 99999); // 核心分页代码

        TdRuleColumn tdRuleColumn = new TdRuleColumn();
        tdRuleColumn.setFlagDelete(0);
        tdRuleColumn.setType(1);

        Page<TdRuleColumn> page =  tdRuleColumnMapper.selectList(tdRuleColumn);

        JSONObject result = new JSONObject();
        result.put("list", page.getResult());
        result.put("totalCount", page.getTotal());
        result.put("pageSize", page.getPageSize());
        return result;
    }

    public JSONObject selectResultList(JSONObject param) throws Exception {
        PageHelper.startPage(1, 99999); // 核心分页代码

        TdRuleColumn tdRuleColumn = new TdRuleColumn();
        tdRuleColumn.setFlagDelete(0);
        tdRuleColumn.setType(2);

        Page<TdRuleColumn> page =  tdRuleColumnMapper.selectList(tdRuleColumn);

        JSONObject result = new JSONObject();
        result.put("list", page.getResult());
        result.put("totalCount", page.getTotal());
        result.put("pageSize", page.getPageSize());
        return result;
    }

}
