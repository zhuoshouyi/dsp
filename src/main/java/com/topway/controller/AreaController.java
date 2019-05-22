package com.topway.controller;

import com.topway.VO.ResultVO;
import com.topway.convert.Area2AreaListDTOConvert;
import com.topway.dto.AreaListDTO;
import com.topway.enums.ResultEnum;
import com.topway.exception.ParamException;
import com.topway.exception.ResultNotFoundException;
import com.topway.form.AreaListForm;
import com.topway.pojo.Area;
import com.topway.service.Impl.AreaServiceImpl;
import com.topway.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by haizhi on 2019/5/20.
 */
@RestController
@RequestMapping("/area")
@Slf4j
public class AreaController {

    @Autowired
    AreaServiceImpl areaService;

    @PostMapping("/list")
    public ResultVO list(@RequestBody AreaListForm areaListForm,
                         BindingResult bindingResult){

        // 参数1,搜索的关键字
        final String KEYWORD = areaListForm.getKeyword();
        // 参数2,查看的页数
        final int PAGENO = areaListForm.getPageNo();
        // 参数3,每页的个数
        final int PAGESIZE = areaListForm.getPageSize();

        // 搜索结果数据总条数
        long total=0L;


        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】keyword不正确,keyword={}", KEYWORD);
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.判断用户是使用"小区编码"搜索的还是使用"小区名称"搜索的 */
        try {
            if (Pattern.matches("^\\w{4}$", KEYWORD)) {
                log.info("匹配结果为小区编码");
                /** 如果是四位数字或字母,则认为是使用小区编码搜索,小区编码不能模糊搜索 */

                // 将查询出来的Area转换成AreaListDTO显示
                Area area = areaService.findByAreaId(KEYWORD);
                AreaListDTO areaListDTO = Area2AreaListDTOConvert.convert(area);
                areaListDTO.setFk999cd340(areaListDTO.getFka9350c89().replace(KEYWORD, "<em>"+KEYWORD+"</em>"));

                return ResultVOUtil.successPage(areaListDTO, 1, 1, total);

            }else {
                /** 否则认为是小区名称,小区名称可以模糊搜索 */
                log.info("匹配结果为小区名称");
                PageRequest pageRequest = new PageRequest(PAGENO, PAGESIZE);

                Page<Area> areaPage = areaService.findByAreaNameLike(KEYWORD, pageRequest);

                /** 3.查询出结果后,拼装json串 */
                List<AreaListDTO> areaListDTOList = new ArrayList<>();
                total = areaPage.getTotalElements();  // 搜索出结果的条数
                for (Area area : areaPage) {
                    // 使用工具类将Area转换成AreaListDTO
                    AreaListDTO areaListDTO = Area2AreaListDTOConvert.convert(area);
                    // 将关键字加上<em>,前端可高亮显示
                    areaListDTO.setFk999cd340(areaListDTO.getFk999cd340().replace(KEYWORD, "<em>"+KEYWORD+"</em>"));
                    areaListDTOList.add(areaListDTO);
                }

                return ResultVOUtil.successPage(areaListDTOList, PAGENO, PAGESIZE, total);
            }

        }catch (Exception e){
            log.error("【结果未找到】未匹配到条件对应的结果");
            throw new ResultNotFoundException(ResultEnum.RESULT_NOT_FOUND.getCode(),
                    ResultEnum.RESULT_NOT_FOUND.getDesc());
        }

    }
}
