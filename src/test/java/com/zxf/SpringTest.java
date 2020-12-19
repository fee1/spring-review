package com.zxf;

import com.zxf.bean.Teacher;
import com.zxf.config.StartConfigrantion;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StartConfigrantion.class)
public class SpringTest {

    @Autowired
    Teacher teacher;

    public void getBeanTest(){
        System.out.println(teacher.toString());
    }

}
