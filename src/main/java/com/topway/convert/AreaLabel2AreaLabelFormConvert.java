package com.topway.convert;

import com.topway.form.AreaLabelForm;
import com.topway.pojo.AreaLabel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by haizhi on 2019/6/20.
 */
public class AreaLabel2AreaLabelFormConvert {

    public static AreaLabelForm convert(AreaLabel areaLabel){
        AreaLabelForm areaLabelForm = new AreaLabelForm();

        areaLabelForm.setAreaId(areaLabel.getAreaId());
        areaLabelForm.setBuildAttrbute(areaLabel.getBuildAttrbute());
        areaLabelForm.setAreaLiveProportion(areaLabel.getAreaLiveProportion());
        areaLabelForm.setIsContractArea(areaLabel.getIsContractArea());
        areaLabelForm.setIsPermittedAdmission(areaLabel.getIsPermittedAdmission());
        areaLabelForm.setIsCompeteArea(areaLabel.getIsCompeteArea());
        areaLabelForm.setIsRegularCover(areaLabel.getIsRegularCover());
        areaLabelForm.setNetworkCoverageProperties(areaLabel.getNetworkCoverageProperties());
        areaLabelForm.setIsStabilityLiver(areaLabel.getIsStabilityLiver());
        areaLabelForm.setCustomFields(
                areaLabel.getCustomFields()==null ? new ArrayList<>() : Arrays.stream(areaLabel.getCustomFields().split(",")).collect(Collectors.toList())
        );

        return areaLabelForm;
    }
}
