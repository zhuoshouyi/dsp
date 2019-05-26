package com.topway.convert;

import com.topway.dto.CustomerDTO;
import com.topway.pojo.Customer;
import com.topway.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/5/24.
 */
public class Customer2CustomerDTOCovert {

    static public CustomerDTO covert(Customer customer, List<User> userList1, String deviceNo){

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setAddress(userList1.get(0).getFka7e6d50f());
        customerDTO.setBusinessType(customer.getCustomerType());
        List<String> deviceNoList = new ArrayList<>();
        deviceNoList.add(deviceNo);
        if (userList1.size()>1)
            deviceNoList.add(userList1.get(1).getFkce29e60a());
        customerDTO.setDeviceNoList(deviceNoList);

        return customerDTO;
    }


    static public CustomerDTO covert(Customer customer, List<User> userList1){

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setAddress(userList1.get(0).getFka7e6d50f());
        customerDTO.setBusinessType(customer.getCustomerType());
        List<String> deviceNoList = new ArrayList<>();
        if (userList1.size()>0)
            deviceNoList.add(userList1.get(0).getFkce29e60a());
        if (userList1.size()>1)
            deviceNoList.add(userList1.get(1).getFkce29e60a());
        customerDTO.setDeviceNoList(deviceNoList);

        return customerDTO;
    }
}
