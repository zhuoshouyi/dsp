package com.topway.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/5/24.
 */
@Data
public class DeviceComplaintDetailDTO<T> {

    /** 投诉信息. */
    private T complaint;

    /** 处理信息. */
    private List<T> dealWithMessage = new ArrayList<T>();


}
