package com.lihaopeng.crm.settings.service;

import com.lihaopeng.crm.settings.domain.User;

import java.util.Map;

/**
 * @author Connor
 * @date 2022/10/11 22:07
 */
public interface UserService {
    User queryUserByLoginActAndPwd(Map<String,Object> map);
}
