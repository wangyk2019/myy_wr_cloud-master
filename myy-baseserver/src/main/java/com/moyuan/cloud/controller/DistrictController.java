package com.moyuan.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.moyuan.cloud.VO.FindAllAreaByParams;
import com.moyuan.cloud.VO.FindAllDistrictByParams;
import com.moyuan.cloud.VO.FindAllIn;
import com.moyuan.cloud.VO.WarninfoOut;
import com.moyuan.cloud.feignClient.CloudNettyClient;
import com.moyuan.cloud.feignClient.DangerClient;
import com.moyuan.cloud.pojo.JsonResult;
import com.moyuan.cloud.pojo.MyyDistrict;
import com.moyuan.cloud.pojo.MyyOrgwarntype;
import com.moyuan.cloud.pojo.MyyWarntype;
import com.moyuan.cloud.service.MyyDistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "组织信息",tags = "组织信息")
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    MyyDistrictService myyDistrictService;
    @Autowired
    DangerClient dangerClient;

    @Autowired
    CloudNettyClient cloudNettyClient;

    protected static Logger log = LogManager.getLogger(DistrictController.class);

    @ApiOperation(value = "添加组织信息",notes = "",httpMethod = "POST")
    @PostMapping(value = "/addDistrict")
    public JsonResult save(@RequestBody @Valid MyyDistrict myyDistrict){
        JsonResult jr = null;
        try {
            myyDistrictService.save(myyDistrict);
            jr=  new JsonResult(0,JsonResult.SUCCESSMSG);
        }catch (Exception e){
            jr=  new JsonResult(1,e.getMessage());
        }
        return jr;
    }

    @ApiOperation(value = "删除组织信息",notes = "",httpMethod = "POST")
    @PostMapping(value = "/deteleDistrict")
    public JsonResult<MyyDistrict> delete(@RequestParam(value = "id") long id){
        JsonResult<MyyDistrict> jsonResult = null;
        try{
            myyDistrictService.updateStateById(id);
            jsonResult = new JsonResult<>(0,JsonResult.SUCCESSMSG);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询组织信息",notes = "id",httpMethod = "POST")
    @PostMapping(value = "/findDistrict")
    public JsonResult<MyyDistrict> findDistrict(@RequestParam(value = "id") long id){

        JsonResult<MyyDistrict> jsonResult = null;
        try {
            MyyDistrict myyDistrict = myyDistrictService.findById(id);
            jsonResult = new JsonResult<>(0,myyDistrict);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "提供给本地服务的组织查询信息",notes = "orgid",httpMethod = "POST")
    @PostMapping(value = "/findDistrictToNetty")
    public JsonResult<MyyDistrict> findDistrictToNetty(@RequestBody String msg){
        JSONObject rceobj = (JSONObject) JSONObject.parse(msg);
        JsonResult<MyyDistrict> jsonResult = null;
        try {
            MyyDistrict myyDistrict = myyDistrictService.findById(rceobj.getLong("orgid"));
            JSONObject map1 = new JSONObject();

            map1.put("client", "org-" + rceobj.getLong("orgid"));
            map1.put("task", "initconstant");
            map1.put("msgtype", "request1");
            map1.put("district",myyDistrict);
            map1.put("api","/constant/init");
            map1.put("apiclient","localservice");
            System.out.println(map1);

//            RestTemplate restTemplate = new RestTemplate();
//            restTemplate.postForEntity("http://127.0.0.1:2222/cloudNetty/sendMsg",map1, JSONObject.class);
            cloudNettyClient.nettySend(map1);


            jsonResult = new JsonResult<>(0,myyDistrict);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询组织信息",notes = "名称",httpMethod = "POST")
    @PostMapping(value = "/findDistricts")
    public JsonResult<List<MyyDistrict>> findAreasByParams(@RequestBody FindAllDistrictByParams findAllDistrictByParams){
        JsonResult<List<MyyDistrict>> jsonResult = null;
        try {
//            if (findAllDistrictByParams.getName() == null){
//                findAllDistrictByParams.setName("");
//            }
            List<MyyDistrict> myyDistricts = myyDistrictService.findDistrictsByParams(findAllDistrictByParams.getName());
            if(myyDistricts.size()==0){
                jsonResult = new JsonResult<>(0,JsonResult.NoDataMSG,myyDistricts);
            }else {
                jsonResult = new JsonResult<>(0,myyDistricts);
            }

        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询组织信息（所有组织）",notes = "名称",httpMethod = "POST")
    @PostMapping(value = "/findAllDistricts")
    public JsonResult<List<MyyDistrict>> findAllDistricts(){
        JsonResult<List<MyyDistrict>> jsonResult = null;
        try {
            List<MyyDistrict> myyDistricts = myyDistrictService.findDistrictsAll();
            if(myyDistricts.size()==0){
                jsonResult = new JsonResult<>(0,JsonResult.NoDataMSG,myyDistricts);
            }else {
                jsonResult = new JsonResult<>(0,myyDistricts);
            }
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "组织添加修改险情")
    @PostMapping(value = "/addOrgWarn")
    public JsonResult<MyyOrgwarntype> addOrgWarn(@RequestBody MyyOrgwarntype myyOrgwarntype){
        JsonResult<MyyOrgwarntype> jsonResult = null;
        try {
            MyyOrgwarntype myyOrgwarntype1 = myyDistrictService.findWarns_OrgidAndWarnid(myyOrgwarntype.getOrgid(),myyOrgwarntype.getWarntypeid());
            if(myyOrgwarntype1 == null){
                myyDistrictService.saveOrgWarn(myyOrgwarntype);
            }else {
                myyOrgwarntype1.setSort(myyOrgwarntype.getSort());
                myyOrgwarntype1.setBelongs(myyOrgwarntype.getBelongs());
                myyOrgwarntype1.setActive(myyOrgwarntype.getActive());
                myyDistrictService.saveOrgWarn(myyOrgwarntype1);
            }
            jsonResult = new JsonResult<>(0);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage()+"");
        }
        return jsonResult;
    }

    @ApiOperation(value = "根据warnen查找险情")
    @PostMapping(value = "/findWarntypeByWarnen")
    public JsonResult<MyyWarntype> findWarntypeByWarnen(@RequestBody FindAllIn findAllIn){
        JsonResult<MyyWarntype> jsonResult = null;
        try {
            MyyWarntype myyWarntype = myyDistrictService.findWarntypeByWarnen(findAllIn.getWarnen());
            jsonResult = new JsonResult<>(0,myyWarntype);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查找所有险情")
    @PostMapping(value = "/findWarnByWarnens")
    public JsonResult<List<MyyWarntype>> findWarnByWarnens(@RequestBody FindAllIn findAllIn){
        JsonResult<List<MyyWarntype>> jsonResult = null;
        try {
            List<MyyWarntype> myyWarntypes = myyDistrictService.findWarnByWarnen(findAllIn.getWarntypeids());
            jsonResult = new JsonResult<>(0,myyWarntypes);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    /**
     * param warntypeid
     */
    @ApiOperation(value = "查找险情类型详细")
    @PostMapping(value = "/findWarnById")
    public JsonResult<MyyWarntype> findWarnById(@RequestBody FindAllIn findAllIn){
        JsonResult<MyyWarntype> jsonResult = null;
        try {
            MyyWarntype myyWarntype = myyDistrictService.findByWarntypeid(findAllIn.getWarntypeid());
            jsonResult = new JsonResult<>(0,myyWarntype);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查找组织下所有险情")
    @PostMapping(value = "/findWarnsByOrgid")
    public JsonResult<List<WarninfoOut>> findWarnsByOrgid(@RequestBody FindAllIn findAllIn){
        JsonResult<List<WarninfoOut>> jsonResult = null;

        List<WarninfoOut> warninfoOuts = new ArrayList<>();
        try {
            List<MyyOrgwarntype> myyWarns = myyDistrictService.findWarns(findAllIn.getOrgId(),findAllIn.getBelongs());
            for (MyyOrgwarntype orgw : myyWarns){
                WarninfoOut warninfoOut = new WarninfoOut();
                MyyWarntype myyWarntype = myyDistrictService.findByWarntypeid(orgw.getWarntypeid());
                warninfoOut.setActive(orgw.getActive());
                warninfoOut.setSort(orgw.getSort());
                if (orgw.getActive()==1){
                    warninfoOut.setWarncorn(myyWarntype.getWarncorn());
                }else {
                    warninfoOut.setWarncorn(myyWarntype.getWarnuncorn());
                }
                warninfoOut.setWarnen(myyWarntype.getWarnen());
                warninfoOut.setWarnname(myyWarntype.getWarnname());
                warninfoOut.setWarntypeid(orgw.getWarntypeid());

                warninfoOuts.add(warninfoOut);
            }
            jsonResult = new JsonResult<>(0,warninfoOuts);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "组织下所有险情排序")
    @PostMapping(value = "/to_sortOrgidWarns")
    public JsonResult to_sortOrgidWarns(@RequestBody FindAllIn findAllIn){
        JsonResult jsonResult = null;

        try {
            if (findAllIn.getUpwarntypeids() != null){
                for (int i=0;i<findAllIn.getUpwarntypeids().size();i++){
                    myyDistrictService.updateOrgwarntypeSort(findAllIn.getOrgId(),findAllIn.getUpwarntypeids().get(i),i+1,1);
                }
            }

            if (findAllIn.getDownwarntypeids() != null){
                for (int i=0;i<findAllIn.getDownwarntypeids().size();i++){
                    myyDistrictService.updateOrgwarntypeSort(findAllIn.getOrgId(),findAllIn.getDownwarntypeids().get(i),i+1,2);
                }
            }

            jsonResult = new JsonResult(0);
        }catch (Exception e){
            jsonResult = new JsonResult(1,e.getMessage());
        }
        return jsonResult;
    }
}
