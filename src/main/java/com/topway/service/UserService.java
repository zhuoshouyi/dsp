package com.topway.service;

import com.topway.dto.CustomerDTO;
import com.topway.dto.DeviceBasicInfoDTO;
import com.topway.dto.DeviceBusinessInfoDTO;
import com.topway.dto.DeviceWorkOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by haizhi on 2019/5/23.
 */
@Service
public interface UserService {

    /** 通过资源号模糊查询. */
    Page<CustomerDTO> findByDeviceNoLike(String deviceNo, Pageable pageable);

    /** 通过电话号码模糊查询. */
    Page<CustomerDTO> findByPhoneLike(String phone, Pageable pageable);

    /** 通过客户名称模糊查询. */
    Page<CustomerDTO> findByCustomerNameLike(String customerName, Pageable pageable);

    /** 通过客户编码模糊查询. */
    Page<CustomerDTO> findByCustomerIdLike(String customerId, Pageable pageable);

    /** 通过客户编码查询. */
    CustomerDTO findByCustomerId(String customerId);

    // 终端信息相关方法
    /** 通过设备号查询 DeviceDTO */
    DeviceBasicInfoDTO findDeviceBasicInfoDTO(String customerId, String deviceNo);

    List<DeviceBusinessInfoDTO> findDeviceBusinessInfoDTO(String customerId, String deviceNo);

    List<DeviceWorkOrderDTO> findDeviceWorkOrderDTO(String customerId, String deviceNo);



}