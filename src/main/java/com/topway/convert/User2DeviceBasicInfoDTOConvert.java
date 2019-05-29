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
        deviceBasicInfoDTO.setDeviceNo(user.getFkdf1e945e());
        // fk98d0d937 型号
        deviceBasicInfoDTO.setModel(user.getFk98d0d937());
        // fk7eb032bb 品牌
        deviceBasicInfoDTO.setBrand(user.getFk7eb032bb());
        // fkb57a3e00 数据日期
        deviceBasicInfoDTO.setLastUpdateTime(user.getFkb57a3e00());
        // fk1d06e6a7 网格名称
        deviceBasicInfoDTO.setGrid(user.getFk1d06e6a7());
        // fk4fb2b37b 机房名称_小区
        deviceBasicInfoDTO.setComputerRoom(user.getFk4fb2b37b());
        // fkb1affa87 账户银行名称, fk53c01bbd 账户银行账号
        deviceBasicInfoDTO.setCertificates(
                (user.getFkb1affa87()==null?"":user.getFkb1affa87())
                        + "(卡号后四位为"
                        + ((user.getFk53c01bbd()==null)?"":(user.getFk53c01bbd().substring(user.getFk53c01bbd().length()-4)))
                        + ")");
        // fkc398514b 地址
        deviceBasicInfoDTO.setAddress(user.getFkc398514b());
        return deviceBasicInfoDTO;
    }
}
