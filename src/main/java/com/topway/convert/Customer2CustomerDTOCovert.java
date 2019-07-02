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
        customerDTO.setAddress(userList1.get(0).getAddress());
        customerDTO.setBusinessType(customer.getCustomerType());
        List<String> deviceNoList = new ArrayList<>();
        deviceNoList.add("<em>"+deviceNo+"</em>");
        switch (userList1.size()){
            case 1: break;
            case 2:
                if (userList1.get(0).getDeviceNo()!=null && !userList1.get(0).getDeviceNo().equals(deviceNo))
                    deviceNoList.add(userList1.get(0).getDeviceNo());
                else if (userList1.get(1).getDeviceNo()!=null && !userList1.get(1).getDeviceNo().equals(deviceNo))
                    deviceNoList.add(userList1.get(1).getDeviceNo());
                break;
            case 3:
            default:
                if (userList1.get(0).getDeviceNo()!=null && !userList1.get(0).getDeviceNo().equals(deviceNo))
                    deviceNoList.add(userList1.get(0).getDeviceNo());
                else if (userList1.get(1).getDeviceNo()!=null && !userList1.get(1).getDeviceNo().equals(deviceNo))
                    deviceNoList.add(userList1.get(1).getDeviceNo());
                else if (userList1.get(2).getDeviceNo()!=null && !userList1.get(2).getDeviceNo().equals(deviceNo))
                    deviceNoList.add(userList1.get(2).getDeviceNo());
                break;
        }
        customerDTO.setDeviceNoList(deviceNoList);

        return customerDTO;
    }


    static public CustomerDTO covert(Customer customer, List<User> userList1){

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setPhone(customer.getPhone());
        if (userList1.size()>0)
            customerDTO.setAddress(userList1.get(0).getAddress());
        customerDTO.setBusinessType(customer.getCustomerType());
        List<String> deviceNoList = new ArrayList<>();
        if (userList1.size()>0)
            deviceNoList.add(userList1.get(0).getDeviceNo());
        if (userList1.size()>1)
            deviceNoList.add(userList1.get(1).getDeviceNo());
        customerDTO.setDeviceNoList(deviceNoList);

        return customerDTO;
    }
}
