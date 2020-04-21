package com.yuyahong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author yuyahong
 * @date 2020/4/21 0021 18:24
 */  
@TableName(value = "country_student")
public class CountryStudent extends BaseStudent implements Serializable {
    /**
     * 公务员工作地址
     */
    @TableField(value = "work_address")
    private String workAddress;

    /**
     * 公务员岗位
     */
    @TableField(value = "work_post")
    private String workPost;

    /**
     * 获取公务员工作地址
     *
     * @return work_address - 公务员工作地址
     */
    public String getWorkAddress() {
        return workAddress;
    }

    /**
     * 设置公务员工作地址
     *
     * @param workAddress 公务员工作地址
     */
    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    /**
     * 获取公务员岗位
     *
     * @return work_post - 公务员岗位
     */
    public String getWorkPost() {
        return workPost;
    }

    /**
     * 设置公务员岗位
     *
     * @param workPost 公务员岗位
     */
    public void setWorkPost(String workPost) {
        this.workPost = workPost;
    }
}