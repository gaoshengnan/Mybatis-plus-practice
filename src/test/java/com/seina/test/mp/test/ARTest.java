package com.seina.test.mp.test;

import com.seina.test.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ARTest {

    @Test
    public void insertAR(){
        User haha = User.builder().name("haha").age(39).email("haha@test.com").createTime(LocalDateTime.now()).build();
        boolean result = haha.insert();
        System.out.println(result);
    }


    @Test
    public void selectAR(){
        User haha = User.builder().id(4L).build();
        User user = haha.selectById(haha);
        System.out.println(user);
    }

    @Test
    public void updateAR(){
        User haha = User.builder().id(4L).name("haha111").build();
        boolean result = haha.updateById();
        System.out.println(result);
    }

    @Test
    public void updateOrUpdateAR(){
        User haha = User.builder().id(5L).name("haha").age(40).email("haha@test.com").createTime(LocalDateTime.now()).build();
        //带 id 的话，就会先查询，有就 update，没有就 insert
        //不带 id 的话，就会一直 insert
        boolean result = haha.insertOrUpdate();
        System.out.println(result);
    }



}
