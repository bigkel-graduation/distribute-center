package com.itchenyang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("t_quartz_job")
public class QuartzJobEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("task_id")
    private String taskId;

    @TableField("cron")
    private String cron;

    @TableField("class_name")
    private String className;

    @TableField("description")
    private String description;

    @TableField("status")
    private Integer status;
}
