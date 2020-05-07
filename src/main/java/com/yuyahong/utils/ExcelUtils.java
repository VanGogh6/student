package com.yuyahong.utils;

import com.yuyahong.entity.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


/**
 * @author wufan
 * @date 2020/4/16 0016 22:21
 */
public class ExcelUtils {

    /**
     * 导出方法
     */
    public static XSSFWorkbook getWorkStudentXSSFWorkbook(List<WorkStudent> lists) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        //序号  学号  姓名  手机号 课程设计名称  学生所在班级
        setRow0(row0, "公司");
        for (int i = 0; i < lists.size(); i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            WorkStudent student = lists.get(i);
            if (student != null) {
                setContent(row1, student.getNo(), student.getName(), student.getSex(), student.getCollegeName(), student.getMajorName(), student.getClassName(), student.getPhone());
                row1.createCell(7).setCellValue(student.getWorkCompany());
            }
        }
        return wb;
    }


    public static XSSFWorkbook getWorkKaoyanStudentXSSFWorkbook(List<KaoyanStudent> lists) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        //序号  学号  姓名  手机号 课程设计名称  学生所在班级
        setRow0(row0, "考研学校");
        for (int i = 0; i < lists.size(); i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            KaoyanStudent student = lists.get(i);
            if (student != null) {
                setContent(row1, student.getNo(), student.getName(), student.getSex(), student.getCollegeName(), student.getMajorName(), student.getClassName(), student.getPhone());
                row1.createCell(7).setCellValue(student.getKaoyanSchool());
            }
        }
        return wb;
    }

    private static void setRow0(XSSFRow row0, String... str) {
        row0.createCell(0).setCellValue("学号");
        row0.createCell(1).setCellValue("姓名");
        row0.createCell(2).setCellValue("性别");
        row0.createCell(3).setCellValue("学院");
        row0.createCell(4).setCellValue("专业");
        row0.createCell(5).setCellValue("班级");
        row0.createCell(6).setCellValue("电话");
        for (int i = 0; i < str.length; i++) {
            row0.createCell(i + 7).setCellValue(str[i]);
        }

    }

    private static void setContent(XSSFRow row1, String no, String name, String sex, String collegeName, String majorName, String className, String phone) {
        row1.createCell(0).setCellValue(no);
        row1.createCell(1).setCellValue(name);
        row1.createCell(2).setCellValue(sex);
        row1.createCell(3).setCellValue(collegeName);
        row1.createCell(4).setCellValue(majorName);
        row1.createCell(5).setCellValue(className);
        row1.createCell(6).setCellValue(phone);
    }


    /**
     * 获取工作簿
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook getWB(MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
        Workbook wb = null;
        String originalFilename = file.getOriginalFilename();
        if (originalFilename.endsWith("xlsx")) {//07
            wb = new XSSFWorkbook(in);
        }
        if (originalFilename.endsWith("xls")) {//03
            wb = new HSSFWorkbook(in);
        }
        return wb;
    }

    public static XSSFWorkbook getArmyStudentXSSFWorkbook(List<ArmyStudent> lists) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        //序号  学号  姓名  手机号 课程设计名称  学生所在班级
        setRow0(row0, "参军开始时间", "参军结束时间");
        for (int i = 0; i < lists.size(); i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            ArmyStudent student = lists.get(i);
            if (student != null) {
                setContent(row1, student.getNo(), student.getName(), student.getSex(), student.getCollegeName(), student.getMajorName(), student.getClassName(), student.getPhone());
                row1.createCell(7).setCellValue(student.getArmyBeginTime());
                row1.createCell(8).setCellValue(student.getArmyDuration());
            }
        }
        return wb;
    }

    public static XSSFWorkbook getCountryStudentXSSFWorkbook(List<CountryStudent> lists) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        //序号  学号  姓名  手机号 课程设计名称  学生所在班级
        setRow0(row0, "工作地址", "工作岗位");
        for (int i = 0; i < lists.size(); i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            CountryStudent student = lists.get(i);
            if (student != null) {
                setContent(row1, student.getNo(), student.getName(), student.getSex(), student.getCollegeName(), student.getMajorName(), student.getClassName(), student.getPhone());
                row1.createCell(7).setCellValue(student.getWorkAddress());
                row1.createCell(8).setCellValue(student.getWorkPost());
            }
        }
        return wb;
    }

    public static XSSFWorkbook getEntrepreneurshipStudentXSSFWorkbook(List<EntrepreneurshipStudent> lists) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        //序号  学号  姓名  手机号 课程设计名称  学生所在班级
        setRow0(row0, "创业地址", "公司名称");
        for (int i = 0; i < lists.size(); i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            EntrepreneurshipStudent student = lists.get(i);
            if (student != null) {
                setContent(row1, student.getNo(), student.getName(), student.getSex(), student.getCollegeName(), student.getMajorName(), student.getClassName(), student.getPhone());
                row1.createCell(7).setCellValue(student.getEntrepreneurshipeAddress());
                row1.createCell(8).setCellValue(student.getEntrepreneurshipeCompany());
            }
        }
        return wb;
    }

    public static XSSFWorkbook getOtherStudentXSSFWorkbook(List<OtherStudent> lists) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        //序号  学号  姓名  手机号 课程设计名称  学生所在班级
        setRow0(row0, "接下来打算", "所致原因", "现在居住地址");
        for (int i = 0; i < lists.size(); i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            OtherStudent student = lists.get(i);
            if (student != null) {
                setContent(row1, student.getNo(), student.getName(), student.getSex(), student.getCollegeName(), student.getMajorName(), student.getClassName(), student.getPhone());
                row1.createCell(7).setCellValue(student.getPlan());
                row1.createCell(8).setCellValue(student.getReason());
                row1.createCell(9).setCellValue(student.getNowAddress());
            }
        }
        return wb;
    }
}
