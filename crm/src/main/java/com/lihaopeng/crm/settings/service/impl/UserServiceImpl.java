package com.lihaopeng.crm.settings.service.impl;

import com.lihaopeng.crm.settings.domain.User;
import com.lihaopeng.crm.settings.mapper.UserMapper;
import com.lihaopeng.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Connor
 * @date 2022/10/11 22:10
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    //一定要有数据访问层的对象
    @Autowired
    private UserMapper userMapper;

    // TODO: 007 被Controller层调用,则去调用UserMapper查询数据库中的数据 [sd用户登录7]
    @Override
    public User queryUserByLoginActAndPwd(Map<String, Object> map) {
        return userMapper.selectUserByLoginActAndPwd(map);
    }
}
