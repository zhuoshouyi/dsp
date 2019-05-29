package com.topway.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haizhi on 2019/5/29.
 */
@Data
public class AreaLabelShowDTO {

    /** date */
    private String date = "";

    /** labelList */
    private List<String> labelList = new ArrayList<>();

}
