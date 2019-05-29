package com.topway.VO;

import com.topway.dto.MsgDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * http请求返回的最外层对象
 * Created by haizhi on 2019/5/7.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    /** 成功标志. */
    private boolean success;

    /** 提示信息. */
    private MsgDTO message;

    /** 具体内容. */
    private T payload;

    /** 第几页. */
    private Integer pageNo;

    /** 每页显示的数量. */
    private Integer pageSize;

    /** 总条数. */
    private Long total;
}
