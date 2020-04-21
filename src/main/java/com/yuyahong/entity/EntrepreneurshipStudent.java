package com.yuyahong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 创业学生
 * @author wufan
 * @date 2020/4/21 0021 17:50
 */  
@TableName(value = "entrepreneurship_student")
public class EntrepreneurshipStudent extends BaseStudent implements Serializable {
    /**
     * 创业的公司名称
     */
    @TableField(value = "entrepreneurshipe_company")
    private String entrepreneurshipeCompany;

    /**
     * 创业的公司地址
     */
    @TableField(value = "entrepreneurshipe_address")
    private String entrepreneurshipeAddress;

    /**
     * 获取创业的公司名称
     *
     * @return entrepreneurshipe_company - 创业的公司名称
     */
    public String getEntrepreneurshipeCompany() {
        return entrepreneurshipeCompany;
    }

    /**
     * 设置创业的公司名称
     *
     * @param entrepreneurshipeCompany 创业的公司名称
     */
    public void setEntrepreneurshipeCompany(String entrepreneurshipeCompany) {
        this.entrepreneurshipeCompany = entrepreneurshipeCompany;
    }

    /**
     * 获取创业的公司地址
     *
     * @return entrepreneurshipe_address - 创业的公司地址
     */
    public String getEntrepreneurshipeAddress() {
        return entrepreneurshipeAddress;
    }

    /**
     * 设置创业的公司地址
     *
     * @param entrepreneurshipeAddress 创业的公司地址
     */
    public void setEntrepreneurshipeAddress(String entrepreneurshipeAddress) {
        this.entrepreneurshipeAddress = entrepreneurshipeAddress;
    }
}