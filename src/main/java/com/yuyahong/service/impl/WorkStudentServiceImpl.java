package com.yuyahong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyahong.entity.WorkStudent;
import com.yuyahong.mapper.WorkStudentMapper;
import com.yuyahong.service.WorkStudentService;
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
public class WorkStudentServiceImpl extends ServiceImpl<WorkStudentMapper, WorkStudent> implements WorkStudentService {
    @Autowired
    private BaseMapper<WorkStudent> baseMapper;

    @Override
    public IPage<WorkStudent> getWorkStudentPage(Page<WorkStudent> page, WorkStudent workStudent) {
        LambdaQueryWrapper<WorkStudent> queryWrapper = new LambdaQueryWrapper<>();
        String no = workStudent.getNo();
        if (no == null) {
            no = "";
        }
        String name = workStudent.getName();
        if (name == null) {
            name = "";
        }
        String majorName = workStudent.getMajorName();
        if (majorName == null) {
            majorName = "";
        }
        String className = workStudent.getClassName();
        if (className == null) {
            className = "";
        }
        queryWrapper.like(WorkStudent::getNo, no);
        queryWrapper.like(WorkStudent::getName, name);
        queryWrapper.like(WorkStudent::getMajorName, majorName);
        queryWrapper.like(WorkStudent::getClassName, className);
        IPage<WorkStudent> iPage = baseMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    @Override
    public void export(HttpServletResponse response, List<WorkStudent> lists) {
        XSSFWorkbook wb = ExcelUtils.getWorkStudentXSSFWorkbook(lists);
        String fileName = "就业学生表.xlsx";
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
    private WorkStudentService workStudentService;

    @Override
    public void importWorkStudent(MultipartFile file) throws IOException {
        if (file != null) {
            Workbook wb = ExcelUtils.getWB(file);
            if (wb != null) {
                List<WorkStudent> list = getWorkStudentList(wb);
                if (list != null && list.size() > 0) {
                    workStudentService.saveBatch(list);
                }
            }
        }
    }

    private static List<WorkStudent> getWorkStudentList(Workbook wb) {
        List<WorkStudent> list = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                WorkStudent workStudent = new WorkStudent();
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
                workStudent.setWorkCompany(cell7.getStringCellValue());
                list.add(workStudent);
            }
        }
        return list;
    }
}
