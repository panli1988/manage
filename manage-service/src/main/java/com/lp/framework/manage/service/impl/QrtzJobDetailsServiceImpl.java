package com.lp.framework.manage.service.impl;

import com.lp.framework.manage.mapper.QrtzJobDetailsMapper;
import com.lp.framework.manage.model.QrtzJobDetails;
import com.lp.framework.manage.model.QrtzJobDetailsKey;
import com.lp.framework.manage.service.QrtzJobDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QrtzJobDetailsServiceImpl implements QrtzJobDetailsService {

    @Autowired
    private QrtzJobDetailsMapper qrtzJobDetailsMapper;

    @Override
    public QrtzJobDetails selectByPrimaryKey(QrtzJobDetailsKey key) {
        return qrtzJobDetailsMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<QrtzJobDetails> selectByPage(Map<String, Object> params) {
        return qrtzJobDetailsMapper.selectByPage(params);
    }
}
