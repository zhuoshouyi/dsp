package com.topway.service;

import com.topway.VO.ResultVO;
import com.topway.dto.AreaBusinessDTO;
import com.topway.dto.AreaLabelShowDTO;
import com.topway.dto.AreaMonthlyDevelopmentDTO;
import com.topway.dto.UserRoleDTO;
import com.topway.form.AreaLabelForm;
import com.topway.form.HistoryMarketForm;
import com.topway.form.PropertyForm;
import com.topway.pojo.Area;
import com.topway.pojo.AreaLabel;
import com.topway.pojo.HistoryMarket;
import com.topway.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by haizhi on 2019/5/22.
 */
@Service
public interface AreaService {

    List<Area> findByAreaList();

    Area findByAreaId(String areaId, UserRoleDTO userRoleDTO);

    Page<Area> findByAreaNameLike(String areaName, UserRoleDTO userRoleDTO, Pageable pageable);

    Property findProperty(String areaId);

    ResultVO saveProperty(UserRoleDTO userRoleDTO, Property property, PropertyForm propertyForm, Integer historyMarketNum);

    AreaBusinessDTO calAreaBusiness(String areaId);

    AreaMonthlyDevelopmentDTO calMonthlyDevelopment(String areaId);

    List<AreaLabelShowDTO> findAreaLabelShow(String areaId);

    List<AreaLabel> findAreaLabel(String areaId);

    ResultVO saveAreaLabel(UserRoleDTO userRoleDTO, AreaLabel areaLabel, AreaLabelForm areaLabelForm);

    Page<HistoryMarket> findHistoryMarket(String areaId, Pageable pageable);

    ResultVO saveHistoryMarket(UserRoleDTO userRoleDTO, HistoryMarketForm historyMarketForm, String date);

}
