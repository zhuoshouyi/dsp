package com.topway.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户对象
 * Created by haizhi on 2019/5/23.
 */
@Data
@NoArgsConstructor
public class CustomerDTO {

    /** 客户名称. */
    private String customerName = "";

    /** 客户编码. */
    private String customerId = "";

    /** 手机号码. */
    private String phone = "";

    /** 身份证号. */
    private String paperNo = "";

    /** 地址:取第一个device的地址. */
    private String address = "";

    /** 业务类型:电视、宽带、融合. */
    private String businessType = "";

    /** 终端列表:列出此客户所有拥有的2个终端,如果有搜索的终端置顶. */
    private List<String> deviceNoList = new ArrayList<>();


    private List<List<DeviceDTO>> arrDeviceNoList = new ArrayList<>();



//    public CustomerDTO(String customerName, String customerId, String phone, String paperNo, String address, String businessType) {
//        this.customerName = customerName;
//        this.customerId = customerId;
//        this.phone = phone;
//        this.paperNo = paperNo;
//        this.address = address;
//        this.businessType = businessType;
//        this.deviceNoList = new ArrayList<>();
//    }
}
