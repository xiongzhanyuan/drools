package com.mrd.drools.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mrd.drools.business.entity.TdRuleWithBLOBs;
import com.mrd.drools.business.fact.CheckResult;
import com.mrd.drools.business.service.RiskService;
import com.mrd.drools.business.service.TdRuleService;
import com.mrd.drools.utils.KieUtils;
import com.xzy.cm.common.exception.BussinessException;
import com.xzy.cm.common.exception.ErrorCodeEnum;
import com.xzy.cm.common.helper.JOHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(value = "service/risk/opera")
public class RiskServiceImpl implements RiskService {
    private static final Logger log = Logger.getLogger("SERVICE");

    @Autowired
    private TdRuleService tdRuleService;

    public JSONObject check(JSONObject param) throws Exception {

        if (ObjectUtils.isEmpty(param)) {
            throw new BussinessException(ErrorCodeEnum.PARAMETERFORMATERROR);
        }

        KieSession kieSession = KieUtils.getKieContainer().newKieSession();

        Map map = JOHelper.jo2class(param, Map.class);

        CheckResult checkResult = new CheckResult();
        List<String> list = new ArrayList<String>();
        checkResult.setResultList(list);
        kieSession.insert(map);
        kieSession.insert(checkResult);

        kieSession.getAgenda().getAgendaGroup(param.getString("group")).setFocus();


        int ruleFiredCount = kieSession.fireAllRules();
        System.out.println("触发了" + ruleFiredCount + "条规则");
        log.info("触发了" + ruleFiredCount + "条规则");

        kieSession.getEntryPoints();
        kieSession.dispose();

        return JOHelper.obj2Json(checkResult);
    }

    public JSONObject reload(JSONObject param) throws Exception {

        if (ObjectUtils.isEmpty(param)) {
            throw new BussinessException(ErrorCodeEnum.PARAMETERFORMATERROR);
        }

        //存库
        if (StringUtils.isBlank(param.getString("id"))) {
            tdRuleService.addRule(param);
        } else {
            tdRuleService.updateRule(param);
        }
        List<TdRuleWithBLOBs> list = tdRuleService.selectTdRuleList(0);
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        for (TdRuleWithBLOBs tdRule : list) {
            if (!ObjectUtils.isEmpty(tdRule) && StringUtils.isNotBlank(tdRule.getRule())) {
                kieFileSystem.write("src/main/resources/rules/" + tdRule.getName() +".drl", tdRule.getRule());
            }
        }

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new BussinessException("规则编写有误");
        }

        KieUtils.setKieContainer(kieServices.newKieContainer(KieServices.Factory.get().getRepository().getDefaultReleaseId()));

        JSONObject result = new JSONObject();
        result.put("data", "新规则重载成功");
        result.put("status", 1);
        return result;
    }

}
