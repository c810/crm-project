package com.lihaopeng.crm.workbench.service.impl;

import com.lihaopeng.crm.workbench.domain.Activity;
import com.lihaopeng.crm.workbench.mapper.ActivityMapper;
import com.lihaopeng.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Connor
 * @date 2022/10/13 15:31
 */

@Service("activityService") // 以首字母小写的接口名命名,同时,要让spring扫描这个包
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }
}
