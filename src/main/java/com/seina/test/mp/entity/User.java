package com.seina.test.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
public class User {

    @TableId("id")
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableField(exist = false)
    private Integer level;

    @TableField("create_Time")
    private LocalDateTime createTime;
}
