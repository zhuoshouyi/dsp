package com.topway.DAO;

import com.topway.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/5/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao dao;

    @Test
    public void findByDeviceNoToCustomer(){

        PageRequest pageRequest = new PageRequest(0,5);

        List<String> list = new ArrayList<>();
        list.add("1621");
        list.add("888");

        Page<Customer> customerPage = dao.findByDeviceNoToCustomer("1342", list, null, null, pageRequest);
        System.out.println("");
    }
}