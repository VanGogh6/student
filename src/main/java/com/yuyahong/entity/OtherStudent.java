package com.yuyahong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 其他学生
 * @author wufan
 * @date 2020/4/21 0021 17:50
 */  
@TableName(value = "other_student")
public class OtherStudent extends BaseStudent implements Serializable {
    /**
     * 接下来打算
     */
    @TableField(value = "plan")
    private String plan;

    /**
     * 所致原因
     */
    @TableField(value = "reason")
    private String reason;

    /**
     * 现在居住地址
     */
    @TableField(value = "now_address")
    private String nowAddress;

    /**
     * 获取接下来打算
     *
     * @return plan - 接下来打算
     */
    public String getPlan() {
        return plan;
    }

    /**
     * 设置接下来打算
     *
     * @param plan 接下来打算
     */
    public void setPlan(String plan) {
        this.plan = plan;
    }

    /**
     * 获取所致原因
     *
     * @return reason - 所致原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置所致原因
     *
     * @param reason 所致原因
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 获取现在居住地址
     *
     * @return now_address - 现在居住地址
     */
    public String getNowAddress() {
        return nowAddress;
    }

    /**
     * 设置现在居住地址
     *
     * @param nowAddress 现在居住地址
     */
    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress;
    }
}