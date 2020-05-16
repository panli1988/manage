package com.lp.framework.manage.utils;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = -6425166523271166687L;
    private String msg;

    private Boolean success;

    private List<T> list;

    private Long total;

    private Map<String,Object> map = new HashMap<String,Object>();

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public JsonResult() {
    }

    public JsonResult(PageInfo<T> pageInfo){
        if(null!=pageInfo){
            this.total = pageInfo.getTotal();
            this.list = pageInfo.getList();
            this.success = true;
        }

    }
}
