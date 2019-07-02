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

        List<String> list1 = new ArrayList<>();
        list1.add("天威");

        List<String> list2 = new ArrayList<>();
        list2.add("福田分公司");
        list2.add("罗湖分公司");

        Page<Customer> customerPage = dao.findByDeviceNoToCustomer("1342", null, list1, list2, pageRequest);
        System.out.println("");
    }

    @Test
    public void findByGridId(){

        List<String> list = new ArrayList<>();
        list.add("128");
        list.add("1270");
        List<String> stringList = dao.findByGridId(list);
        System.out.println(stringList.get(0));
    }
}