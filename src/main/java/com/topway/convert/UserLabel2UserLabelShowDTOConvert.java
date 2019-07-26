package com.topway.convert;

import com.topway.dto.UserLabelShowDTO;
import com.topway.pojo.UserLabel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haizhi on 2019/5/29.
 */
public class UserLabel2UserLabelShowDTOConvert {
    
    public static UserLabelShowDTO convert(UserLabel userLabel){
        UserLabelShowDTO userLabelShowDTO = new UserLabelShowDTO();
        userLabelShowDTO.setDate(userLabel.getCreateTime().substring(0,10));
        List<String> labelList = new ArrayList<>();

        if (userLabel.getIsSmartTV()!=null && !userLabel.getIsSmartTV().equals(""))
            labelList.add(userLabel.getIsSmartTV()=="1" ? "有智能电视" : "无智能电视");
        if (userLabel.getIsRenting()!=null && !userLabel.getIsRenting().equals(""))
            labelList.add(userLabel.getIsRenting()=="1" ? "自住" : "非自住");
        if (userLabel.getCustomerType()!=null && !userLabel.getCustomerType().equals(""))
            labelList.add(userLabel.getCustomerType());
        if (userLabel.getIsHaveChildren()!=null && !userLabel.getIsHaveChildren().equals(""))
            labelList.add(userLabel.getIsHaveChildren()=="1" ? "有小孩" : "无小孩");
        if (userLabel.getIsHaveElderly()!=null && !userLabel.getIsHaveElderly().equals(""))
            labelList.add(userLabel.getIsHaveElderly()=="1" ? "有老人" : "无老人");
        if (userLabel.getIsUseOtherProduct()!=null && !userLabel.getIsUseOtherProduct().equals(""))
            labelList.add(userLabel.getIsUseOtherProduct()=="1" ? "有其他运营商产品" : "无其他运营商产品");
        if (userLabel.getWatchInterest()!=null && !userLabel.getWatchInterest().equals("")) {
            Arrays.stream(userLabel.getWatchInterest().split(",")).forEach(e -> labelList.add(e));
        }
        if (userLabel.getCustomerCharacteristic()!=null && !userLabel.getCustomerCharacteristic().equals("")) {
            Arrays.stream(userLabel.getCustomerCharacteristic().split(",")).forEach(e -> labelList.add(e));
        }
        if (userLabel.getCommunicationType()!=null && !userLabel.getCommunicationType().equals(""))
            labelList.add(userLabel.getCommunicationType());
        if (userLabel.getCustomerFileds()!=null && !userLabel.getCustomerFileds().equals("")) {
            Arrays.stream(userLabel.getCustomerFileds().split(",")).forEach(e -> labelList.add(e));
        }

        userLabelShowDTO.setLabelList(labelList);

        return userLabelShowDTO;
    }
    
    public static List<UserLabelShowDTO> convert(List<UserLabel> userLabelList){
        List<UserLabelShowDTO> userLabelShowDTOList =
                userLabelList.stream().map(e -> convert(e)).collect(Collectors.toList());
        
        return userLabelShowDTOList;
    }
}
