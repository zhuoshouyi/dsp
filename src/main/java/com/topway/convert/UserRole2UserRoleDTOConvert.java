package com.topway.convert;

import com.topway.dto.UserRoleDTO;
import com.topway.pojo.UserRole;

/**
 * Created by haizhi on 2019/7/11.
 */
public class UserRole2UserRoleDTOConvert {

    public static UserRoleDTO convert(UserRole userRole){
        UserRoleDTO userRoleDTO = new UserRoleDTO();

        userRoleDTO.setUserId(userRole.getUserId());
        userRoleDTO.setUserName(userRole.getUserName());
        userRoleDTO.setOpenId(userRole.getOpenId());
        userRoleDTO.setUserRole(userRole.getUserRole());
        userRoleDTO.setServiceGridId(userRole.getServiceGridId()==null ? null : String2ListConvert.convert(userRole.getServiceGridId()));
        userRoleDTO.setSpcodeId(userRole.getSpcodeId()==null ? null : String2ListConvert.convertTo(userRole.getSpcodeId()));
        userRoleDTO.setBusinessOfficeId(userRole.getBusinessOfficeId()==null ? null : String2ListConvert.convertTo(userRole.getBusinessOfficeId()));

        return userRoleDTO;
    }

}
