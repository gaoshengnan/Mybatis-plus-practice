package com.seina.test.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@TableName("user")
@AllArgsConstructor
@NoArgsConstructor
public class User extends Model<User> {

    @TableId("id")
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableField("create_Time")
    private LocalDateTime createTime;

//    @TableLogic
//    private Integer deleted;

    @TableField(exist = false)
    private Integer level;

}
