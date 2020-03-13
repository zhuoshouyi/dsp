package com.topway.service;

import com.topway.VO.ResultVO;
import com.topway.dto.*;
import com.topway.form.UserLabelForm;
import com.topway.pojo.BrowseRecord;
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
    Page<CustomerDTO> findByDeviceNoLike(UserRoleDTO userRoleDTO, String deviceNo, Pageable pageable);

    /** 通过电话号码模糊查询. */
    Page<CustomerDTO> findByPhoneLike(UserRoleDTO userRoleDTO, String phone, Pageable pageable);

    /** 通过客户名称模糊查询. */
    Page<CustomerDTO> findByCustomerNameLike(UserRoleDTO userRoleDTO, String customerName, Pageable pageable);

    /** 通过客户编码模糊查询. */
    Page<CustomerDTO> findByCustomerIdLike(UserRoleDTO userRoleDTO, String customerId, Pageable pageable);

    /** 通过客户编码查询. */
    CustomerDTO findByCustomerId(String customerId);

    /** 终端搜索查询 */
    List<List<String>> findDeviceSearchByCustomerId(String customerId, String keyword);

    /** 浏览记录查询 */
    List<BrowseRecord> findBrowseRecord(String userId);

    /** 浏览记录修改 */
    void saveBrowseRecord(String userId, String valueId, String valueName);


    // 终端信息相关方法
    /** 通过设备号查询 DeviceDTO */
    DeviceBasicInfoDTO findDeviceBasicInfoDTO(String customerId, String deviceNo);

    List<DeviceBusinessInfoDetailDTO> findDeviceBusinessInfoDTO(String customerId, String deviceNo);

    List<DeviceWorkOrderDTO> findDeviceWorkOrderDTO(String customerId, String deviceNo);

    List<DeviceComplaintDTO> findDeviceComplaintDTO(String customerId, String deviceNo);

    DeviceWatchActionDTO findDeviceWatchActionDTO(String customerId, String deviceNo);


    /** 详情查看 */
    DeviceBusinessInfoDetailDTO findDeviceBusinessInfoDetailDTO(String customerId, String deviceNo, String id);

    DeviceWorkOrderDetailDTO findDeviceWorkOrderDetailDTO(String customerId, String deviceNo, String id);

    DeviceComplaintDetailDTO findDeviceComplaintDetailDTO(String customerId, String deviceNo, String id);


    /** 滑动接口 */
    Page<DeviceBusinessInfoDetailDTO> findBusinessSlide(String customerId, String deviceNo, Pageable pageable);

    Page<DeviceWorkOrderDTO> findWorkOrderSlide(String customerId, String deviceNo, Pageable pageable);

    Page<DeviceComplaintDTO> findComplaintSlide(String customerId, String deviceNo, Pageable pageable);


    /** 标签 */
    List<UserLabelShowDTO> findUserLabelShowDTO(String customerId);

    ResultVO saveUserLabel(UserRoleDTO userRoleDTO, UserLabelForm userLabelForm, String date);

    ResultVO findUserLabelLastRecord(String customerId);

}
