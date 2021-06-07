package com.seina.test.mp.test;


import com.seina.test.mp.dao.UserMapper;
import com.seina.test.mp.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SimpleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void select(){
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(2, users.size());
        users.forEach(System.out::println);
    }

    @Test
    public void inset(){
        User bob = User.builder().id(3L).age(3).name("bob").email("bob@test.com").build();
        int rows = userMapper.insert(bob);
        Assert.assertEquals(1, rows);
    }

}
