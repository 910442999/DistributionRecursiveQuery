package com.yc.distribution.service.impl;
import com.yc.distribution.base.dao.BaseDao;
import com.yc.distribution.base.service.impl.BaseServiceImpl;
import com.yc.distribution.dao.UserMapper;
import com.yc.distribution.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.yc.distribution.model.User;

/**
 *  UserServiceImpl
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected BaseDao getBaseDao() {
        return this.userMapper;
    }
}
