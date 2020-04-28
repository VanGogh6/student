package com.yuyahong.utils;

import com.yuyahong.entity.WorkStudent;
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
        row0.createCell(0).setCellValue("学号");
        row0.createCell(1).setCellValue("姓名");
        row0.createCell(3).setCellValue("性别");
        row0.createCell(3).setCellValue("学院");
        row0.createCell(4).setCellValue("专业");
        row0.createCell(5).setCellValue("班级");
        row0.createCell(6).setCellValue("电话");
        row0.createCell(7).setCellValue("公司");
        for (int i = 0; i < lists.size(); i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            WorkStudent student = lists.get(i);
            if (student != null) {
                row1.createCell(0).setCellValue(student.getNo());
                row1.createCell(1).setCellValue(student.getName());
                row1.createCell(2).setCellValue(student.getSex());
                row1.createCell(3).setCellValue(student.getCollegeName());
                row1.createCell(4).setCellValue(student.getMajorName());
                row1.createCell(5).setCellValue(student.getClassName());
                row1.createCell(6).setCellValue(student.getPhone());
                row1.createCell(7).setCellValue(student.getWorkCompany());
            }
        }
        return wb;
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
}
