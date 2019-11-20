package com.topway.convert;

import com.topway.dto.DeviceBasicInfoDTO;
import com.topway.pojo.User;

/**
 * Created by haizhi on 2019/5/26.
 */
public class User2DeviceBasicInfoDTOConvert {

    public static DeviceBasicInfoDTO convert(User user){
        DeviceBasicInfoDTO deviceBasicInfoDTO = new DeviceBasicInfoDTO();
        // fkdf1e945e 资源号
        deviceBasicInfoDTO.setDeviceNo(user.getDeviceNo());
        // fk98d0d937 型号
        deviceBasicInfoDTO.setModel(user.getModel());
        // fk7eb032bb 品牌
        deviceBasicInfoDTO.setBrand(user.getBrand());
        // fkb57a3e00 数据日期
        deviceBasicInfoDTO.setLastUpdateTime(user.getDate());
        // fk1d06e6a7 网格名称
        deviceBasicInfoDTO.setGrid(user.getGrid());
        // fk4fb2b37b 机房名称_小区
        deviceBasicInfoDTO.setComputerRoom(user.getComputer());
        // fkb1affa87 账户银行名称, fk53c01bbd 账户银行账号
        deviceBasicInfoDTO.setCertificates(
                (user.getBankName()==null?"":user.getBankName())
                        + "(卡号后四位为"
                        + ((user.getBankNo()==null)?"":(user.getBankNo().length()>4 ? user.getBankNo().substring(user.getBankNo().length()-4) : ""))
                        + ")");
        // fkc398514b 地址
        deviceBasicInfoDTO.setAddress(user.getAddress());
        return deviceBasicInfoDTO;
    }
}
