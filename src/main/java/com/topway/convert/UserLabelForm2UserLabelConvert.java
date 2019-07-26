package com.topway.convert;

import com.topway.form.UserLabelForm;
import com.topway.pojo.UserLabel;
import org.apache.commons.lang.StringUtils;


/**
 * Created by haizhi on 2019/5/29.
 */
public class UserLabelForm2UserLabelConvert {

    public static UserLabel convert(UserLabelForm userLabelForm){
        UserLabel userLabel = new UserLabel();
        userLabel.setCustomerId(userLabelForm.getCustomerId());
        userLabel.setIsSmartTV(userLabelForm.getIsSmartTV());
        userLabel.setIsRenting(userLabelForm.getIsRenting());
        userLabel.setCustomerType(userLabelForm.getCustomerType());
        userLabel.setIsHaveChildren(userLabelForm.getIsHaveChildren());
        userLabel.setIsHaveElderly(userLabelForm.getIsHaveElderly());
        userLabel.setIsUseOtherProduct(userLabelForm.getIsUseOtherProduct());
        userLabel.setWatchInterest(StringUtils.strip(userLabelForm.getWatchInterest().toString(),"[]"));
        userLabel.setCustomerCharacteristic(StringUtils.strip(userLabelForm.getCustomerCharacteristic().toString(),"[]"));
        userLabel.setCommunicationType(userLabelForm.getCommunicationType());
        userLabel.setCustomerFileds(StringUtils.strip(userLabelForm.getCustomerFileds().toString(),"[]"));

        return userLabel;
    }
}
