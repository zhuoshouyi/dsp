package com.topway.controller;

import com.topway.VO.ResultVO;
import com.topway.convert.AreaConvert;
import com.topway.dto.*;
import com.topway.enums.ResultEnum;
import com.topway.exception.ParamException;
import com.topway.exception.ResultNotFoundException;
import com.topway.form.AreaIdForm;
import com.topway.form.AreaListForm;
import com.topway.pojo.Area;
import com.topway.pojo.Property;
import com.topway.service.Impl.AreaServiceImpl;
import com.topway.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
                AreaListDTO areaListDTO = AreaConvert.convertAreaListDTO(area);
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
                    AreaListDTO areaListDTO = AreaConvert.convertAreaListDTO(area);
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

    @PostMapping("/detail")
    public ResultVO detail(@RequestBody AreaIdForm areaIdForm){

        final String AREAID = areaIdForm.getAreaId();
        AreaDetailDTO areaDetailDTO = new AreaDetailDTO();

        /** 1.根据areaId查询出基本信息 */
        AreaBasicInfoDTO areaBasicInfoDTO = new AreaBasicInfoDTO();
        // TODO 计算天威客户数,覆盖住户数
        Area area = areaService.findByAreaId(AREAID);
        areaBasicInfoDTO = AreaConvert.convertAreaBasicInfoDTO(area, 0, 0);


        /** 2.根据areaId查询出label */
        List<String> label = new ArrayList<>();
        // TODO 关联小区标签表,得到小区标签
        label.add("竞争小区");
        label.add("高端小区");

        /** 3.根据areaId计算出business */
        AreaBusinessDTO areaBusinessDTO = areaService.calAreaBusiness(AREAID);

        /** 4.根据areaId计算出monthlyDevelopment */
        AreaMonthlyDevelopmentDTO areaMonthlyDevelopmentDTO = areaService.calMonthlyDevelopment(AREAID);

        /** 5.根据areaId关联出propertyInfo */
        // TODO 关联小区物业信息表
        Property property = new Property();


        /** 6.将所有信息组装在一起 */
        areaDetailDTO.setBasicInfo(areaBasicInfoDTO);
        areaDetailDTO.setLabel(label);
        areaDetailDTO.setBusiness(areaBusinessDTO);
        areaDetailDTO.setMonthlyDevelopment(areaMonthlyDevelopmentDTO);
        areaDetailDTO.setPropertyInfo(property);

        return ResultVOUtil.success(areaDetailDTO);
    }
}
