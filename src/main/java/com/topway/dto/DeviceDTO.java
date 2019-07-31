package com.topway.dto;

import lombok.Data;

/**
 * Created by haizhi on 2019/7/30.
 */
@Data
public class DeviceDTO {

    /** 排序号 */
    public Integer no;

    /** 资源号 */
    public String deviceNo;

    /** 终端类型 单电视,单宽 */
    public String device_type;

    /** 终端状态 */
    public String status;
}
