package com.mrd.drools.business.fact;

import java.util.List;

public class CheckResult {

    private boolean postCodeResult = false; // true:通过校验；false：未通过校验

    private String resultStr;

    private List<String> resultList;

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String resultStr) {
        this.resultStr = resultStr;
    }

    public boolean isPostCodeResult() {
        return postCodeResult;
    }

    public void setPostCodeResult(boolean postCodeResult) {
        this.postCodeResult = postCodeResult;
    }

    public List<String> getResultList() {
        return resultList;
    }

    public void setResultList(List<String> resultList) {
        this.resultList = resultList;
    }
}
