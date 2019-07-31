package com.topway.convert;

import com.topway.dto.DeviceBusinessInfoDetailDTO;
import com.topway.pojo.UserProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haizhi on 2019/5/27.
 */
public class UserProduct2DeviceBusinessInfoDetailDTOConvert {

    public static DeviceBusinessInfoDetailDTO convert(UserProduct userProduct){
        DeviceBusinessInfoDetailDTO deviceBusinessInfoDetailDTO = new DeviceBusinessInfoDetailDTO();
        // fkb7939445 产品编码
        deviceBusinessInfoDetailDTO.setProductId(userProduct.getProductId());
        // fk795b247c 产品名称
        deviceBusinessInfoDetailDTO.setProductName(userProduct.getProductName());
        // fkef7330c8 订购价格
        deviceBusinessInfoDetailDTO.setPurchasePrice(userProduct.getOrderPrice());
        // fke9201422 使用时长
        deviceBusinessInfoDetailDTO.setPurchaseLength(userProduct.getUseTime());
        // fk477a0abf 订购时间
        deviceBusinessInfoDetailDTO.setPurchaseTime(userProduct.getOrderTime());
        // fk918097a6 授权开始时间
        deviceBusinessInfoDetailDTO.setOpenTime(userProduct.getAuthorizationStartTime());
        // fk313044e4 授权到期时间
        deviceBusinessInfoDetailDTO.setDueTime(userProduct.getAuthorizationEndTime());
        // fkb8be2a22 订购状态
        deviceBusinessInfoDetailDTO.setStatus(userProduct.getOrderStatus());
        // 经营范围
        deviceBusinessInfoDetailDTO.setAuthorizedArea("");
        // fk928d6ed0 受理渠道 授权区域
        deviceBusinessInfoDetailDTO.setBusinessScope(userProduct.getAcceptChannel());
        // fke127b206 计费标准
        deviceBusinessInfoDetailDTO.setBillingMode(userProduct.getFreightBasis());
        return deviceBusinessInfoDetailDTO;

    }

    public static List<DeviceBusinessInfoDetailDTO> convert(List<UserProduct> userProductList){

        return userProductList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
    }

    public static Page<DeviceBusinessInfoDetailDTO> convert(Page<UserProduct> userProductPage){
        Pageable pageable = userProductPage.getPageable();
        List<UserProduct> userProductList = userProductPage.getContent();
        List<DeviceBusinessInfoDetailDTO> deviceBusinessInfoDetailDTOList = convert(userProductList);
        Page<DeviceBusinessInfoDetailDTO> deviceBusinessInfoDetailDTOPage =
                new PageImpl(deviceBusinessInfoDetailDTOList, pageable, userProductPage.getTotalElements());


        return deviceBusinessInfoDetailDTOPage;

    }


}
