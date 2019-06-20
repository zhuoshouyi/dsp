package com.topway.convert;

import com.topway.dto.AreaLabelShowDTO;
import com.topway.pojo.AreaLabel;

import java.util.ArrayList;
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
        if (areaLabel.getBuildAttrbute()!=null) labelList.add(areaLabel.getBuildAttrbute());
        if (areaLabel.getAreaLiveProportion()!=null) labelList.add(areaLabel.getAreaLiveProportion());
        if (areaLabel.getIsContractArea()!=null) labelList.add(areaLabel.getIsContractArea()==1 ? "合同小区" : "非合同小区");
        if (areaLabel.getIsPermittedAdmission()!=null) labelList.add(areaLabel.getIsPermittedAdmission()==1 ? "可进场小区" : "不可进场小区");
        if (areaLabel.getIsCompeteArea()!=null) labelList.add(areaLabel.getIsCompeteArea()==1 ? "竞争小区" : "非竞争小区");
        if (areaLabel.getIsRegularCover()!=null) labelList.add(areaLabel.getIsRegularCover()==1 ? "正规覆盖" : "非正规覆盖");
        if (areaLabel.getNetworkCoverageProperties()!=null) labelList.add(areaLabel.getNetworkCoverageProperties());
        if (areaLabel.getIsStabilityLiver()!=null) labelList.add(areaLabel.getIsStabilityLiver()==1 ? "稳定" : "不稳定");
        // TODO 拆分自定义标签
        if (areaLabel.getCustomFields()!=null) labelList.add(areaLabel.getCustomFields());

        areaLabelShowDTO.setLabelList(labelList);

        return areaLabelShowDTO;  
    }

    public static List<AreaLabelShowDTO> convert(List<AreaLabel> areaLabelList){
        List<AreaLabelShowDTO> areaLabelShowDTOList =
            areaLabelList.stream().map(e -> convert(e)).collect(Collectors.toList());

        return areaLabelShowDTOList;
    }
}
