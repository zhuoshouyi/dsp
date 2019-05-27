package com.topway.convert;

import com.topway.dto.DeviceBusinessInfoDetailDTO;
import com.topway.pojo.UserProduct;

/**
 * Created by haizhi on 2019/5/27.
 */
public class UserProduct2DeviceBusinessInfoDetailDTOConvert {

    public static DeviceBusinessInfoDetailDTO convert(UserProduct userProduct){
        DeviceBusinessInfoDetailDTO deviceBusinessInfoDetailDTO = new DeviceBusinessInfoDetailDTO();
        // fkb7939445 产品编码
        deviceBusinessInfoDetailDTO.setProductId(userProduct.getFkb7939445());
        // fk795b247c 产品名称
        deviceBusinessInfoDetailDTO.setProductName(userProduct.getFk795b247c());
        // fkef7330c8 订购价格
        deviceBusinessInfoDetailDTO.setPurchasePrice(userProduct.getFkef7330c8());
        // fke9201422 使用时长
        deviceBusinessInfoDetailDTO.setPurchaseLength(userProduct.getFke9201422());
        // fk477a0abf 订购时间
        deviceBusinessInfoDetailDTO.setPurchaseTime(userProduct.getFk477a0abf());
        // fk918097a6 授权开始时间
        deviceBusinessInfoDetailDTO.setOpenTime(userProduct.getFk918097a6());
        // fk313044e4 授权到期时间
        deviceBusinessInfoDetailDTO.setDueTime(userProduct.getFk313044e4());
        // fkb8be2a22 订购状态
        deviceBusinessInfoDetailDTO.setStatus(userProduct.getFkb8be2a22());
        // 经营范围
        deviceBusinessInfoDetailDTO.setAuthorizedArea("");
        // fk928d6ed0 受理渠道 授权区域
        deviceBusinessInfoDetailDTO.setBusinessScope(userProduct.getFk928d6ed0());
        // fke127b206 计费标准
        deviceBusinessInfoDetailDTO.setBillingMode(userProduct.getFke127b206());
        return deviceBusinessInfoDetailDTO;

    }


}
