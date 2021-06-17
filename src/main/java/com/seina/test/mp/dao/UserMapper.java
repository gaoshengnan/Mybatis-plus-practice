package com.seina.test.mp.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.seina.test.mp.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user ${ew.customSqlSegment}")
    List<User> selectAllByWrapper(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    @Select("select * from user")
    List<User> selectAll();

}
