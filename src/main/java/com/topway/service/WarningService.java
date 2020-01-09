package com.topway.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haizhi on 2019/6/25.
 */
@Service
public interface WarningService {

//    List<Double> WatchLossNumAndWbLossNum(UserRoleDTO userRoleDTO);

    Double InstallSuccess24h(List<String> gridStr, List<String> operatorStr, List<String> branchStr);

//    Double MarketEffectNum(UserRoleDTO userRoleDTO);

    Double RegionAverageNum(List<String> gridStr, List<String> operatorStr, List<String> branchStr);

    Double RegionFaultSuccess(List<String> gridStr, List<String> operatorStr, List<String> branchStr);

//    UserRoleDTO authentication(LoginForm loginForm);
}
