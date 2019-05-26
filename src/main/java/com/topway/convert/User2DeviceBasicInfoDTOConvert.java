package com.topway.convert;

import com.topway.dto.DeviceBasicInfoDTO;
import com.topway.pojo.User;

/**
 * Created by haizhi on 2019/5/26.
 */
public class User2DeviceBasicInfoDTOConvert {

    public static DeviceBasicInfoDTO convert(User user){
        DeviceBasicInfoDTO deviceBasicInfoDTO = new DeviceBasicInfoDTO();
        // fkce29e60a 资源号
        deviceBasicInfoDTO.setDeviceNo(user.getFkce29e60a());
        // fk99f571d7 model
        deviceBasicInfoDTO.setModel(user.getFk99f571d7());
        // fk06da4f4a BRANDNAME
        deviceBasicInfoDTO.setBrand(user.getFk06da4f4a());
        // fk77884c42 数据日期
        deviceBasicInfoDTO.setLastUpdateTime(user.getFk77884c42());
        // fk9354d659 网格名称
        deviceBasicInfoDTO.setGrid(user.getFk9354d659());
        // fkee660ed3 机房名称
        deviceBasicInfoDTO.setComputerRoom(user.getFkee660ed3());
        //
        deviceBasicInfoDTO.setCertificates("");
        // fka7e6d50f 安装地址
        deviceBasicInfoDTO.setAddress(user.getFka7e6d50f());
        return deviceBasicInfoDTO;
    }
}
