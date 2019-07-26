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
        userLabelForm.setCustomerType(userLabel.getCustomerType());
        userLabelForm.setIsHaveChildren(userLabel.getIsHaveChildren());
        userLabelForm.setIsHaveElderly(userLabel.getIsHaveElderly());
        userLabelForm.setIsUseOtherProduct(userLabel.getIsUseOtherProduct());
        userLabelForm.setWatchInterest(
                Arrays.stream(userLabel.getWatchInterest().split(",")).collect(Collectors.toList()));
        userLabelForm.setCustomerCharacteristic(
                Arrays.stream(userLabel.getCustomerCharacteristic().split(",")).collect(Collectors.toList()));
        userLabelForm.setCommunicationType(userLabel.getCommunicationType());
        userLabelForm.setCustomerFileds(
                Arrays.stream(userLabel.getCustomerFileds().split(",")).collect(Collectors.toList()));

        return userLabelForm;

    }
}
