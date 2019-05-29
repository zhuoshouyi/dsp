package com.topway.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户标签
 *
 * Created by haizhi on 2019/5/29.
 */
@Data
public class UserLabelShowDTO {

    private String date = "";

    private List<String> labelList = new ArrayList<>();
}
