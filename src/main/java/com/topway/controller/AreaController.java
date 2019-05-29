package com.topway.controller;

import com.topway.VO.ResultVO;
import com.topway.convert.Area2AreaBasicInfoDTOConvert;
import com.topway.convert.Area2AreaListDTOConvert;
import com.topway.dto.*;
import com.topway.enums.ResultEnum;
import com.topway.exception.ParamException;
import com.topway.exception.ResultNotFoundException;
import com.topway.form.*;
import com.topway.pojo.Area;
import com.topway.pojo.HistoryMarket;
import com.topway.pojo.Property;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    // 全局统一时间格式化格式
    SimpleDateFormat FMT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 小区列表接口
     *
     * @return
     */
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
                List<AreaListDTO> areaListDTOList = new ArrayList<>();
                Area area = areaService.findByAreaId(KEYWORD);
                AreaListDTO areaListDTO = Area2AreaListDTOConvert.convert(area);
                areaListDTO.setFk999cd340(areaListDTO.getFka9350c89().replace(KEYWORD, "<em>"+KEYWORD+"</em>"));
                areaListDTOList.add(areaListDTO);

                return ResultVOUtil.successPage(areaListDTOList, 1, 1, 1L);

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

    /**
     * 小区详情接口
     *
     * @return
     */
    @PostMapping("/detail")
    public ResultVO detail(@RequestBody AreaIdForm areaIdForm,
                           BindingResult bindingResult){

        final String AREAID = areaIdForm.getAreaId();
        AreaDetailDTO areaDetailDTO = new AreaDetailDTO();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】areaId不正确,areaId={}", AREAID);
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.根据areaId查询出基本信息 */
        AreaBasicInfoDTO areaBasicInfoDTO = new AreaBasicInfoDTO();
        // TODO 计算天威客户数,覆盖住户数
        Area area = areaService.findByAreaId(AREAID);
        areaBasicInfoDTO = Area2AreaBasicInfoDTOConvert.convert(area, 0, 0);


        /** 3.根据areaId查询出label */
        List<String> label = areaService.findAreaLabelOne(AREAID);

        /** 4.根据areaId计算出business */
        AreaBusinessDTO areaBusinessDTO = areaService.calAreaBusiness(AREAID);

        /** 5.根据areaId计算出monthlyDevelopment */
        AreaMonthlyDevelopmentDTO areaMonthlyDevelopmentDTO = areaService.calMonthlyDevelopment(AREAID);

        /** 6.根据areaId关联出propertyInfo */
        // TODO 关联小区物业信息表
        Property property = new Property();


        /** 7.将所有信息组装在一起 */
        areaDetailDTO.setBasicInfo(areaBasicInfoDTO);
        areaDetailDTO.setLabel(label);
        areaDetailDTO.setBusiness(areaBusinessDTO);
        areaDetailDTO.setMonthlyDevelopment(areaMonthlyDevelopmentDTO);
        areaDetailDTO.setPropertyInfo(property);

        return ResultVOUtil.success(areaDetailDTO);
    }


    /**
     * 小区标签查看接口
     *
     * @return
     */
    @PostMapping("/label/list")
    public ResultVO labelList(@RequestBody AreaIdForm areaIdForm,
                              BindingResult bindingResult){

        final String AREAID = areaIdForm.getAreaId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】areaId不正确,areaId={}", AREAID);
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        List<AreaLabelShowDTO> areaLabelShowDTOList = areaService.findAreaLabel(AREAID);

        return ResultVOUtil.success(areaLabelShowDTOList);
    }

    /**
     * 小区标签编辑接口
     *
     * @return
     */
    @PostMapping("/label/save")
    public ResultVO labelSave(@RequestBody AreaLabelForm areaLabelForm,
                              BindingResult bindingResult){

        final String AREAID = areaLabelForm.getAreaId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】areaId不正确,areaId={}", AREAID);
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.更新标签信息 */
        // TODO 小区标签编辑开发

        return ResultVOUtil.success();
    }

    /**
     * 小区物业信息编辑接口
     *
     * @return
     */
    @PostMapping("/area/property/save")
    public ResultVO propertySave(@RequestBody PropertyForm propertyForm,
                                 BindingResult bindingResult){

        final String AREAID = propertyForm.getAreaId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()) {
            log.error("【参数错误】areaId不正确,areaId={}", AREAID);
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.更新物业信息 */


        // TODO 小区物业信息编辑开发

        return ResultVOUtil.success();
    }




    /**
     * 查看历史营销进场记录接口
     *
     * @return
     */
    @PostMapping("/area/market/list")
    public ResultVO marketList(@RequestBody AreaIdForm areaIdForm,
                               BindingResult bindingResult){

        final String AREAID = areaIdForm.getAreaId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()) {
            log.error("【参数错误】areaId不正确,areaId={}", AREAID);
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.查询历史营销进场记录 */
        List<HistoryMarket> historyMarketList = areaService.findHistoryMarket(AREAID);

        return ResultVOUtil.success(historyMarketList);
    }

    /**
     * 新建营销进场记录接口
     *
     * @return
     */
    @PostMapping("/area/market/save")
    public ResultVO marketSave(HistoryMarketForm historyMarketForm,
                               BindingResult bindingResult){

        final String AREAID = historyMarketForm.getAreaId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()) {
            log.error("【参数错误】参数不正确,historyMarketForm={}", historyMarketForm);
            throw new ParamException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.插入历史营销进场记录 */
        String date = FMT.format(new Date());
        areaService.saveHistoryMarket(historyMarketForm, date);
        log.info("【写入成功】历史营销进场记录数据库写入成功.");

        return ResultVOUtil.success();
    }

}
