package com.topway.service.Impl;

import com.topway.DAO.*;
import com.topway.convert.*;
import com.topway.dto.*;
import com.topway.pojo.*;
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
    ComplaintDao complaintDao;
    WatchActionDao watchActionDao;


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
            List<User> userList1 = userDao.findByFk572f5a34(customer.getCustomerId());
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
            List<User> userList1 = userDao.findByFk572f5a34(customer.getCustomerId());

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
            List<User> userList1 = userDao.findByFk572f5a34(customer.getCustomerId());

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
            List<User> userList1 = userDao.findByFk572f5a34(customer.getCustomerId());

            CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
            customerDTOList.add(customerDTO);
        }

        return new PageImpl(customerDTOList, pageable, customerDTOList.size());
    }


    /***********************************************************************************/





    /**
     * 通过 customerId 查询 CustomerDTO
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

        List<User> userList = userDao.findByFk572f5a34(customerId);
        customerDTO.setAddress(userList.get(0).getFkc398514b());
        customerDTO.setDeviceNoList(
                userList.stream().map(e -> e.getFkdf1e945e()).collect(Collectors.toList())
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
        User user = userDao.findByFk572f5a34AndFkdf1e945e(customerId, deviceNo);

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

    /**
     * 通过 customerId 和 deviceNo 查询投诉单信息
     * @param customerId
     * @param deviceNo
     * @return
     */
    @Override
    public List<DeviceComplaintDTO> findDeviceComplaintDTO(String customerId, String deviceNo) {
        PageRequest pageRequest = new PageRequest(0, 5);
        Page<Complaint> complaintPage = complaintDao.findJoinComplaintAndUser(customerId, deviceNo, pageRequest);
        return Complaint2DeviceComplaintDTOConvert.convert(complaintPage.getContent());
    }


    /**
     * 通过 customerId 和 deviceNo 查询收视行为信息
     * @param customerId
     * @param deviceNo
     * @return
     */
    @Override
    public DeviceWatchActionDTO findDeviceWatchActionDTO(String customerId, String deviceNo) {
        DeviceWatchActionDTO deviceWatchActionDTO = new DeviceWatchActionDTO();
        List<WatchAction> watchActionList = watchActionDao.findJoinWatchActionAndUser(customerId, deviceNo);

        return null;
    }



    /***********************************************************************************/






    /**
     * 通过 customerId 、 deviceNo 和 id 查询产品详情信息
     * @param customerId
     * @param deviceNo
     * @return
     */
    @Override
    public DeviceBusinessInfoDetailDTO findDeviceBusinessInfoDetailDTO(String customerId, String deviceNo, String id) {

        UserProduct userProduct = userProductDao.findJoinUserAndUserProductDetail(customerId, deviceNo, id);
        DeviceBusinessInfoDetailDTO deviceBusinessInfoDetailDTO =
                UserProduct2DeviceBusinessInfoDetailDTOConvert.convert(userProduct);
        deviceBusinessInfoDetailDTO.setDeviceNo(deviceNo);
        return deviceBusinessInfoDetailDTO;
    }


    /**
     * 通过 customerId 和 deviceNo 和 id 查询工单详情信息
     * @param customerId
     * @param deviceNo
     * @return
     */
    @Override
    public DeviceWorkOrderDetailDTO findDeviceWorkOrderDetailDTO(String customerId, String deviceNo, String id) {
        WorkForm workForm = workFormDao.findJoinWorkFormAndUserDetail(customerId, deviceNo, id);
        // TODO
        return null;
    }

    /**
     * 通过 customerId 和 deviceNo 和 id 查询投诉单详情信息
     * @param customerId
     * @param deviceNo
     * @return
     */
    @Override
    public DeviceComplaintDetailDTO findDeviceComplaintDetailDTO(String customerId, String deviceNo, String id) {
        Complaint complaint = complaintDao.findJoinComplaintAndUserDetail(customerId, deviceNo, id);
        DeviceComplaintDetailDTO deviceComplaintDetailDTO = new DeviceComplaintDetailDTO();
        List<DealWithMessageDTO> dealWithMessageDTOList = new ArrayList<>();
        ComplaintDTO complaintDTO = new ComplaintDTO();

        // fk2d546781 投诉编码
        complaintDTO.setOrderId(complaint.getFk2d546781());
        // fk0b5c4bd1 受理时间
        complaintDTO.setAcceptTime(complaint.getFk0b5c4bd1());
        // fkee9a3c22 投诉来源
        complaintDTO.setComplaintFrom(complaint.getFkee9a3c22());
        // fk25c3d8d3 结单时间
        complaintDTO.setEndTime(complaint.getFk25c3d8d3());
        // fk7fadbec0 处理方式分类一 fk14d9b227 处理方式分类二
        complaintDTO.setDealWithWay(complaint.getFk7fadbec0() + "/" + complaint.getFk14d9b227());
        // fkcf8c69a6 投诉单内容

        // TODO

        deviceComplaintDetailDTO.setComplaint(complaintDTO);
        deviceComplaintDetailDTO.setDealWithMessage(dealWithMessageDTOList);
        return null;
    }

}
