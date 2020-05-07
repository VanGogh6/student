package com.yuyahong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyahong.entity.KaoyanStudent;
import com.yuyahong.mapper.KaoyanStudentMapper;
import com.yuyahong.service.KaoyanStudentService;
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
public class KaoyanStudentServiceImpl extends ServiceImpl<KaoyanStudentMapper, KaoyanStudent> implements KaoyanStudentService {

    /**
     * BaseMapper<KaoyanStudent> baseMapper
     * 这是mybatis-plus框架封装完的查询，直接使用已经有的查询、
     * 如果有复杂查询自定义即可
     */
    @Autowired
    private BaseMapper<KaoyanStudent> baseMapper;

    /**
     * 具体的查询方法：参照之前写的WorkStudentServiceImpl中的getWorkStudentPage方法
     *
     * @param page
     * @param kaoyanStudent
     * @return
     */
    @Override
    public IPage<KaoyanStudent> getWorkStudentPage(Page<KaoyanStudent> page, KaoyanStudent kaoyanStudent) {
        LambdaQueryWrapper<KaoyanStudent> queryWrapper = new LambdaQueryWrapper<>();
        String no = kaoyanStudent.getNo();
        if (no == null) {
            no = "";
        }
        String name = kaoyanStudent.getName();
        if (name == null) {
            name = "";
        }
        String majorName = kaoyanStudent.getMajorName();
        if (majorName == null) {
            majorName = "";
        }
        String className = kaoyanStudent.getClassName();
        if (className == null) {
            className = "";
        }
        //这是jdk8 新特性Lambda写法
        queryWrapper.like(KaoyanStudent::getNo, no);
        queryWrapper.like(KaoyanStudent::getName, name);
        queryWrapper.like(KaoyanStudent::getMajorName, majorName);
        queryWrapper.like(KaoyanStudent::getClassName, className);
        IPage<KaoyanStudent> iPage = baseMapper.selectPage(page, queryWrapper);
        return iPage;
    }


    @Override
    public void export(HttpServletResponse response, List<KaoyanStudent> lists) {
        XSSFWorkbook wb = ExcelUtils.getWorkKaoyanStudentXSSFWorkbook(lists);
        String fileName = "考研学生表.xlsx";
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
    KaoyanStudentService kaoyanStudentService;

    @Override
    public void importKaoyanStudent(MultipartFile file) throws IOException {
        if (file != null) {
            Workbook wb = ExcelUtils.getWB(file);
            if (wb != null) {
                List<KaoyanStudent> list = getKaoyanStudentList(wb);
                if (list != null && list.size() > 0) {
                    kaoyanStudentService.saveBatch(list);
                }
            }
        }
    }

    private List<KaoyanStudent> getKaoyanStudentList(Workbook wb) {
        List<KaoyanStudent> list = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                KaoyanStudent workStudent = new KaoyanStudent();
                Cell cell1 = row.getCell(0);
                workStudent.setNo(cell1.getStringCellValue());
                Cell cell2 = row.getCell(1);
                workStudent.setName(cell2.getStringCellValue());
                Cell cell3 = row.getCell(2);
                workStudent.setCollegeName(cell3.getStringCellValue());
                Cell cell4 = row.getCell(3);
                workStudent.setMajorName(cell4.getStringCellValue());
                Cell cell5 = row.getCell(4);
                workStudent.setClassName(cell5.getStringCellValue());
                Cell cell6 = row.getCell(5);
                workStudent.setPhone(cell6.getStringCellValue());
                Cell cell7 = row.getCell(6);
                workStudent.setKaoyanSchool(cell7.getStringCellValue());
                list.add(workStudent);
            }
        }
        return list;
    }
}
