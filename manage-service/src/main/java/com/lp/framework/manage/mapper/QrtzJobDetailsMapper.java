package com.lp.framework.manage.mapper;

import com.lp.framework.manage.model.QrtzJobDetails;
import com.lp.framework.manage.model.QrtzJobDetailsKey;

import java.util.List;
import java.util.Map;

public interface QrtzJobDetailsMapper {

    QrtzJobDetails selectByPrimaryKey(QrtzJobDetailsKey key);

    List<QrtzJobDetails> selectByPage(Map<String,Object> params);
}