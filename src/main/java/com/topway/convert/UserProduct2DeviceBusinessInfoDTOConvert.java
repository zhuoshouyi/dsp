package com.topway.convert;

import com.topway.dto.DeviceBusinessInfoDTO;
import com.topway.pojo.UserProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haizhi on 2019/5/27.
 */
public class UserProduct2DeviceBusinessInfoDTOConvert {

    public static DeviceBusinessInfoDTO convert(UserProduct userProduct){
        DeviceBusinessInfoDTO deviceBusinessInfoDTO = new DeviceBusinessInfoDTO();
        // id
        deviceBusinessInfoDTO.setId(userProduct.getId());
        // fk795b247c 产品名称
        deviceBusinessInfoDTO.setProductName(userProduct.getProductName());
        // fk918097a6 授权开始时间
        deviceBusinessInfoDTO.setOpenTime(userProduct.getAuthorizationStartTime());
        // fkb8be2a22 订购状态
        deviceBusinessInfoDTO.setStatus(userProduct.getOrderStatus());
        return deviceBusinessInfoDTO;

    }

    public static List<DeviceBusinessInfoDTO> convert(List<UserProduct> userProductList){

        return userProductList.stream()
                .map(e -> convert(e))
                .collect(Collectors.toList());
    }

    public static Page<DeviceBusinessInfoDTO> convert(Page<UserProduct> userProductPage){
        Pageable pageable = userProductPage.getPageable();
        List<UserProduct> userProductList = userProductPage.getContent();
        List<DeviceBusinessInfoDTO> deviceBusinessInfoDTOList = convert(userProductList);
        Page<DeviceBusinessInfoDTO> deviceBusinessInfoDTOPage =
                new PageImpl(deviceBusinessInfoDTOList, pageable, userProductPage.getTotalElements());


        return deviceBusinessInfoDTOPage;

    }

}
