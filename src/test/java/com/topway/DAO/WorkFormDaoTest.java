package com.topway.DAO;

import com.topway.pojo.WorkForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by haizhi on 2019/5/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkFormDaoTest {

    @Autowired
    WorkFormDao dao;

    @Test
    public void findJoinWorkFormAndUser() throws Exception {

        PageRequest pageRequest = new PageRequest(0, 5);
        Page<WorkForm> workFormPage = dao.findJoinWorkFormAndUser("1010577290", "6100000237362", pageRequest);
        System.out.println("");
    }

    @Test
    public void test(){
        WorkForm workForm = dao.findJoinWorkFormAndUserDetail("1010577290", "6100000237362", "331624");
        System.out.println("");
    }

}