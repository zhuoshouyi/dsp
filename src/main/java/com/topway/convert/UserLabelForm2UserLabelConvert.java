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
        userLabel.setCustomType(userLabelForm.getCustomType());
        userLabel.setIsHaveChildren(userLabelForm.getIsHaveChildren());
        userLabel.setIsHaveElderly(userLabelForm.getIsHaveElderly());
        userLabel.setIsUseOtherProduct(userLabelForm.getIsUseOtherProduct());
        userLabel.setWatchInterest(StringUtils.strip(userLabelForm.getWatchInterest().toString(),"[]"));
        userLabel.setCustomCharacteristic(StringUtils.strip(userLabelForm.getCustomCharacteristic().toString(),"[]"));
        userLabel.setCommunicationType(userLabelForm.getCommunicationType());
        userLabel.setCustomFileds(StringUtils.strip(userLabelForm.getCustomFileds().toString(),"[]"));

        return userLabel;
    }
}
