package com.topway.service.Impl;

import com.topway.DAO.CustomerDao;
import com.topway.DAO.UserDao;
import com.topway.DAO.UserProductDao;
import com.topway.DAO.WorkFormDao;
import com.topway.convert.Customer2CustomerDTOCovert;
import com.topway.convert.User2DeviceBasicInfoDTOConvert;
import com.topway.convert.UserProduct2DeviceBusinessInfoDTOConvert;
import com.topway.convert.WorkForm2DeviceWorkOrderDTOConvert;
import com.topway.dto.CustomerDTO;
import com.topway.dto.DeviceBasicInfoDTO;
import com.topway.dto.DeviceBusinessInfoDTO;
import com.topway.dto.DeviceWorkOrderDTO;
import com.topway.pojo.Customer;
import com.topway.pojo.User;
import com.topway.pojo.UserProduct;
import com.topway.pojo.WorkForm;
import com.topway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haizhi on 2019/5/23.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    CustomerDao customerDao;
    UserProductDao userProductDao;
    WorkFormDao workFormDao;


    /**
     * 通过资源号模糊查询
     *
     * @return
     */
    @Override
    public Page<CustomerDTO> findByDeviceNoLike(String deviceNo, Pageable pageable) {

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        Page<Customer> customerPage = userDao.findByDeviceNoToCustomer(deviceNo, pageable);

        for (Customer customer : customerPage){
            List<User> userList1 = userDao.findByFkc1f5a1c1(customer.getCustomerId());
            CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1, deviceNo);
            customerDTOList.add(customerDTO);
        }

        return new PageImpl(customerDTOList, pageable, customerDTOList.size());
    }

    /**
     * 通过电话号码模糊查询
     *
     * @return
     */
    @Override
    public Page<CustomerDTO> findByPhoneLike(String phone, Pageable pageable) {

        List<CustomerDTO> customerDTOList = new ArrayList<>();

        Page<Customer> customerPage = customerDao.findByPhoneLike("%" + phone + "%", pageable);
        for (Customer customer : customerPage) {
            List<User> userList1 = userDao.findByFkc1f5a1c1(customer.getCustomerId());

            CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
            customerDTOList.add(customerDTO);
        }

        return new PageImpl(customerDTOList, pageable, customerDTOList.size());
    }

    /**
     * 通过客户名称模糊查询
     *
     * @return
     */
    @Override
    public Page<CustomerDTO> findByCustomerNameLike(String customerName, Pageable pageable) {

        List<CustomerDTO> customerDTOList = new ArrayList<>();

        Page<Customer> customerPage = customerDao.findByCustomerNameLike("%" + customerName + "%", pageable);
        for (Customer customer : customerPage) {
            List<User> userList1 = userDao.findByFkc1f5a1c1(customer.getCustomerId());

            CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
            customerDTOList.add(customerDTO);
        }

        return new PageImpl(customerDTOList, pageable, customerDTOList.size());
    }

    /**
     * 通过客户编码模糊查询
     *
     * @return
     */
    @Override
    public Page<CustomerDTO> findByCustomerIdLike(String customerId, Pageable pageable) {

        List<CustomerDTO> customerDTOList = new ArrayList<>();

        Page<Customer> customerPage = customerDao.findByCustomerIdLike("%" + customerId + "%", pageable);
        for (Customer customer : customerPage) {
            List<User> userList1 = userDao.findByFkc1f5a1c1(customer.getCustomerId());

            CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
            customerDTOList.add(customerDTO);
        }

        return new PageImpl(customerDTOList, pageable, customerDTOList.size());
    }


    /**
     * 通过客户Id
     *
     * @param customerId
     * @return
     */
    @Override
    public CustomerDTO findByCustomerId(String customerId) {

        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = customerDao.findByCustomerId(customerId);

        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setBusinessType(customer.getCustomerType());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setPaperNo(customer.getPaperNo());
        customerDTO.setCustomerId(customer.getCustomerId());

        List<User> userList = userDao.findByFkc1f5a1c1(customerId);
        customerDTO.setAddress(userList.get(0).getFka7e6d50f());
        customerDTO.setDeviceNoList(
                userList.stream().map(e -> e.getFkce29e60a()).collect(Collectors.toList())
        );

        return customerDTO;
    }


    /**
     * 通过 deviceNo 查询 DeviceBasicInfoDTO
     * @param deviceNo
     * @return
     */
    @Override
    public DeviceBasicInfoDTO findDeviceBasicInfoDTO(String customerId, String deviceNo) {
        User user = userDao.findByFkc1f5a1c1AndFkce29e60a(customerId, deviceNo);

        return User2DeviceBasicInfoDTOConvert.convert(user);

    }

    /**
     * 通过 customerId 和 deviceNo 查询产品信息
     * @param customerId
     * @param deviceNo
     * @return
     */
    @Override
    public List<DeviceBusinessInfoDTO> findDeviceBusinessInfoDTO(String customerId, String deviceNo) {
        PageRequest pageRequest = new PageRequest(0, 5);
        Page<UserProduct> userProductPage = userProductDao.findJoinUserAndUserProduct(customerId, deviceNo, pageRequest);
        return UserProduct2DeviceBusinessInfoDTOConvert.convert(userProductPage.getContent());
    }


    /**
     * 通过 customerId 和 deviceNo 查询工单信息
     * @param customerId
     * @param deviceNo
     * @return
     */
    @Override
    public List<DeviceWorkOrderDTO> findDeviceWorkOrderDTO(String customerId, String deviceNo) {
        PageRequest pageRequest = new PageRequest(0, 5);
        Page<WorkForm> workFormPage = workFormDao.findJoinWorkFormAndUser(customerId, deviceNo, pageRequest);
        return WorkForm2DeviceWorkOrderDTOConvert.convert(workFormPage.getContent());
    }
}
