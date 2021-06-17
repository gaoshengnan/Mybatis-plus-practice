package com.seina.test.mp.test;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.seina.test.mp.dao.UserMapper;
import com.seina.test.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UpdateTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * id
     */
    @Test
    public void updateById(){
        User sn111 = User.builder().id(1L).name("sn").build();
        int row = userMapper.updateById(sn111);
        System.out.println("影响记录数：" + row);
    }

    /**
     * 条件构造器
     */
    @Test
    public void updateByWrapper(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "sn");
        User user = User.builder().age(18).build();
        int row = userMapper.update(user, updateWrapper);
        System.out.println("影响记录数：" + row);
    }

    @Test
    public void updateByWrapperWhere(){
        User whereSn = User.builder().name("sn").build();
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>(whereSn);
        User user = User.builder().age(99).build();
        int row = userMapper.update(user, updateWrapper);
        System.out.println("影响记录数：" + row);
    }

    @Test
    public void updateByWrapperSet(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "sn").set("email", "sn@test.com");
        int row = userMapper.update(null, updateWrapper);
        System.out.println("影响记录数：" + row);
    }

    /**
     * lambda
     */
    @Test
    public void updateByLambda(){
        LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.lambdaUpdate();
        lambdaUpdate.eq(User::getName, "sn").set(User::getEmail, "sn@test.com");
        int row = userMapper.update(null, lambdaUpdate);
        System.out.println("影响记录数：" + row);
    }

    @Test
    public void updateByLambdaChain(){
        boolean result = new LambdaUpdateChainWrapper<>(userMapper)
                .eq(User::getName, "sn")
                .set(User::getEmail, "sn@test.com")
                .update();
        System.out.println("update 结果：" + result);
    }
}
