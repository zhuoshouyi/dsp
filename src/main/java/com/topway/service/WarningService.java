package com.topway.service;

import com.topway.dto.UserRoleDTO;
import com.topway.form.LoginForm;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haizhi on 2019/6/25.
 */
@Service
public interface WarningService {

    List<Double> WatchLossNumAndWbLossNum(UserRoleDTO userRoleDTO);

    Double InstallSuccess24h(UserRoleDTO userRoleDTO);

    Double MarketEffectNum(UserRoleDTO userRoleDTO);

    Double RegionAverageNum(UserRoleDTO userRoleDTO);

    Double RegionFaultSuccess(UserRoleDTO userRoleDTO);

    UserRoleDTO authentication(LoginForm loginForm);
}
