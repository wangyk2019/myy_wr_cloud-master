package com.moyuaninfo.suggest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moyuaninfo.suggest.common.Global;
import com.moyuaninfo.suggest.dto.SuggestListDto;
import com.moyuaninfo.suggest.dto.SuggestSolveDto;
import com.moyuaninfo.suggest.feignClient.UserClient;
import com.moyuaninfo.suggest.model.JsonResult;
import com.moyuaninfo.suggest.model.MyySuggest;
import com.moyuaninfo.suggest.model.MyyUser;
import com.moyuaninfo.suggest.service.SuggestService;
import com.moyuaninfo.suggest.service.UserService;
import com.moyuaninfo.suggest.utils.CosUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * @ClassName SuggestController
 * @Description 咨询建议
 * @Author zhaoGq
 * @Date 2019/11/1 10:27
 * @Version 1.0
 **/
@Api(value = "咨询建议",tags="suggest")
@RestController
public class SuggestController {

    private static Logger logger = LoggerFactory.getLogger(SuggestController.class);

    @Autowired
    private SuggestService suggestService;

    @Autowired
    private UserClient userClient;

    @Autowired
    private UserService userService;

    /*
     * @Description 获取河道列表
     * @Author zhaoGq
     * @Date 2019/11/1 13:47
     * @Param
     * @param districtId
     * @Return JsonResult<java.util.List<java.util.Map<java.lang.String,java.lang.Object>>>
     **/
    @ApiOperation(value = "获取河道列表", notes = "获取河道列表")
    @RequestMapping(value = "/getRiverList", method = RequestMethod.POST)
    public JsonResult<List<Map<String,Object>>> getRiverList(
            @ApiParam(value="组织id",required=true) @RequestParam Integer districtId) {
        JsonResult<List<Map<String,Object>>> jsrt = null;
        try {
            List<Map<String,Object>> riverList = suggestService.getRiverList(districtId);
            jsrt = new JsonResult<List<Map<String,Object>>>(0,"ok",riverList);
        } catch (Exception e) {
            jsrt = new JsonResult<List<Map<String,Object>>>(2,e.getClass().getName() + ":" + e.getMessage());
            logger.error("getRiverList:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description 新增咨询建议
     * @Author zhaoGq
     * @Date 2019/11/1 13:48
     * @Param
     * @param request
     * @param myySuggest
     * @Return JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ApiOperation(value = "新增咨询建议", notes = "新增咨询建议")
    @RequestMapping(value = "/addSuggest", method = RequestMethod.POST)
    public JsonResult<Map<String,Object>> addSuggest(HttpServletRequest request
            ,@RequestBody MyySuggest myySuggest) {
        JsonResult<Map<String, Object>> jsrt = null;
        try {
            String userId = request.getHeader("userId");
            myySuggest.setCreateUser(Long.valueOf(StringUtils.isBlank(userId) ? "0" : userId));
            int addResult = suggestService.addSuggest(myySuggest);
            if (addResult != 0) {
                jsrt = new JsonResult<>(0,"ok");
            }else {
                jsrt = new JsonResult<>(1,"error");
            }
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("addSuggest:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description 上传咨询建议拍照图片
     * @Author zhaoGq
     * @Date 2019/11/1 15:26
     * @Param
     * @param files
     * @param areaId
     * @Return JsonResult<java.lang.String>
     **/
    @ApiOperation(value = "上传咨询建议拍照图片", notes = "上传咨询建议拍照图片")
    @RequestMapping(value = "/addUploadFile", method = RequestMethod.POST)
    public JsonResult<List<String>> addUploadFile(
            @ApiParam(value="文件",required=true) @RequestParam MultipartFile[] files
            ,@ApiParam(value="区域河道id",required=true) @RequestParam Integer areaId) {
        JsonResult<List<String>> jsrt = null;
        COSClient cosclient = null;
        try {
            if (files != null && files.length > 0) {
                StringBuffer strBuff = new StringBuffer();
                List<String> resultList = new ArrayList<>();
                //循环获取file数组中得文件
                for (int i = 0; i < files.length; i++) {
                    MultipartFile photo = files[i];
                    String oldFileName = photo.getOriginalFilename();
                    String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
                    String newFileName = new Date().getTime() + eName;
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH) + 1;
                    int day = cal.get(Calendar.DATE);
                    CosUtil cosUtil = new CosUtil();
                    cosclient = cosUtil.getTXcosClient();
                    // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
                    String bucketName = Global.TXCLOUD_BUCKETNAME;
                    // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
                    // 大文件上传请参照 API 文档高级 API 上传
                    File localFile = null;
                    localFile = File.createTempFile("temp", null);
                    photo.transferTo(localFile);
                    // 指定要上传到 COS 上的路径
                    String key = "/" + Global.TXCLOUD_SUGGEST_PREFIX + "/river-" + areaId + "/" + year + "/" + month + "/" + day + "/" + newFileName;
                    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
                    PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
                    String filePath = Global.TXCLOUD_PATH + putObjectRequest.getKey();
                    strBuff.append(filePath).append(Global.SUGGESTPHOTOPATH_SPLIT_APPOINT);
                }
                resultList.add(strBuff.toString());
                jsrt = new JsonResult<List<String>>(0,"ok",resultList);
            }
        } catch (Exception e) {
            jsrt = new JsonResult<List<String>>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("addUploadFile:" + e.getMessage());
        } finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }
        return jsrt;
    }

    /*
     * @Description 根据用户角色查看咨询建议列表
     * @Author zhaoGq
     * @Date 2019/11/1 15:47
     * @Param
     * @param request
     * @param suggestListDto
     * @Return JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ApiOperation(value = "查看咨询建议列表", notes = "根据用户角色查看咨询建议列表")
    @RequestMapping(value = "/getSuggestListByUserRole", method = RequestMethod.POST)
    public JsonResult<PageInfo> getSuggestListByUserRole(HttpServletRequest request,
        @RequestBody SuggestListDto suggestListDto) {
        JsonResult<PageInfo> jsrt = null;
        try {
            String id = request.getHeader("userId");
//            JsonResult<MyyUser> user = userClient.findUser(Long.valueOf(StringUtils.isBlank(id) ? "0" : id));
//            String userRole = "";
//            if (user != null) {
//                userRole = user.getData() == null ? "people" : user.getData().getRole();
//            } else {
//                userRole = "people";
//            }

            // 用户角色
            Map<String, Object> userInfo = getUserInfoByUserId(Long.valueOf(StringUtils.isBlank(id) ? "0" : id));
            String userRole = userInfo.get("userRole").toString();

            int page = suggestListDto.getPage() == null ? 1 : suggestListDto.getPage();
            int rows = suggestListDto.getRows() == null ? 10 : suggestListDto.getRows();
            PageHelper.startPage(page, rows);
            if (Global.GOVERNMENT_ADMIN.equals(userRole) || Global.GOVERNMENT_PEOPLE.equals(userRole)) {
                suggestListDto.setDistrictStatus(true);
            } else if(Global.THE_MASSES.equals(userRole)) {
                suggestListDto.setDistrictStatus(false);
            }
            List<Map<String, Object>> suggestList = suggestService.getSuggestListByUserRole(suggestListDto);
            PageInfo pageInfo = new PageInfo(suggestList);
            jsrt = new JsonResult<PageInfo>(0,"ok",pageInfo);
        } catch (Exception e) {
            jsrt = new JsonResult<PageInfo>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("getSuggestListByUserRole:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/23 15:40
     * @Param
     * @param request
     * @param suggestListDto
     * @Return com.moyuaninfo.suggest.model.JsonResult<com.github.pagehelper.PageInfo>
     **/
    @ApiOperation(value = "查询咨询建议列表", notes = "查询咨询建议列表")
    @RequestMapping(value = "/getSuggestListBySearch", method = RequestMethod.POST)
    public JsonResult<PageInfo> getSuggestListBySearch(HttpServletRequest request,
        @RequestBody SuggestListDto suggestListDto) {
        JsonResult<PageInfo> jsrt = null;
        try {
            String id = request.getHeader("userId");
//            JsonResult<MyyUser> user = userClient.findUser(Long.valueOf(StringUtils.isBlank(id) ? "0" : id));
//            String userRole = "";
//            if (user != null) {
//                userRole = user.getData() == null ? "people" : user.getData().getRole();
//            } else {
//                userRole = "people";
//            }

            // 用户角色
            Map<String, Object> userInfo = getUserInfoByUserId(Long.valueOf(StringUtils.isBlank(id) ? "0" : id));
            String  userRole = userInfo.get("userRole").toString();

            int page = suggestListDto.getPage() == null ? 1 : suggestListDto.getPage();
            int rows = suggestListDto.getRows() == null ? 10 : suggestListDto.getRows();
            PageHelper.startPage(page, rows);
            if (Global.GOVERNMENT_ADMIN.equals(userRole) || Global.GOVERNMENT_PEOPLE.equals(userRole)) {
                suggestListDto.setDistrictStatus(true);
            } else if(Global.THE_MASSES.equals(userRole)) {
                suggestListDto.setDistrictStatus(false);
            }
            List<Map<String, Object>> suggestList = suggestService.getSuggestListBySearch(suggestListDto);
            PageInfo pageInfo = new PageInfo(suggestList);
            jsrt = new JsonResult<PageInfo>(0,"ok",pageInfo);
        } catch (Exception e) {
            jsrt = new JsonResult<PageInfo>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("getSuggestListBySearch:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/23 15:08
     * @Param
     * @param suggestId
     * @Return com.moyuaninfo.suggest.model.JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ApiOperation(value = "查看咨询建议详情", notes = "查看咨询建议详情")
    @RequestMapping(value = "/getSuggestDetailBySuggestId", method = RequestMethod.POST)
    public JsonResult<Map<String, Object>> getSuggestDetailBySuggestId(
            @ApiParam(value="咨询建议id",required=true) @RequestParam Long suggestId) {
        JsonResult<Map<String, Object>> jsrt = null;
        try {
            Map<String, Object> suggestDetail = suggestService.getSuggestDetailBySuggestId(suggestId);
            jsrt = new JsonResult<Map<String, Object>>(0,"ok",suggestDetail);
        } catch (Exception e) {
            jsrt = new JsonResult<Map<String, Object>>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("getSuggestDetailBySuggestId:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description 管理员回复处理建议
     * @Author zhaoGq
     * @Date 2019/11/1 16:28
     * @Param
     * @param request
     * @param suggestSolveDto
     * @Return JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ApiOperation(value = "管理员回复处理建议", notes = "管理员回复处理建议")
    @RequestMapping(value = "/updateSuggestResult", method = RequestMethod.POST)
    public JsonResult<String> updateSuggestResult(HttpServletRequest request,
        @RequestBody SuggestSolveDto suggestSolveDto) {
        JsonResult<String> jsrt = null;
        try {
            String id = request.getHeader("userId");
//            JsonResult<MyyUser> user = userClient.findUser(Long.valueOf(StringUtils.isBlank(id) ? "0" : id));
//            String userRole = "";
//            if (user != null) {
//                userRole = user.getData() == null ? "people" : user.getData().getRole();
//            } else {
//                userRole = "people";
//            }

            // 用户角色
            Map<String, Object> userInfo = getUserInfoByUserId(Long.valueOf(StringUtils.isBlank(id) ? "0" : id));
            String  userRole = userInfo.get("userRole").toString();

            if (!Global.GOVERNMENT_ADMIN.equals(userRole)) {
                jsrt = new JsonResult<String>(0,"ok","无权限");
                return jsrt;
            }
            suggestSolveDto.setUserId(Long.valueOf(StringUtils.isBlank(id) ? "0" : id));
            int updateResult = suggestService.updateSuggestResult(suggestSolveDto);
            if (updateResult != 0) {
                jsrt = new JsonResult<>(0,"ok");
            }else {
                jsrt = new JsonResult<>(1,"error");
            }
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("updateSuggestResult:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description 管理员删除咨询建议
     * @Author zhaoGq
     * @Date 2019/11/1 16:33
     * @Param
     * @param request
     * @param suggestIds
     * @Return JsonResult<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @ApiOperation(value = "管理员删除咨询建议", notes = "管理员删除咨询建议")
    @RequestMapping(value = "/deleteSuggest", method = RequestMethod.POST)
    public JsonResult<String> deleteSuggest(HttpServletRequest request
            ,@ApiParam(value="咨询建议id列表",required=true) @RequestParam Long[] suggestIds) {
        JsonResult<String> jsrt = null;
        try {
            String id = request.getHeader("userId");
//            JsonResult<MyyUser> user = userClient.findUser(Long.valueOf(StringUtils.isBlank(id) ? "0" : id));
//            String userRole = "";
//            if (user != null) {
//                userRole = user.getData() == null ? "people" : user.getData().getRole();
//            } else {
//                userRole = "people";
//            }

            // 用户角色
            Map<String, Object> userInfo = getUserInfoByUserId(Long.valueOf(StringUtils.isBlank(id) ? "0" : id));
            String  userRole = userInfo.get("userRole").toString();

            if (!Global.GOVERNMENT_ADMIN.equals(userRole)) {
                jsrt = new JsonResult<String>(0,"ok","无权限");
                return jsrt;
            }
            Long createUser = Long.valueOf(StringUtils.isBlank(id) ? "0" : id);
            int deleteResult = suggestService.deleteSuggest(createUser,suggestIds);
            if (deleteResult != 0) {
                jsrt = new JsonResult<>(0,"ok");
            }else {
                jsrt = new JsonResult<>(1,"error");
            }
        } catch (Exception e) {
            jsrt = new JsonResult<>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("deleteSuggest:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/23 14:53
     * @Param
     * @param request
     * @param suggestListDto
     * @Return com.moyuaninfo.suggest.model.JsonResult<com.github.pagehelper.PageInfo>
     **/
    @ApiOperation(value = "查看我的咨询列表", notes = "查看我的咨询列表")
    @RequestMapping(value = "/getSuggestListByUserId", method = RequestMethod.POST)
    public JsonResult<PageInfo> getSuggestListByUserId(HttpServletRequest request,
        @RequestBody SuggestListDto suggestListDto) {
        JsonResult<PageInfo> jsrt = null;
        try {
            String userId = request.getHeader("userId");
            suggestListDto.setUserId(Long.valueOf(StringUtils.isBlank(userId) ? "0" : userId));
            int page = suggestListDto.getPage() == null ? 1 : suggestListDto.getPage();
            int rows = suggestListDto.getRows() == null ? 10 : suggestListDto.getRows();
            PageHelper.startPage(page, rows);
            List<Map<String, Object>> suggestList = suggestService.getSuggestListByUserId(suggestListDto);
            PageInfo pageInfo = new PageInfo(suggestList);
            jsrt = new JsonResult<PageInfo>(0,"ok",pageInfo);
        } catch (Exception e) {
            jsrt = new JsonResult<PageInfo>(2, e.getClass().getName() + ":" + e.getMessage());
            logger.error("getSuggestListByUserId:" + e.getMessage());
        }
        return jsrt;
    }

    /*
     * @Description //TODO
     * @Author zhaoGq
     * @Date 2019/12/24 21:01
     * @Param
     * @param userId
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    private Map<String, Object> getUserInfoByUserId (Long userId) {
        return userService.getUserInfoByUserId(userId);
    }

    /*
     * @Description 测试
     * @Author zhaoGq
     * @Date 2019/11/5 15:46
     * @Param
     * @param districtId
     * @Return java.lang.String
     **/
    @ApiOperation(value = "根据组织id获取组织信息", notes = "根据组织id获取组织信息")
    @RequestMapping(value = "/getDistrictInfo", method = RequestMethod.GET)
    public String getDistrictInfo(@RequestParam("districtId") Long districtId) {
        String districtName = suggestService.getDistrictInfo(districtId);
        return districtName;
    }


}


