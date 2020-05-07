package com.yuyahong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyahong.entity.OtherStudent;
import com.yuyahong.mapper.OtherStudentMapper;
import com.yuyahong.service.OtherStudentService;
import com.yuyahong.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyahong
 * @date 2020/4/21 0021 17:50
 */
@Service
public class OtherStudentServiceImpl extends ServiceImpl<OtherStudentMapper, OtherStudent> implements OtherStudentService {

    @Override
    public IPage<OtherStudent> getOtherStudentPage(Page<OtherStudent> page, OtherStudent otherStudent) {
        LambdaQueryWrapper<OtherStudent> queryWrapper = new LambdaQueryWrapper<>();
        String no = otherStudent.getNo();
        if (no == null) {
            no = "";
        }
        String name = otherStudent.getName();
        if (name == null) {
            name = "";
        }
        String majorName = otherStudent.getMajorName();
        if (majorName == null) {
            majorName = "";
        }
        String className = otherStudent.getClassName();
        if (className == null) {
            className = "";
        }
        //这是jdk8 新特性Lambda写法
        queryWrapper.like(OtherStudent::getNo, no);
        queryWrapper.like(OtherStudent::getName, name);
        queryWrapper.like(OtherStudent::getMajorName, majorName);
        queryWrapper.like(OtherStudent::getClassName, className);
        IPage<OtherStudent> iPage = baseMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    @Override
    public void export(HttpServletResponse response, List<OtherStudent> lists) {
        XSSFWorkbook wb = ExcelUtils.getOtherStudentXSSFWorkbook(lists);
        String fileName = "其他学生表.xlsx";
        OutputStream outputStream = null;
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    OtherStudentService otherStudentService;

    @Override
    public void importOtherStudent(MultipartFile file) throws IOException {
        if (file != null) {
            Workbook wb = ExcelUtils.getWB(file);
            if (wb != null) {
                List<OtherStudent> list = getOtherStudentList(wb);
                if (list != null && list.size() > 0) {
                    otherStudentService.saveBatch(list);
                }
            }
        }
    }

    private List<OtherStudent> getOtherStudentList(Workbook wb) {
        List<OtherStudent> list = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                OtherStudent workStudent = new OtherStudent();

                Cell cell1 = row.getCell(0);//学号
                workStudent.setNo(cell1.getStringCellValue());

                Cell cell2 = row.getCell(1);//姓名
                workStudent.setName(cell2.getStringCellValue());

                Cell cell3 = row.getCell(2);//性别
                workStudent.setSex(cell3.getStringCellValue());

                Cell cell4 = row.getCell(3);//学院名称
                workStudent.setCollegeName(cell4.getStringCellValue());

                Cell cell5 = row.getCell(4);//专业名称
                workStudent.setMajorName(cell5.getStringCellValue());

                Cell cell6 = row.getCell(5);//班级名称
                workStudent.setClassName(cell6.getStringCellValue());

                Cell cell7 = row.getCell(6);//个人电话setPhone
                workStudent.setPhone(cell7.getStringCellValue());

                Cell cell8 = row.getCell(7);//开始时间
                workStudent.setPlan(cell8.getStringCellValue());

                Cell cell9 = row.getCell(8);//开始时间
                workStudent.setReason(cell9.getStringCellValue());

                Cell cell10 = row.getCell(9);//开始时间
                workStudent.setNowAddress(cell10.getStringCellValue());
                list.add(workStudent);
            }
        }
        return list;
    }
}
