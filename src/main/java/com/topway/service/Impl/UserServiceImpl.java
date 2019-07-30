package com.topway.service.Impl;

import com.topway.DAO.*;
import com.topway.VO.ResultVO;
import com.topway.convert.*;
import com.topway.dto.*;
import com.topway.enums.ResultEnum;
import com.topway.form.UserLabelForm;
import com.topway.pojo.*;
import com.topway.service.UserService;
import com.topway.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


/**cd
 * Created by haizhi on 2019/5/23.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    UserProductDao userProductDao;

    @Autowired
    WorkFormDao workFormDao;

    @Autowired
    ComplaintDao complaintDao;

    @Autowired
    WatchActionDao watchActionDao;

    @Autowired
    UserLabelDao userLabelDao;

    @Autowired
    BrowseRecordDao browseRecordDao;

    // 全局统一时间格式化格式
    SimpleDateFormat FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 通过资源号模糊查询
     *
     * @return
     */
    @Override
    public Page<CustomerDTO> findByDeviceNoLike(UserRoleDTO userRoleDTO, String deviceNo, Pageable pageable) {

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        Page<Customer> customerPage;
        switch (userRoleDTO.getUserRole()){
            case "基础网格员":
            case "支撑网格员":
            case "站长":
                log.info("【认证】身份为 基础网格员、支撑网格员 或 站长");

                customerPage = userDao.findByDeviceNoToCustomer(deviceNo, userRoleDTO.getServiceGridId(), null, null, pageable);

                log.info("【客户查询】客户检索完毕");
                for (Customer customer : customerPage){
                    List<User> userList1 = userDao.findByCustomerId(customer.getCustomerId());
                    CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1, deviceNo);
                    customerDTOList.add(customerDTO);
                }

                break;

            case "公司领导":
            case "业务部门":
                log.info("【认证】身份为 公司领导 或 业务部门");
                log.info("spcode:" + userRoleDTO.getSpcodeId());
                log.info("branch:" + userRoleDTO.getBusinessOfficeId());
                customerPage = userDao.findByDeviceNoToCustomer(deviceNo, null, userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), pageable);

                log.info("【客户查询】客户检索完毕");
                for (Customer customer : customerPage){
                    List<User> userList1 = userDao.findByCustomerId(customer.getCustomerId());
                    for (User user : userList1){
                        if (user.getDeviceNo()!=null && user.getDeviceNo().contains(deviceNo))
                            deviceNo = user.getDeviceNo();
                    }
                    CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1, deviceNo);
                    customerDTOList.add(customerDTO);
                }
                break;

            default:
                log.info("【认证】无用户身份,默认网格员");
//                customerPage = userDao.findByDeviceNoToCustomer(deviceNo, userRoleDTO.getServiceGridId(), null, null, pageable);
                customerPage = userDao.findByDeviceNoToCustomer(deviceNo, userRoleDTO.getServiceGridId(), null, null, pageable);

                log.info("【客户查询】客户检索完毕");
                for (Customer customer : customerPage){
                    List<User> userList1 = userDao.findByCustomerId(customer.getCustomerId());
                    CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1, deviceNo);
                    customerDTOList.add(customerDTO);
                }
                break;
        }


        return new PageImpl(customerDTOList, pageable, customerDTOList.size());
    }

    /**
     * 通过电话号码模糊查询
     *
     * @return
     */
    @Override
    public Page<CustomerDTO> findByPhoneLike(UserRoleDTO userRoleDTO, String phone, Pageable pageable) {

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        Page<Customer> customerPage;
        switch (userRoleDTO.getUserRole()){
            case "基础网格员":
            case "支撑网格员":
            case "站长":
                log.info("【认证】身份为 基础网格员、支撑网格员 或 站长");

                customerPage = customerDao.findByPhoneLike(phone, userRoleDTO.getServiceGridId(), null, null, pageable);

                log.info("【客户查询】客户检索完毕");
                for (Customer customer : customerPage) {
                    List<User> userList1 = userDao.findByCustomerId(customer.getCustomerId());

                    CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
                    customerDTOList.add(customerDTO);
                }
                customerDTOList.stream()
                        .forEach(e -> e.setPhone(e.getPhone().replace(phone, "<em>"+phone+"</em>")));

                break;

            case "公司领导":
            case "业务部门":
                log.info("【认证】身份为 公司领导 或 业务部门");
                customerPage = customerDao.findByPhoneLike(phone, null, userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), pageable);

                log.info("【客户查询】客户检索完毕");
                for (Customer customer : customerPage) {
                    List<User> userList1 = userDao.findByCustomerId(customer.getCustomerId());

                    CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
                    customerDTOList.add(customerDTO);
                }
                customerDTOList.stream()
                        .forEach(e -> e.setPhone(e.getPhone().replace(phone, "<em>"+phone+"</em>")));

                break;

            default:
                log.info("【认证】无用户身份,默认网格员");
                customerPage = customerDao.findByPhoneLike(phone, userRoleDTO.getServiceGridId(), null, null, pageable);

                log.info("【客户查询】客户检索完毕");
                for (Customer customer : customerPage) {
                    List<User> userList1 = userDao.findByCustomerId(customer.getCustomerId());

                    CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
                    customerDTOList.add(customerDTO);
                }
                customerDTOList.stream()
                        .forEach(e -> e.setPhone(e.getPhone().replace(phone, "<em>"+phone+"</em>")));

                break;
        }

        return new PageImpl(customerDTOList, pageable, customerDTOList.size());
    }

    /**
     * 通过客户名称模糊查询
     *
     * @return
     */
    @Override
    public Page<CustomerDTO> findByCustomerNameLike(UserRoleDTO userRoleDTO, String customerName, Pageable pageable) {

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        Page<Customer> customerPage;
        switch (userRoleDTO.getUserRole()){
            case "基础网格员":
            case "支撑网格员":
            case "站长":
                log.info("【认证】身份为 基础网格员、支撑网格员 或 站长");

                customerPage = customerDao.findByCustomerNameLike(customerName, userRoleDTO.getServiceGridId(), null, null, pageable);

                log.info("【客户查询】客户检索完毕");
                for (Customer customer : customerPage) {
                    List<User> userList1 = userDao.findByCustomerId(customer.getCustomerId());

                    CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
                    customerDTOList.add(customerDTO);
                }
                customerDTOList.stream()
                        .forEach(e -> e.setCustomerName(e.getCustomerName().replace(customerName, "<em>"+customerName+"</em>")));

                break;

            case "公司领导":
            case "业务部门":
                log.info("【认证】身份为 公司领导 或 业务部门");
                customerPage = customerDao.findByCustomerNameLike(customerName, null, userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), pageable);

                log.info("【客户查询】客户检索完毕");
                for (Customer customer : customerPage) {
                    List<User> userList1 = userDao.findByCustomerId(customer.getCustomerId());

                    CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
                    customerDTOList.add(customerDTO);
                }
                customerDTOList.stream()
                        .forEach(e -> e.setCustomerName(e.getCustomerName().replace(customerName, "<em>"+customerName+"</em>")));

                break;

            default:
                log.info("【认证】无用户身份,默认网格员");
                customerPage = customerDao.findByCustomerNameLike(customerName, userRoleDTO.getServiceGridId(), null, null, pageable);

                log.info("【客户查询】客户检索完毕");
                for (Customer customer : customerPage) {
                    List<User> userList1 = userDao.findByCustomerId(customer.getCustomerId());

                    CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
                    customerDTOList.add(customerDTO);
                }
                customerDTOList.stream()
                        .forEach(e -> e.setCustomerName(e.getCustomerName().replace(customerName, "<em>"+customerName+"</em>")));

                break;
        }

        return new PageImpl(customerDTOList, pageable, customerDTOList.size());
    }

    /**
     * 通过客户编码模糊查询
     *
     * @return
     */
    @Override
    public Page<CustomerDTO> findByCustomerIdLike(UserRoleDTO userRoleDTO, String customerId, Pageable pageable) {

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        Page<Customer> customerPage;
        switch (userRoleDTO.getUserRole()){
            case "基础网格员":
            case "支撑网格员":
            case "站长":
                log.info("【认证】身份为 基础网格员、支撑网格员 或 站长");

                customerPage = customerDao.findByCustomerIdLike(customerId, userRoleDTO.getServiceGridId(), null, null, pageable);

                log.info("【客户查询】客户检索完毕");
                for (Customer customer : customerPage) {
                    List<User> userList1 = userDao.findByCustomerId(customer.getCustomerId());

                    CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
                    customerDTOList.add(customerDTO);
                }
                customerDTOList.stream()
                        .forEach(e -> e.setCustomerId(e.getCustomerId().replace(customerId, "<em>"+customerId+"</em>")));

                break;

            case "公司领导":
            case "业务部门":
                log.info("【认证】身份为 公司领导 或 业务部门");
                customerPage = customerDao.findByCustomerIdLike(customerId, null, userRoleDTO.getSpcodeId(), userRoleDTO.getBusinessOfficeId(), pageable);

                log.info("【客户查询】客户检索完毕");
                for (Customer customer : customerPage) {
                    List<User> userList1 = userDao.findByCustomerId(customer.getCustomerId());

                    CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
                    customerDTOList.add(customerDTO);
                }
                customerDTOList.stream()
                        .forEach(e -> e.setCustomerId(e.getCustomerId().replace(customerId, "<em>"+customerId+"</em>")));

                break;

            default:
                log.info("【认证】无用户身份,默认网格员");
                customerPage = customerDao.findByCustomerIdLike(customerId, userRoleDTO.getServiceGridId(), null, null, pageable);

                log.info("【客户查询】客户检索完毕");
                for (Customer customer : customerPage) {
                    List<User> userList1 = userDao.findByCustomerId(customer.getCustomerId());

                    CustomerDTO customerDTO = Customer2CustomerDTOCovert.covert(customer, userList1);
                    customerDTOList.add(customerDTO);
                }
                customerDTOList.stream()
                        .forEach(e -> e.setCustomerId(e.getCustomerId().replace(customerId, "<em>"+customerId+"</em>")));

                break;
        }

        return new PageImpl(customerDTOList, pageable, customerDTOList.size());
    }


    /***********************************************************************************/



    public static void DeviceSort(List<DeviceDTO> deviceDTOList) {
        Collections.sort(deviceDTOList,new Comparator<DeviceDTO>() {
            @Override
            public int compare(DeviceDTO d1,DeviceDTO d2) {
                if(d1.getDevice_type().equals("数字电视")) return 1;
                else if (d2.getDevice_type().equals("宽带")) return -1;
                else return 0;
            }
        });
    }


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

        // 查找此 customerId 所有的 user, 如果 user 不为null或空,则取第一个 address
        List<User> userList = userDao.findByCustomerId(customerId);
        if (userList!=null && !userList.isEmpty())
            customerDTO.setAddress(userList.get(0).getAddress());

        // 创建一个不可重复的 Set 集合,存放此 customer 的所有的融合编码
        Set<String> mixSet = new HashSet<>();
        for (User user : userList){
            if (user.getMixNo() != null && !user.getMixNo().isEmpty())
                mixSet.add(user.getMixNo());
        }

        // 创建一个二维数组存放deviceNo
        List<List<DeviceDTO>> deviceList = new ArrayList<>(new ArrayList<>());

        // 遍历融合编码的数组,遍历每一个融合编码的用户,找到两个融合编码相同的 deviceNo,将其拼装成 list,添加到 deviceList 中。
        mixSet.stream().forEach(x -> {
            List<DeviceDTO> list = new ArrayList<>();
            for (User user : userList){
                if (user.getMixNo()!=null && !user.getMixNo().isEmpty())
                    if (user.getMixNo().equals(x)) {
                        DeviceDTO deviceDTO = new DeviceDTO();
                        deviceDTO.setDeviceNo(user.getDeviceNo());
                        deviceDTO.setDevice_type(user.getBusinessType());
                        list.add(deviceDTO);
                    }
            }
            // 使用匿名内部类,完成自定义排序
            DeviceSort(list);
            deviceList.add(list);
        });

        // 遍历完所有有融合编码的 user 后,遍历所有没有融合编码的 user,将他们的 deviceNo 单独建立 list 存入 deviceList 中。
        userList.stream().forEach(x -> {
            List<DeviceDTO> list = new ArrayList<>();
            if (x.getMixNo()==null || x.getMixNo().isEmpty()){
                if (x.getDeviceNo()!=null && !x.getDeviceNo().equals("")){
                    DeviceDTO deviceDTO = new DeviceDTO();
                    deviceDTO.setDevice_type(x.getBusinessType());
                    deviceDTO.setDeviceNo(x.getDeviceNo());
                    list.add(deviceDTO);
                    DeviceSort(list);
                    deviceList.add(list);
                }
            }
        });

        customerDTO.setArrDeviceNoList(deviceList);

        return customerDTO;
    }


    /**
     * 终端搜索查询
     *
     */
    @Override
    public List<List<String>> findDeviceSearchByCustomerId(String customerId, String keyword){

        CustomerDTO customerDTO = new CustomerDTO();
        Customer customer = customerDao.findByCustomerId(customerId);

        // 查找此 customerId 所有的 user, 如果 user 不为null或空,则取第一个 address
        List<User> userList = userDao.findByCustomerId(customerId);

        // 创建一个不可重复的 Set 集合,存放此 customer 的所有的融合编码
        Set<String> mixSet = new HashSet<>();
        for (User user : userList){
            if (user.getMixNo() != null && !user.getMixNo().isEmpty())
                mixSet.add(user.getMixNo());
        }

        // 创建一个二维数组存放deviceNo
        List<List<String>> deviceList = new ArrayList<>(new ArrayList<>());

        // 遍历融合编码的数组,遍历每一个融合编码的用户,找到两个融合编码相同的 deviceNo,将其拼装成 list,添加到 deviceList 中。
        mixSet.stream().forEach(x -> {
            List<String> list = new ArrayList<>();
            for (User user : userList){
                if (user.getMixNo()!=null && !user.getMixNo().isEmpty())
                    if (user.getMixNo().equals(x)) list.add(user.getDeviceNo());
            }
            deviceList.add(list);
        });

        // 遍历完所有有融合编码的 user 后,遍历所有没有融合编码的 user,将他们的 deviceNo 单独建立 list 存入 deviceList 中。
        userList.stream().forEach(x -> {
            List<String> list = new ArrayList<>();
            if (x.getMixNo()==null || x.getMixNo().isEmpty()){
                if (x.getDeviceNo()!=null && !x.getDeviceNo().equals("")){
                    list.add(x.getDeviceNo());
                    deviceList.add(list);
                }
            }
        });

        List<List<String>> returnDeviceList = new ArrayList<>();
        for (List<String> stringList : deviceList){
            if (stringList.size()>1){
                if (stringList.get(0).contains(keyword) || stringList.get(1).contains(keyword)){
                    stringList.get(0).replace(keyword, "<em>" + keyword + "</em>");
                    stringList.get(1).replace(keyword, "<em>" + keyword + "</em>");
                    returnDeviceList.add(stringList);
                }
            }else if (stringList.size()==1){
                if (stringList.get(0).contains(keyword)){
                    stringList.get(0).replace(keyword, "<em>" + keyword + "</em>");
                    returnDeviceList.add(stringList);
                }
            }
        }

        return returnDeviceList;
    }



    /**
     * 通过 deviceNo 查询 DeviceBasicInfoDTO
     * @param deviceNo
     * @return
     */
    @Override
    public DeviceBasicInfoDTO findDeviceBasicInfoDTO(String customerId, String deviceNo) {
        User user = userDao.findByCustomerIdAndDeviceNo(customerId, deviceNo);

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
        List<WatchAction> watchActionList = watchActionDao.findJoinWatchActionAndUser(customerId, deviceNo);
        DeviceWatchActionDTO deviceWatchActionDTO = WatchAction2DeviceWatchActionDTOConvert.convert(watchActionList);

        return deviceWatchActionDTO;
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
        WorkForm workFormTemp = workFormDao.findJoinWorkFormAndUserDetail(customerId, deviceNo, id);

        return WorkForm2DeviceWorkOrderDetailDTOConvert.convert(workFormTemp);
    }

    /**
     * 通过 customerId 和 deviceNo 和 id 查询投诉单详情信息
     * @param customerId
     * @param deviceNo
     * @return
     */
    @Override
    public DeviceComplaintDetailDTO findDeviceComplaintDetailDTO(String customerId, String deviceNo, String id) {
        Complaint Complaint = complaintDao.findJoinComplaintAndUserDetail(customerId, deviceNo, id);
        DeviceComplaintDetailDTO deviceComplaintDetailDTO = new DeviceComplaintDetailDTO();
        List<DealWithMessageDTO> dealWithMessageDTOList = new ArrayList<>();
        ComplaintDTO complaintDTO = new ComplaintDTO();

        // fk2d546781 投诉编码
        complaintDTO.setOrderId(Complaint.getComplaintId());
        // fk0b5c4bd1 受理时间
        complaintDTO.setAcceptTime(Complaint.getAcceptTime());
        // fkee9a3c22 投诉来源
        complaintDTO.setComplaintFrom(Complaint.getComplaintForm());
        // fk25c3d8d3 结单时间
        complaintDTO.setEndTime(Complaint.getOverTime());
        // fk7fadbec0 处理方式分类一 fk14d9b227 处理方式分类二
        complaintDTO.setDealWithWay(Complaint.getProcessMode1() + "/" + Complaint.getProcessMode2());
        // fkcf8c69a6 投诉单内容

        // TODO

        deviceComplaintDetailDTO.setComplaint(complaintDTO);
        deviceComplaintDetailDTO.setDealWithMessage(dealWithMessageDTOList);
        return null;
    }



    /** 滑动 */
    @Override
    public Page<DeviceBusinessInfoDTO> findBusinessSlide(String customerId, String deviceNo, Pageable pageable) {
        Page<UserProduct> userProductPage = userProductDao.findJoinUserAndUserProduct(customerId, deviceNo, pageable);
        return UserProduct2DeviceBusinessInfoDTOConvert.convert(userProductPage);
    }

    @Override
    public Page<DeviceWorkOrderDTO> findWorkOrderSlide(String customerId, String deviceNo, Pageable pageable) {
        Page<WorkForm> workFormPage = workFormDao.findJoinWorkFormAndUser(customerId, deviceNo, pageable);
        return WorkForm2DeviceWorkOrderDTOConvert.convert(workFormPage);
    }

    @Override
    public Page<DeviceComplaintDTO> findComplaintSlide(String customerId, String deviceNo, Pageable pageable) {
        Page<Complaint> complaintPage = complaintDao.findJoinComplaintAndUser(customerId, deviceNo, pageable);
        return Complaint2DeviceComplaintDTOConvert.convert(complaintPage);
    }


    /**
     * 查找客户标签
     *
     * @param customerId
     * @return
     */
    @Override
    public List<UserLabelShowDTO> findUserLabelShowDTO(String customerId){
        List<UserLabel> userLabelList = userLabelDao.findByCustomerIdOrderByCreateTimeDesc(customerId);

        // 将 UserLabel 转化为 UserLabelShowDTO
        List<UserLabelShowDTO> userLabelShowDTOList =
                UserLabel2UserLabelShowDTOConvert.convert(userLabelList);

        return userLabelShowDTOList;
    }

    /**
     * 查找客户标签最后一条
     *
     * @param customerId
     * @return
     */
    @Override
    public ResultVO findUserLabelLastRecord(String customerId){
        List<UserLabel> userLabelList = userLabelDao.findByCustomerIdOrderByCreateTimeDesc(customerId);
        if (userLabelList.size()>0) {
            UserLabel userLabel = userLabelList.get(0);
            UserLabelForm userLabelForm = UserLabel2UserLabelFormConvert.convert(userLabel);
            return ResultVOUtil.success(userLabelForm);
        }else {
            return ResultVOUtil.success(new UserLabelForm());
        }

    }

    /**
     * 插入客户标签
     */
    @Override
    public ResultVO saveUserLabel(UserRoleDTO userRoleDTO, UserLabelForm userLabelForm, String date){

        log.info("【客户标签编辑】用户身份:" + userRoleDTO.getUserRole());
        if (userRoleDTO.getUserRole().equals("公司领导")){

            log.error("【客户标签编辑】此身份无用户标签编辑权限");
            return ResultVOUtil.error(ResultEnum.USER_HAVE_NOT_PRIVILEGE.getCode(),
                    "此身份无用户标签编辑权限");
        }else if (userRoleDTO.getUserRole().equals("业务部门") ||
                userRoleDTO.getUserRole().equals("站长") ||
                userRoleDTO.getUserRole().equals("支撑网格员") ||
                userRoleDTO.getUserRole().equals("基础网格员") ||
                userRoleDTO.getUserRole().equals("管理员")){

            UserLabel userLabel = UserLabelForm2UserLabelConvert.convert(userLabelForm);
            userLabel.setCreateTime(date);

            userLabelDao.save(userLabel);
            log.info("【写入成功】客户标签添加成功.");
            return ResultVOUtil.success();

        }else {
            log.info("【客户标签编辑】无用户身份,默认身份网格员");
            UserLabel userLabel = UserLabelForm2UserLabelConvert.convert(userLabelForm);
            userLabel.setCreateTime(date);

            userLabelDao.save(userLabel);
            log.info("【写入成功】客户标签添加成功.");
            return ResultVOUtil.success();
        }

    }


    /**
     * 查询浏览记录
     */
    @Override
    public List<BrowseRecord> findBrowseRecord(String userId){

        return browseRecordDao.findByUserIdAndTypeOrderByCreateTimeDesc(userId, "user");
    }

    /**
     * 保存浏览记录
     */
    @Override
    public void saveBrowseRecord(String userId, String valueId, String valueName){
        BrowseRecord browseRecord = browseRecordDao.findByUserIdAndTypeAndValueId(userId, "user", valueId);
        if (browseRecord==null) {
            browseRecord = new BrowseRecord();
            browseRecord.setUserId(userId);
            browseRecord.setType("user");
            browseRecord.setValueId(valueId);
            browseRecord.setValueName(valueName);
            browseRecord.setCreateTime(FMT.format(new Date()));
        }else {
            browseRecord.setCreateTime(FMT.format(new Date()));
        }

        browseRecordDao.save(browseRecord);
    }

}
