package com.yuyahong;
import com.yuyahong.entity.ArmyStudent;
import com.yuyahong.service.ArmyStudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
/**
 * 参军学生相关测试
 * @author wufan
 * @date 2020/4/21 0021 17:58
 */
public class ArmyStudentTest {
    @Autowired
    private ArmyStudentService armyStudentService;

    /**
     * 新增参军学生测试
     */
    @Test
    void saveArmyStudent() {
        ArmyStudent armyStudent=new ArmyStudent();
        armyStudent.setNo("20164081111");
        armyStudent.setName("吴帆");
        armyStudent.setSex("男");
        armyStudent.setArmyBeginTime("2020年7月1日");
        armyStudent.setArmyDuration("2年");
        armyStudent.setCollegeName("信息学院");
        boolean save = armyStudentService.save(armyStudent);
        System.out.println(save);
    }

}
