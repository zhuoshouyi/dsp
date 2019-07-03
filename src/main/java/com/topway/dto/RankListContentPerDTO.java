package com.topway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by haizhi on 2019/7/3.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankListContentPerDTO {

    private String name = "";

    private double value = 0;

    private double per = 0;
}
