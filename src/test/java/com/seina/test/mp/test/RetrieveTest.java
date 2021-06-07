package com.seina.test.mp.test;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.seina.test.mp.dao.UserMapper;
import com.seina.test.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RetrieveTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void selectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user.toString());
    }

    @Test
    public void selectBatchIds(){
        //批量 id 获取用户
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L));
        users.forEach(System.out::println);
    }

    @Test
    public void selectByMap(){
        //Map 中的 key 表示表的列名，不是实体中的属性名
        Map<String, Object> column = new HashMap<>();
        column.put("name", "seina");
        column.put("age", 3);
        //SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
        List<User> users = userMapper.selectByMap(column);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByWrapper(){
        //批量 id 获取用户
        //QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.gt("id", 1)
                .gt("age", 2)
                .like("name", "t")
                .between("id", 2L,3L)
                .isNotNull("email");
        queryWrapper.gt("id", 1)
                .or()
                .lt("age", 13)
                .orderByAsc("age");

        //apply 主要用于数据库函数动态入参
        String time = "2021-06-03";
        queryWrapper.apply("date_format(create_time, '%Y-%m-%d') = {0}", time);

        //inSql 用于子查询
        queryWrapper.inSql("id", "select id from user where name like ''tan%");

        //名字为 s 开头，并且年龄小于1 或者 年龄大于2
        queryWrapper.likeRight("name", "s")
                //Function 传入一个 T 返回一个 R
                .and(eq -> eq.lt("age", 1L).or().gt("age", 2L));

        queryWrapper.in("age", 2, 3);

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectPartField(){
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.select("id", "name").gt("age", 1);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectCondition(){
        QueryWrapper<User> queryWrapper = Wrappers.query();

        String name = "";
        String email = "";
        //condition 为 true 才会拼接条件
        queryWrapper.like(StringUtils.isNotEmpty(name), "name", name)
                .like(StringUtils.isNotEmpty(email), "email", email);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectWhereEntity(){
        //将实体对象作为查询条件
        User user = User.builder().age(3).name("seina").build();
        QueryWrapper<User> queryWrapper = Wrappers.query(user);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectLambda(){
        //LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper<User>().lambda();
        //LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        //从实体类中得到属性，再得到 column，防止写错
        lambdaQueryWrapper.eq(User::getAge, 3);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void selectChainLambda(){
        List<User> list = new LambdaQueryChainWrapper<User>(userMapper).gt(User::getAge, 2).list();
        list.forEach(System.out::println);
    }

    @Test
    public void selectPage(){
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.gt("age", 2);
        Page<User> page = new Page<>(1, 10);
        Page<User> pageNotTotal = new Page<>(1, 10, false);//false 表示不查总记录数量
        IPage<User> iPage = userMapper.selectPage(page, queryWrapper);
        System.out.println(iPage.getTotal());
        iPage.getRecords().forEach(System.out::println);
    }


































}
