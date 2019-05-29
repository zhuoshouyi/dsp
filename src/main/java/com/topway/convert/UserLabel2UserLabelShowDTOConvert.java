package com.topway.convert;

import com.topway.dto.UserLabelShowDTO;
import com.topway.pojo.UserLabel;

import java.util.ArrayList;
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
        // TODO 转换成boolean类型
        if (userLabel.getIsSmartTV()!=null) labelList.add(userLabel.getIsSmartTV());
        if (userLabel.getIsRenting()!=null) labelList.add(userLabel.getIsRenting());
        if (userLabel.getCustomType()!=null) labelList.add(userLabel.getCustomType());
        if (userLabel.getIsHaveChildren()!=null) labelList.add(userLabel.getIsHaveChildren());
        if (userLabel.getIsHaveElderly()!=null) labelList.add(userLabel.getIsHaveElderly());
        if (userLabel.getIsUseOtherProduct()!=null) labelList.add(userLabel.getIsUseOtherProduct());
        if (userLabel.getWatchInterest()!=null) labelList.add(userLabel.getWatchInterest());
        if (userLabel.getCustomCharacteristic()!=null) labelList.add(userLabel.getCustomCharacteristic());
        if (userLabel.getCommunicationType()!=null) labelList.add(userLabel.getCommunicationType());
        if (userLabel.getCustomFileds()!=null) labelList.add(userLabel.getCustomFileds());
        userLabelShowDTO.setLabelList(labelList);

        return userLabelShowDTO;
    }
    
    public static List<UserLabelShowDTO> convert(List<UserLabel> userLabelList){
        List<UserLabelShowDTO> userLabelShowDTOList =
                userLabelList.stream().map(e -> convert(e)).collect(Collectors.toList());
        
        return userLabelShowDTOList;
    }
}
