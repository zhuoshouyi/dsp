package com.topway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by haizhi on 2019/6/5.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankListFilterDTO {

    private String branch = "";

    private String station = "";
}
