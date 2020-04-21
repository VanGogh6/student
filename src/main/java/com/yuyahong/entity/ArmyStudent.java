package com.yuyahong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 参军学生
 * @author wufan
 * @date 2020/4/21 0021 17:50
 */  
@TableName(value = "army_student")
public class ArmyStudent extends BaseStudent implements Serializable {
    /**
     * 参军开始时间
     */
    @TableField(value = "army_begin_time")
    private String armyBeginTime;

    /**
     * 参军时长
     */
    @TableField(value = "army_duration")
    private String armyDuration;

    /**
     * 获取参军开始时间
     *
     * @return army_begin_time - 参军开始时间
     */
    public String getArmyBeginTime() {
        return armyBeginTime;
    }

    /**
     * 设置参军开始时间
     *
     * @param armyBeginTime 参军开始时间
     */
    public void setArmyBeginTime(String armyBeginTime) {
        this.armyBeginTime = armyBeginTime;
    }

    /**
     * 获取参军时长
     *
     * @return army_duration - 参军时长
     */
    public String getArmyDuration() {
        return armyDuration;
    }

    /**
     * 设置参军时长
     *
     * @param armyDuration 参军时长
     */
    public void setArmyDuration(String armyDuration) {
        this.armyDuration = armyDuration;
    }
}