package com.topway.convert;

import com.topway.form.UserLabelForm;
import com.topway.pojo.UserLabel;
import org.springframework.beans.BeanUtils;

/**
 * Created by haizhi on 2019/5/29.
 */
public class UserLabelForm2UserLabelConvert {

    public static UserLabel convert(UserLabelForm userLabelForm){
        UserLabel userLabel = new UserLabel();
        BeanUtils.copyProperties(userLabelForm, userLabel);

        return userLabel;
    }
}
