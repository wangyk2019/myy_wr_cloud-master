package com.moyuaninfo.suggest.service;

import com.moyuaninfo.suggest.dto.SuggestListDto;
import com.moyuaninfo.suggest.dto.SuggestSolveDto;
import com.moyuaninfo.suggest.model.MyySuggest;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SuggestService
 * @Description 咨询建议
 * @Author zhaoGq
 * @Date 2019/11/1 10:30
 * @Version 1.0
 **/
public interface SuggestService {

    /*
     * @Description 获取河道列表
     * @Author zhaoGq
     * @Date 2019/11/1 11:13
     * @Param
     * @param districtId
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getRiverList(Integer districtId);

    /* @Description 新增咨询建议
     * @Author zhaoGq
     * @Date 2019/11/1 13:49
     * @Param
     * @param myySuggest
     * @Return int
     **/
    int addSuggest(MyySuggest myySuggest);

    /*
     * @Description 根据用户角色查看咨询建议列表
     * @Author zhaoGq
     * @Date 2019/11/1 15:47
     * @Param
     * @param suggestListDto
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getSuggestListByUserRole(SuggestListDto suggestListDto);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/23 14:53
     * @Param
     * @param suggestListDto
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getSuggestListByUserId(SuggestListDto suggestListDto);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/23 15:09
     * @Param
     * @param suggestId
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, Object> getSuggestDetailBySuggestId(Long suggestId);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/23 15:40
     * @Param
     * @param suggestListDto
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getSuggestListBySearch(SuggestListDto suggestListDto);

    /*
     * @Description 管理员回复处理建议
     * @Author zhaoGq
     * @Date 2019/11/1 16:20
     * @Param
     * @param suggestSolveDto
     * @Return int
     **/
    int updateSuggestResult(SuggestSolveDto suggestSolveDto);

    /*
     * @Description 管理员删除咨询建议
     * @Author zhaoGq
     * @Date 2019/11/1 16:34
     * @Param
     * @param createUser
     * @param suggestIds
     * @Return int
     **/
    int deleteSuggest(Long createUser, Long[] suggestIds);

    String getDistrictInfo(Long districtId);


}


