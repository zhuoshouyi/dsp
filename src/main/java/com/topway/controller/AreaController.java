package com.topway.controller;

import com.topway.VO.ResultVO;
import com.topway.convert.Area2AreaBasicInfoDTOConvert;
import com.topway.convert.Area2AreaListDTOConvert;
import com.topway.dto.*;
import com.topway.enums.ResultEnum;
import com.topway.form.*;
import com.topway.pojo.*;
import com.topway.service.Impl.AreaServiceImpl;
import com.topway.utils.ResultVOUtil;
import com.topway.utils.UserAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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


//    public ResultVO list_all(HttpServletRequest httpServletRequest){
//
//        log.info("【小区清单】-------------------------------------------------------");
//
//        /** 2.识别用户身份,判断权限 */
//        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);
//
//        List<Area> areaList = areaService.findAllByGridId(userRoleDTO);
//
//
//
//    }

    /**
     * 小区搜索接口
     *
     * @return
     */
    @PostMapping("/list")
    public ResultVO list(HttpServletRequest httpServletRequest,
                         @Valid @RequestBody AreaListForm areaListForm,
                         BindingResult bindingResult){

        log.info("【小区列表】-------------------------------------------------------");

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
            log.error("【ERROR】参数错误,keyword不正确,keyword={}", KEYWORD);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.识别用户身份,判断权限 */
        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);

        /** 3.判断用户是使用"小区编码"搜索的还是使用"小区名称"搜索的 */
        try {
            if (Pattern.matches("^(\\w){4}$", KEYWORD)) {
                log.info("【小区搜索】小区编码搜索:" + KEYWORD);
                /** 如果是四位数字或字母,则认为是使用小区编码搜索,小区编码不能模糊搜索 */

                // 将查询出来的Area转换成AreaListDTO显示
                List<AreaListDTO> areaListDTOList = new ArrayList<>();
                Area area = areaService.findByAreaId(KEYWORD, userRoleDTO);
                AreaListDTO areaListDTO = Area2AreaListDTOConvert.convert(area);
                areaListDTO.setAreaId(areaListDTO.getAreaId().replace(KEYWORD, "<em>"+KEYWORD+"</em>"));
                areaListDTOList.add(areaListDTO);

                return ResultVOUtil.successPage(areaListDTOList, 0, 1, 1L);

            }else {
                /** 否则认为是小区名称,小区名称可以模糊搜索 */
                log.info("【小区搜索】小区名称搜索:" + KEYWORD);
                PageRequest pageRequest = new PageRequest(PAGENO, PAGESIZE);

                Page<Area> areaPage = areaService.findByAreaNameLike(KEYWORD, userRoleDTO, pageRequest);

                /** 4.查询出结果后,拼装json串 */
                List<AreaListDTO> areaListDTOList = new ArrayList<>();
                total = areaPage.getTotalElements();  // 搜索出结果的条数
                for (Area area : areaPage) {
                    // 使用工具类将Area转换成AreaListDTO
                    AreaListDTO areaListDTO = Area2AreaListDTOConvert.convert(area);
                    // 将关键字加上<em>,前端可高亮显示
                    areaListDTO.setAreaName(areaListDTO.getAreaName().replace(KEYWORD, "<em>"+KEYWORD+"</em>"));
                    areaListDTOList.add(areaListDTO);
                }

                return ResultVOUtil.successPage(areaListDTOList, PAGENO, PAGESIZE, total);
            }

        }catch (Exception e){
            log.error("【ERROR】结果未找到,未匹配到条件对应的结果");
            return ResultVOUtil.error(ResultEnum.RESULT_NOT_FOUND.getCode(),
                    ResultEnum.RESULT_NOT_FOUND.getDesc());
        }

    }




    /**
     * 小区浏览记录展示接口
     *
     * @return
     */
    @PostMapping("/browseRecord")
    public ResultVO browseRecord(HttpServletRequest httpServletRequest){

        log.info("【小区浏览记录】-------------------------------------------------------");

        /** 1.识别用户身份,判断权限 */
        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);

        /** 2.查询浏览记录 */
        List<BrowseRecord> browseRecordList = areaService.findBrowseRecord(userRoleDTO.getUserId());

        List<BrowseRecordDTO> browseRecordDTOList = new ArrayList<>();
        browseRecordList.stream().forEach(e -> {
            BrowseRecordDTO browseRecordDTO = new BrowseRecordDTO();
            browseRecordDTO.setId(e.getValueId());
            browseRecordDTO.setName(e.getValueName());
            browseRecordDTOList.add(browseRecordDTO);
        });

        return ResultVOUtil.success(browseRecordDTOList);
    }



    /**
     * 小区详情接口
     *
     * @return
     */
    @PostMapping("/detail")
    public ResultVO detail(HttpServletRequest httpServletRequest,
                           @Valid @RequestBody AreaIdForm areaIdForm,
                           BindingResult bindingResult){

        log.info("【小区详情】-------------------------------------------------------");

        final String AREAID = areaIdForm.getAreaId();
        AreaDetailDTO areaDetailDTO = new AreaDetailDTO();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】areaId不正确,areaId={}", AREAID);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.根据areaId查询出基本信息 */
        AreaBasicInfoDTO areaBasicInfoDTO = new AreaBasicInfoDTO();
        // TODO 计算天威客户数,覆盖住户数
        Area area = areaService.findByAreaId(AREAID);
        if (area == null){
            log.info("【ERROR】无法通过AREAID查询到小区,数据库未匹配到此小区");
            return ResultVOUtil.error(ResultEnum.RESULT_NOT_FOUND.getCode(),
                    "数据库未匹配到此小区");
        }
        areaBasicInfoDTO = Area2AreaBasicInfoDTOConvert.convert(area, 0, 0);

        /** 3.识别用户身份,判断权限 */
        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);

        /** 4.根据areaId查询出label */
        log.info("【小区详情】查询小区标签");
        List<String> label = new ArrayList<>();
        List<AreaLabelShowDTO> areaLabelShowDTOList = areaService.findAreaLabelShow(AREAID);
        if (areaLabelShowDTOList.size()>0){
            label = areaLabelShowDTOList.get(0).getLabelList();
        }

        /** 5.根据areaId计算出business */
        log.info("【小区详情】查询小区业务在线情况");
        AreaBusinessDTO areaBusinessDTO = areaService.calAreaBusiness(AREAID);

        /** 6.根据areaId计算出monthlyDevelopment */
        log.info("【小区详情】查询小区终端阅读发展情况");
        AreaMonthlyDevelopmentDTO areaMonthlyDevelopmentDTO = areaService.calMonthlyDevelopment(AREAID);

        /** 7.根据areaId关联出propertyInfo */
        log.info("【小区详情】查询小区物业信息");
        Property property = new Property();
        if (areaService.findProperty(AREAID)!=null)
            property = areaService.findProperty(AREAID);
        PageRequest pageRequest = new PageRequest(0, 100);
        Page<HistoryMarket> historyMarketPage = areaService.findHistoryMarket(AREAID, pageRequest);
        property.setHistoryMarket((int)historyMarketPage.getTotalElements());

        /** 8.将所有信息组装在一起 */
        areaDetailDTO.setBasicInfo(areaBasicInfoDTO);
        areaDetailDTO.setLabel(label);
        areaDetailDTO.setBusiness(areaBusinessDTO);
        areaDetailDTO.setMonthlyDevelopment(areaMonthlyDevelopmentDTO);
        areaDetailDTO.setPropertyInfo(property);

        /** 9.保存浏览记录 */
        areaService.saveBrowseRecord(userRoleDTO.getUserId(), AREAID, areaBasicInfoDTO.getAreaName());

        return ResultVOUtil.success(areaDetailDTO);
    }


    /**
     * 小区标签查看接口
     *
     * @return
     */
    @PostMapping("/label/list")
    public ResultVO labelList(@Valid @RequestBody AreaIdForm areaIdForm,
                              BindingResult bindingResult){

        log.info("【小区标签】-------------------------------------------------------");

        final String AREAID = areaIdForm.getAreaId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】areaId不正确,areaId={}", AREAID);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        List<AreaLabelShowDTO> areaLabelShowDTOList = areaService.findAreaLabelShow(AREAID);

        return ResultVOUtil.success(areaLabelShowDTOList);
    }


    /**
     * 小区标签查询最后一条
     *
     * @param areaIdForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/label/lastrecord")
    public ResultVO userLabelLastRecord(@Valid @RequestBody AreaIdForm areaIdForm,
                                        BindingResult bindingResult){

        log.info("【小区标签查询】-------------------------------------------------------");

        final String AREAID = areaIdForm.getAreaId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】传入的参数有误,AREAID={}", AREAID);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        return areaService.findAreaLabelLastRecord(AREAID);

    }


    /**
     * 小区标签编辑接口
     *
     * @return
     */
    @PostMapping("/label/save")
    public ResultVO labelSave(HttpServletRequest httpServletRequest,
                              @Valid @RequestBody AreaLabelForm areaLabelForm,
                              BindingResult bindingResult){

        log.info("【小区标签编辑】-------------------------------------------------------");

        final String AREAID = areaLabelForm.getAreaId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()){
            log.error("【参数错误】areaId不正确,areaId={}", AREAID);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.识别用户身份,判断权限 */
        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);

        /** 3.更新标签信息 */
        // 查找出现有的标签
        AreaLabel areaLabel = new AreaLabel();

        return areaService.saveAreaLabel(userRoleDTO, areaLabelForm);
    }

    /**
     * 小区物业信息编辑接口
     *
     * @return
     */
    @PostMapping("/property/save")
    public ResultVO propertySave(HttpServletRequest httpServletRequest,
                                 @Valid @RequestBody PropertyForm propertyForm,
                                 BindingResult bindingResult){

        log.info("【小区物业编辑】-------------------------------------------------------");

        final String AREAID = propertyForm.getAreaId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()) {
            log.error("【参数错误】areaId不正确,areaId={}", AREAID);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.识别用户身份,判断权限 */
        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);

        /** 3.更新物业信息 */
        Property property = new Property();
        // 查找数据库中是否有此小区的物业信息
        if (areaService.findProperty(AREAID)!=null) {
            property = areaService.findProperty(AREAID);
        }
        property.setAreaId(AREAID);

        PageRequest pageRequest = new PageRequest(0, 100);
        Page<HistoryMarket> historyMarketPage = areaService.findHistoryMarket(AREAID, pageRequest);
        property.setHistoryMarket(historyMarketPage.getTotalPages());

        return areaService.saveProperty(userRoleDTO, property, propertyForm, historyMarketPage.getTotalPages());
    }




    /**
     * 查看历史营销进场记录接口
     *
     * @return
     */
    @PostMapping("/market/list")
    public ResultVO marketList(HttpServletRequest httpServletRequest,
                               @Valid @RequestBody AreaIdForm areaIdForm,
                               BindingResult bindingResult){

        log.info("【小区营销记录查询】-------------------------------------------------------");

        final String AREAID = areaIdForm.getAreaId();
        final int PAGENO = areaIdForm.getPageNo();
        final int PAGESIZE = areaIdForm.getPageSize();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()) {
            log.error("【参数错误】areaId不正确,areaId={}", AREAID);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.识别用户身份,判断权限 */
        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);

        /** 3.查询历史营销进场记录 */
        PageRequest pageRequest = new PageRequest(PAGENO, PAGESIZE);
        Page<HistoryMarket> historyMarketPage = areaService.findHistoryMarket(AREAID, pageRequest);

        return ResultVOUtil.successPage(historyMarketPage.getContent(), PAGENO, PAGESIZE, historyMarketPage.getTotalElements());
    }

    /**
     * 新建营销进场记录接口
     *
     * @return
     */
    @PostMapping("/market/save")
    public ResultVO marketSave(HttpServletRequest httpServletRequest,
                               @Valid @RequestBody HistoryMarketForm historyMarketForm,
                               BindingResult bindingResult){

        log.info("【小区营销记录编辑】-------------------------------------------------------");

        final String AREAID = historyMarketForm.getAreaId();

        /** 1.校验form表单是否正确 */
        if (bindingResult.hasErrors()) {
            log.error("【参数错误】参数不正确,historyMarketForm={}", historyMarketForm);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        /** 2.识别用户身份,判断权限 */
        UserRoleDTO userRoleDTO = UserAuthentication.authentication(httpServletRequest);

        /** 3.插入历史营销进场记录 */
        String date = FMT.format(new Date());

        return areaService.saveHistoryMarket(userRoleDTO, historyMarketForm, date);
    }

}
