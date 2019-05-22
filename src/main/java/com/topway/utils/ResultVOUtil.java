package com.topway.utils;

import com.topway.VO.ResultVO;
import com.topway.dto.MsgDTO;

/**
 * Created by haizhi on 2019/5/10.
 */
public class ResultVOUtil {

    /**
     * 带payload信息的成功返回
     * @param object  payload内容
     * @return
     */
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setSuccess(true);
        resultVO.setPayload(object);
        return resultVO;
    }


    /**
     * 不带信息的成功返回
     * @return
     */
    public static ResultVO success(){
        return success(null);
    }


    /**
     * 带错误信息的失败返回
     * @param code  错误代码
     * @param desc  错误信息
     * @return
     */
    public static ResultVO error(Integer code, String desc){
        ResultVO resultVO = new ResultVO();
        MsgDTO msgDTO = new MsgDTO(code, desc);
        resultVO.setSuccess(false);
        resultVO.setMessage(msgDTO);
        return resultVO;
    }

    /**
     * 带page和payload的成功返回
     * @param object  payload内容
     * @param pageNo  显示第几页的数据
     * @param pagePage  每页显示多少条数据
     * @return
     */
    public static ResultVO successPage(Object object, Integer pageNo, Integer pagePage, Long total){
        ResultVO resultVO = new ResultVO();
        resultVO.setSuccess(true);
        resultVO.setPayload(object);
        resultVO.setPageNo(pageNo);
        resultVO.setPagePage(pagePage);
        resultVO.setTotal(total);
        return resultVO;
    }




}
