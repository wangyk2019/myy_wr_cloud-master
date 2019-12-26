package com.moyuaninfo.suggest.service.impl;

import com.moyuaninfo.suggest.dto.SuggestListDto;
import com.moyuaninfo.suggest.dto.SuggestSolveDto;
import com.moyuaninfo.suggest.common.Global;
import com.moyuaninfo.suggest.model.MyySuggest;
import com.moyuaninfo.suggest.utils.CosUtil;
import com.moyuaninfo.suggest.dao.SuggestMapper;
import com.moyuaninfo.suggest.service.SuggestService;
import com.moyuaninfo.suggest.utils.DateUtils;
import com.qcloud.cos.COSClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SuggestServiceImpl
 * @Description 咨询建议
 * @Author zhaoGq
 * @Date 2019/11/1 11:03
 * @Version 1.0
 **/
@Service
public class SuggestServiceImpl implements SuggestService {

    @Autowired
    private SuggestMapper suggestMapper;

    /*
     * @Description 获取河道列表
     * @Author zhaoGq
     * @Date 2019/11/1 11:13
     * @Param
     * @param districtId
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public List<Map<String, Object>> getRiverList(Integer districtId) {
        return suggestMapper.getRiverList(districtId);
    }

    /*
     * @Description 新增咨询建议
     * @Author zhaoGq
     * @Date 2019/11/1 14:11
     * @Param
     * @param myySuggest
     * @Return int
     **/
    @Override
    public int addSuggest(MyySuggest myySuggest) {
        myySuggest.setUpdateUser(myySuggest.getCreateUser());
        myySuggest.setCreateTime(DateUtils.getNow());
        myySuggest.setUpdateTime(DateUtils.getNow());
        myySuggest.setStatus("0");
        myySuggest.setState("1");
        myySuggest.setResult("");
        return suggestMapper.addSuggest(myySuggest);
    }

    /*
     * @Description 根据用户角色查看咨询建议列表
     * @Author zhaoGq
     * @Date 2019/11/1 15:48
     * @Param
     * @param suggestListDto
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public List<Map<String, Object>> getSuggestListByUserRole(SuggestListDto suggestListDto) {
        if (StringUtils.isBlank(suggestListDto.getEndTime())) {
            suggestListDto.setEndTime(DateUtils.getSystemDateTime());
        }
        if (suggestListDto.getSuggestType() == null) {
            suggestListDto.setSuggestType(1);
        }
        return suggestMapper.getSuggestListByUserRole(suggestListDto);
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/23 14:55
     * @Param
     * @param suggestListDto
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public List<Map<String, Object>> getSuggestListByUserId(SuggestListDto suggestListDto) {
        return suggestMapper.getSuggestListByUserId(suggestListDto);
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/23 15:09
     * @Param
     * @param suggestId
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Override
    public Map<String, Object> getSuggestDetailBySuggestId(Long suggestId) {
        return suggestMapper.getSuggestDetailBySuggestId(suggestId);
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/23 15:42
     * @Param
     * @param suggestListDto
     * @Return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @Override
    public List<Map<String, Object>> getSuggestListBySearch(SuggestListDto suggestListDto) {
        return suggestMapper.getSuggestListBySearch(suggestListDto);
    }

    /*
     * @Description 管理员回复处理建议
     * @Author zhaoGq
     * @Date 2019/11/1 16:21
     * @Param
     * @param suggestSolveDto
     * @Return int
     **/
    @Override
    public int updateSuggestResult(SuggestSolveDto suggestSolveDto) {
        suggestSolveDto.setUpdateTime(DateUtils.getNow());
        return suggestMapper.updateSuggestResult(suggestSolveDto);
    }

    /*
     * @Description 管理员删除咨询建议
     * @Author zhaoGq
     * @Date 2019/11/1 16:35
     * @Param
     * @param userId
     * @param suggestIds
     * @Return int
     **/
    @Override
    public int deleteSuggest(Long createUser, Long[] suggestIds) {
        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("userId",createUser);
        mapParam.put("suggestIds",suggestIds);
        mapParam.put("updateTime",DateUtils.getNow());
        // 通过咨询建议id查询对应图片（用于对应删除腾讯云图片）
        List<String> suggestPhotoList = suggestMapper.getSuggestPhotoById(mapParam);
        for (String suggestPhotoStr : suggestPhotoList) {
            if (StringUtils.isNotBlank(suggestPhotoStr)) {
                String[] suggestPhotoArray = suggestPhotoStr.split(Global.SUGGESTPHOTOPATH_SPLIT_APPOINT);
                if (suggestPhotoArray != null && suggestPhotoArray.length > 0) {
                    for (String singlePhoto : suggestPhotoArray) {
                        String[] singlePhotoKeyArray = singlePhoto.split(Global.SUGGESTPHOTOPATH_KEY_SPLIT_APPOINT);
                        if (singlePhotoKeyArray != null && singlePhotoKeyArray.length > 0) {
                            String singlePhotoKey = singlePhotoKeyArray[1];
                            CosUtil cosUtil = new CosUtil();
                            COSClient cosclient = cosUtil.getTXcosClient();
                            cosclient.deleteObject(Global.TXCLOUD_BUCKETNAME, singlePhotoKey);
                        }
                    }
                }
            }
        }
        return suggestMapper.deleteSuggest(mapParam);
    }


    @Override
    public String getDistrictInfo(Long districtId) {
        return suggestMapper.getDistrictInfo(districtId);
    }


}


