package com.yuyahong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 找到工作学生
 * @author yuyahong
 * @date 2020/4/21 0021 17:50
 */  
@TableName(value = "work_student")
public class WorkStudent extends BaseStudent implements Serializable {
    /**
     * 公司名称
     */
    @TableField(value = "work_company")
    private String workCompany;

    /**
     * 公司地址
     */
    @TableField(value = "work_address")
    private String workAddress;

    /**
     * 获取公司名称
     *
     * @return work_company - 公司名称
     */
    public String getWorkCompany() {
        return workCompany;
    }

    /**
     * 设置公司名称
     *
     * @param workCompany 公司名称
     */
    public void setWorkCompany(String workCompany) {
        this.workCompany = workCompany;
    }

    /**
     * 获取公司地址
     *
     * @return work_address - 公司地址
     */
    public String getWorkAddress() {
        return workAddress;
    }

    /**
     * 设置公司地址
     *
     * @param workAddress 公司地址
     */
    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }
}