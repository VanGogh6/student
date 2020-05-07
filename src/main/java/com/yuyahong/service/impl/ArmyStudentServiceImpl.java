package com.yuyahong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuyahong.entity.ArmyStudent;
import com.yuyahong.mapper.ArmyStudentMapper;
import com.yuyahong.service.ArmyStudentService;
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
public class ArmyStudentServiceImpl extends ServiceImpl<ArmyStudentMapper, ArmyStudent> implements ArmyStudentService {

    @Override
    public IPage<ArmyStudent> getArmyStudentPage(Page<ArmyStudent> page, ArmyStudent armyStudent) {
        LambdaQueryWrapper<ArmyStudent> queryWrapper = new LambdaQueryWrapper<>();
        String no = armyStudent.getNo();
        if (no == null) {
            no = "";
        }
        String name = armyStudent.getName();
        if (name == null) {
            name = "";
        }
        String majorName = armyStudent.getMajorName();
        if (majorName == null) {
            majorName = "";
        }
        String className = armyStudent.getClassName();
        if (className == null) {
            className = "";
        }
        //这是jdk8 新特性Lambda写法
        queryWrapper.like(ArmyStudent::getNo, no);
        queryWrapper.like(ArmyStudent::getName, name);
        queryWrapper.like(ArmyStudent::getMajorName, majorName);
        queryWrapper.like(ArmyStudent::getClassName, className);
        IPage<ArmyStudent> iPage = baseMapper.selectPage(page, queryWrapper);
        return iPage;
    }

    @Override
    public void export(HttpServletResponse response, List<ArmyStudent> lists) {
        XSSFWorkbook wb = ExcelUtils.getArmyStudentXSSFWorkbook(lists);
        String fileName = "参军学生表.xlsx";
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
    private ArmyStudentService armyStudentService;

    @Override
    public void importArmyStudent(MultipartFile file) throws IOException {
        if (file != null) {
            Workbook wb = ExcelUtils.getWB(file);
            if (wb != null) {
                List<ArmyStudent> list = getArmyStudentList(wb);
                if (list != null && list.size() > 0) {
                    armyStudentService.saveBatch(list);
                }
            }
        }
    }

    private List<ArmyStudent> getArmyStudentList(Workbook wb) {
        List<ArmyStudent> list = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
//                学号	姓名	性别	学院名称	专业名称	班级名称	个人电话	开始时间	参军时长
                ArmyStudent workStudent = new ArmyStudent();

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
                workStudent.setArmyBeginTime(cell8.getStringCellValue());

                Cell cell9 = row.getCell(8);//开始时间
                workStudent.setArmyDuration(cell9.getStringCellValue());
                list.add(workStudent);
            }
        }
        return list;
    }

}
