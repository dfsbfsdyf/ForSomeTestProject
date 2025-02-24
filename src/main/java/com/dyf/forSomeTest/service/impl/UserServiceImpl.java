package com.dyf.forSomeTest.service.impl;

import com.dyf.forSomeTest.entity.User;
import com.dyf.forSomeTest.mapper.UserMapper;
import com.dyf.forSomeTest.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.junit.Test;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author dyf
 * @since 2025-02-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}
