package com.moyuaninfo.suggest.dao;

import com.moyuaninfo.suggest.dto.SuggestListDto;
import com.moyuaninfo.suggest.dto.SuggestSolveDto;
import com.moyuaninfo.suggest.model.MyySuggest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SuggestMapper
 * @Description 咨询建议
 * @Author zhaoGq
 * @Date 2019/11/1 11:05
 * @Version 1.0
 **/
@Repository("suggestMapper")
public interface SuggestMapper {

    /*
     * @Description 获取河道列表
     * @Author zhaoGq
     * @Date 2019/11/1 11:13
     * @Param
     * @param districtId
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getRiverList(Integer districtId);

    /*
     * @Description 新增咨询建议
     * @Author zhaoGq
     * @Date 2019/11/1 14:15
     * @Param
     * @param mapParam
     * @Return int
     **/
    int addSuggest(MyySuggest myySuggest);

    /*
     * @Description 根据用户角色查看咨询建议列表
     * @Author zhaoGq
     * @Date 2019/11/1 15:52
     * @Param
     * @param suggestListDto
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getSuggestListByUserRole(SuggestListDto suggestListDto);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/23 14:56
     * @Param
     * @param suggestListDto
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getSuggestListByUserId(SuggestListDto suggestListDto);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/23 15:10
     * @Param
     * @param suggestId
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, Object> getSuggestDetailBySuggestId(Long suggestId);

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/23 15:43
     * @Param
     * @param suggestListDto
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    List<Map<String, Object>> getSuggestListBySearch(SuggestListDto suggestListDto);

    /*
     * @Description 管理员回复处理建议
     * @Author zhaoGq
     * @Date 2019/11/1 16:22
     * @Param
     * @param suggestSolveDto
     * @Return int
     **/
    int updateSuggestResult(SuggestSolveDto suggestSolveDto);

    /*
     * @Description 管理员删除咨询建议
     * @Author zhaoGq
     * @Date 2019/11/1 16:37
     * @Param
     * @param mapParam
     * @Return int
     **/
    int deleteSuggest(Map<String, Object> mapParam);

    /*
     * @Description 通过咨询建议id查询对应图片（用于对应删除腾讯云图片）
     * @Author zhaoGq
     * @Date 2019/11/2 14:16
     * @Param
     * @param mapParam
     * @Return java.util.List<java.lang.String>
     **/
    List<String> getSuggestPhotoById(Map<String, Object> mapParam);

    String getDistrictInfo(@Param("districtId") Long districtId);

}


