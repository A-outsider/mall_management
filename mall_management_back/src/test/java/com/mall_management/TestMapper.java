package com.mall_management;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mall_management.dao.mapper.UserMapper;
import com.mall_management.dao.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestMapper {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testMapper() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.select(User.class, info -> !info.getColumn().equals("phone"));
        List<User> userList = userMapper.selectList(queryWrapper);

        for (User user : userList) {
            System.out.println(user);
        }


    }
}

