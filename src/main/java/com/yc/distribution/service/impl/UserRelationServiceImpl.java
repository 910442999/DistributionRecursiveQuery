package com.yc.distribution.service.impl;
import com.yc.distribution.base.dao.BaseDao;
import com.yc.distribution.base.service.impl.BaseServiceImpl;
import com.yc.distribution.dao.UserRelationMapper;
import com.yc.distribution.service.UserRelationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.yc.distribution.model.UserRelation;

/**
 *  UserRelationServiceImpl
 */
@Service("userRelationService")
public class UserRelationServiceImpl extends BaseServiceImpl<UserRelation> implements UserRelationService {

    @Autowired
    private UserRelationMapper userRelationMapper;

    @Override
    protected BaseDao getBaseDao() {
        return this.userRelationMapper;
    }
}
