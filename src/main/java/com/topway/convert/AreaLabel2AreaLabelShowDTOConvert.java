package com.topway.convert;

import com.topway.dto.AreaLabelShowDTO;
import com.topway.pojo.AreaLabel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by haizhi on 2019/6/18.
 */
public class AreaLabel2AreaLabelShowDTOConvert {
    
    public static AreaLabelShowDTO convert(AreaLabel areaLabel){
        AreaLabelShowDTO areaLabelShowDTO = new AreaLabelShowDTO();
        areaLabelShowDTO.setDate(areaLabel.getCreateTime());
        List<String> labelList = new ArrayList<>();
        if (areaLabel.getBuildAttrbute()!=null && !areaLabel.getBuildAttrbute().equals(""))
            labelList.add(areaLabel.getBuildAttrbute());
        if (areaLabel.getAreaLiveProportion()!=null && !areaLabel.getAreaLiveProportion().equals(""))
            labelList.add("小区自住比例:" + areaLabel.getAreaLiveProportion());
        if (areaLabel.getIsContractArea()!=null && !areaLabel.getIsContractArea().equals(""))
            labelList.add(areaLabel.getIsContractArea().equals("1") ? "合同小区" : "非合同小区");
        if (areaLabel.getIsPermittedAdmission()!=null && !areaLabel.getIsPermittedAdmission().equals(""))
            labelList.add(areaLabel.getIsPermittedAdmission().equals("1") ? "可进场小区" : "不可进场小区");
        if (areaLabel.getIsCompeteArea()!=null && !areaLabel.getIsCompeteArea().equals(""))
            labelList.add(areaLabel.getIsCompeteArea().equals("1") ? "竞争小区" : "非竞争小区");
        if (areaLabel.getIsRegularCover()!=null && !areaLabel.getIsRegularCover().equals(""))
            labelList.add(areaLabel.getIsRegularCover().equals("1") ? "正规覆盖" : "非正规覆盖");
        if (areaLabel.getNetworkCoverageProperties()!=null && !areaLabel.getNetworkCoverageProperties().equals(""))
            labelList.add(areaLabel.getNetworkCoverageProperties());
        if (areaLabel.getIsStabilityLiver()!=null && !areaLabel.getIsStabilityLiver().equals(""))
            labelList.add(areaLabel.getIsStabilityLiver().equals("1") ? "小区住户稳定" : "小区住户不稳定");
        if (areaLabel.getCustomFields()!=null && !areaLabel.getCustomFields().equals("")) {
            Arrays.stream(areaLabel.getCustomFields().split(",")).forEach(e -> labelList.add(e));
        }

        areaLabelShowDTO.setLabelList(labelList);

        return areaLabelShowDTO;  
    }

    public static List<AreaLabelShowDTO> convert(List<AreaLabel> areaLabelList){
        List<AreaLabelShowDTO> areaLabelShowDTOList =
            areaLabelList.stream().map(e -> convert(e)).collect(Collectors.toList());

        return areaLabelShowDTOList;
    }
}
