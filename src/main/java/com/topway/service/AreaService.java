package com.topway.service;

import com.topway.dto.AreaBusinessDTO;
import com.topway.dto.AreaLabelShowDTO;
import com.topway.dto.AreaMonthlyDevelopmentDTO;
import com.topway.form.HistoryMarketForm;
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

    Area findByAreaId(String areaId);

    Page<Area> findByAreaNameLike(String areaName, Pageable pageable);

    Property findProperty(String areaId);

    void saveProperty(Property property);

    AreaBusinessDTO calAreaBusiness(String areaId);

    AreaMonthlyDevelopmentDTO calMonthlyDevelopment(String areaId);

    List<AreaLabelShowDTO> findAreaLabel(String areaId);

    void saveAreaLabel(AreaLabel areaLabel);

    Page<HistoryMarket> findHistoryMarket(String areaId, Pageable pageable);

    void saveHistoryMarket(HistoryMarketForm historyMarketForm, String date);

    List<String> findAreaLabelOne(String areaId);
}
