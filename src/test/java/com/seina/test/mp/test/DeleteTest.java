package com.seina.test.mp.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.seina.test.mp.dao.UserMapper;
import com.seina.test.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DeleteTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * id
     */
    @Test
    public void deleteById(){
        int row = userMapper.deleteById(4L);
        System.out.println("影响记录数：" + row);
    }

    @Test
    public void deleteBatchIds(){
        int row = userMapper.deleteBatchIds(Arrays.asList(1L, 2L));
        System.out.println("影响记录数：" + row);
    }

    @Test
    public void deleteByMap(){
        //Map 中的 key 表示表的列名，不是实体中的属性名
        Map<String, Object> column = new HashMap<>();
        column.put("name", "bob");
        column.put("age", 25);
        int row = userMapper.deleteByMap(column);
        System.out.println(row);
    }

    @Test
    public void deleteByLambda(){
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(User::getName, "sn");
        int row = userMapper.delete(lambdaQuery);
        System.out.println("影响记录数：" + row);
    }

    @Test
    public void updateByLambda(){
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(User::getName, "sn");
        int row = userMapper.delete(lambdaQuery);
        System.out.println("影响记录数：" + row);
    }
}
