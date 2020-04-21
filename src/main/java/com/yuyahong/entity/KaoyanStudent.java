package com.yuyahong.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 考研学生
 *
 * @author yuyahong
 * @date 2020/4/21 0021 17:50
 */  
@TableName(value = "kaoyan_student")
public class KaoyanStudent extends BaseStudent implements Serializable {
    /**
     * 所在班级
     */
    @TableField(value = "clazz_name")
    private String clazzName;

    /**
     * 考研学校
     */
    @TableField(value = "kaoyan_school")
    private String kaoyanSchool;

    /**
     * 考研学校地址
     */
    @TableField(value = "school_address")
    private String schoolAddress;

    /**
     * 获取所在班级
     *
     * @return clazz_name - 所在班级
     */
    public String getClazzName() {
        return clazzName;
    }

    /**
     * 设置所在班级
     *
     * @param clazzName 所在班级
     */
    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    /**
     * 获取考研学校
     *
     * @return kaoyan_school - 考研学校
     */
    public String getKaoyanSchool() {
        return kaoyanSchool;
    }

    /**
     * 设置考研学校
     *
     * @param kaoyanSchool 考研学校
     */
    public void setKaoyanSchool(String kaoyanSchool) {
        this.kaoyanSchool = kaoyanSchool;
    }

    /**
     * 获取考研学校地址
     *
     * @return school_address - 考研学校地址
     */
    public String getSchoolAddress() {
        return schoolAddress;
    }

    /**
     * 设置考研学校地址
     *
     * @param schoolAddress 考研学校地址
     */
    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }
}