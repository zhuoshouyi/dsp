package com.topway.convert;

import com.topway.form.UserLabelForm;
import com.topway.pojo.UserLabel;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by haizhi on 2019/6/20.
 */
public class UserLabel2UserLabelFormConvert {

    public static UserLabelForm convert(UserLabel userLabel){
        UserLabelForm userLabelForm = new UserLabelForm();

        userLabelForm.setCustomerId(userLabel.getCustomerId());
        userLabelForm.setIsSmartTV(userLabel.getIsSmartTV());
        userLabelForm.setIsRenting(userLabel.getIsRenting());
        userLabelForm.setCustomType(userLabel.getCustomType());
        userLabelForm.setIsHaveChildren(userLabel.getIsHaveChildren());
        userLabelForm.setIsHaveElderly(userLabel.getIsHaveElderly());
        userLabelForm.setIsUseOtherProduct(userLabel.getIsUseOtherProduct());
        userLabelForm.setWatchInterest(
                Arrays.stream(userLabel.getWatchInterest().split(",")).collect(Collectors.toList()));
        userLabelForm.setCustomCharacteristic(
                Arrays.stream(userLabel.getCustomCharacteristic().split(",")).collect(Collectors.toList()));
        userLabelForm.setCommunicationType(userLabel.getCommunicationType());
        userLabelForm.setCustomFileds(
                Arrays.stream(userLabel.getCustomFileds().split(",")).collect(Collectors.toList()));

        return userLabelForm;

    }
}
