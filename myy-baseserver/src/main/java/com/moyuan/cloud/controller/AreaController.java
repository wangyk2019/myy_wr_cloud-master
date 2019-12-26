package com.moyuan.cloud.controller;

import com.moyuan.cloud.VO.FindAllAreaByParams;
import com.moyuan.cloud.VO.FindAllAreaIn;
import com.moyuan.cloud.VO.PositionOut;
import com.moyuan.cloud.VO.WaterinfoOut;
import com.moyuan.cloud.pojo.*;
import com.moyuan.cloud.service.MyyAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@Api(value = "区域信息",tags = "区域信息")
@RequestMapping("/area")
public class AreaController {
    @Autowired
    MyyAreaService myyAreaService;

    @ApiOperation(value = "添加修改区域一级信息")
    @PostMapping(value = "/addAreaone")
    public JsonResult addAreaone(@RequestBody @Valid MyyAreaone myyAreaone){
        JsonResult jr = null;
        try {
            myyAreaone = myyAreaService.save_one(myyAreaone);
            jr=  new JsonResult(0,JsonResult.SUCCESSMSG);
        }catch (Exception e){
            jr=  new JsonResult(1,e.getMessage());
        }
        return jr;
    }

    @ApiOperation(value = "删除区域一级信息",notes = "",httpMethod = "POST")
    @PostMapping(value = "/deteleAreaone")
    public JsonResult deteleAreaone(@RequestParam(value = "id") long id){
        JsonResult jsonResult = null;
        try{
            myyAreaService.updateStateById(id);
            jsonResult = new JsonResult(0);
        }catch (Exception e){
            jsonResult = new JsonResult(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询区域一级信息(根据id)")
    @PostMapping(value = "/findAreaone")
    public JsonResult<MyyAreaone> findAreaone(@RequestParam(value = "id") long id){
        JsonResult<MyyAreaone> jsonResult = null;
        try {
            MyyAreaone o = myyAreaService.findById_one(id);
            jsonResult = new JsonResult<>(0,o);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询区域一级信息(根据组织id、区域名字查)")
    @PostMapping(value = "/findAreaonesParam")
    public JsonResult<MyyAreaone> findAreaonesParam(@RequestBody @Valid FindAllAreaByParams findAllAreaByParams){
        JsonResult<MyyAreaone> jsonResult = null;
        try {
            MyyAreaone os = myyAreaService.findAllByDistrictidAndNameAndState(findAllAreaByParams.getDistrictid(),findAllAreaByParams.getName());
            jsonResult = new JsonResult<>(0,os);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询区域一级信息(根据组织id查)")
    @PostMapping(value = "/findAreaones")
    public JsonResult<List<MyyAreaone>> findAreaones(@RequestBody FindAllAreaIn findAllAreaIn){
        JsonResult<List<MyyAreaone>> jsonResult = null;
        try {
            List<MyyAreaone> os = myyAreaService.findoneAllByDistr(findAllAreaIn.getOrgId());
            jsonResult = new JsonResult<>(0,os);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }




    @ApiOperation(value = "添加修改区域二级信息")
    @ApiImplicitParam(name = "myyAreatwo",value = "区域二级信息",dataType = "MyyAreatwo")
    @PostMapping(value = "/addAreatwo")
    public JsonResult addAreatwo(@RequestBody @Valid MyyAreatwo myyAreatwo){
        JsonResult jr = null;
        try {
            myyAreaService.save_two(myyAreatwo);
            jr=  new JsonResult(0,JsonResult.SUCCESSMSG);
        }catch (Exception e){
            jr=  new JsonResult(1,e.getMessage());
        }
        return jr;
    }
    @ApiOperation(value = "查询区域二级信息(根据id)")
    @PostMapping(value = "/findAreatwo")
    public JsonResult<MyyAreatwo> findAreatwo(@RequestParam(value = "id") long id){
        JsonResult<MyyAreatwo> jsonResult = null;
        try {
            MyyAreatwo o = myyAreaService.findById_reach(id);
            jsonResult = new JsonResult<>(0,o);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }
    @ApiOperation(value = "查询区域二级信息(根据区域一级id)")
    @PostMapping(value = "/findAreatwos")
    public JsonResult<List<MyyAreatwo>> findAreatwos(@RequestParam(value = "id") long areaoneid){
        JsonResult<List<MyyAreatwo>> jsonResult = null;
        try {
            List<MyyAreatwo> os = myyAreaService.findTwosByOneid(areaoneid);
            jsonResult = new JsonResult<>(0,os);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }
    @ApiOperation(value = "删除区域二级信息")
    @PostMapping(value = "/deteleAreatwo")
    public JsonResult deteleAreatwo(@RequestParam(value = "id") long id){
        JsonResult jsonResult = null;
        try{
            myyAreaService.updateStateById_reach(id);
            jsonResult = new JsonResult(0);
        }catch (Exception e){
            jsonResult = new JsonResult(1,e.getMessage());
        }
        return jsonResult;
    }


    @ApiOperation(value = "添加修改区域三级信息")
    @ApiImplicitParam(name = "myyAreabottom",value = "区域三级信息",dataType = "MyyAreabottom")
    @PostMapping(value = "/addAreabottom")
    public JsonResult addAreabottom(@RequestBody @Valid MyyAreabottom myyAreabottom){
        JsonResult jr = null;
        try {
            myyAreaService.save_bottom(myyAreabottom);
            jr=  new JsonResult(0,JsonResult.SUCCESSMSG);
        }catch (Exception e){
            jr=  new JsonResult(1,e.getMessage());
        }
        return jr;
    }
    @ApiOperation(value = "查询区域三级信息跟id",notes = "id",httpMethod = "POST")
    @PostMapping(value = "/findAreabottom")
    public JsonResult<MyyAreabottom> findAreabottom(@RequestParam(value = "id") long id){
        JsonResult<MyyAreabottom> jsonResult = null;
        try {
            MyyAreabottom o = myyAreaService.findById_bank(id);
            jsonResult = new JsonResult<>(0,o);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }
    @ApiOperation(value = "查询区域三级信息跟areatwoid",notes = "areatwoid",httpMethod = "POST")
    @PostMapping(value = "/findAreabottoms")
    public JsonResult<List<MyyAreabottom>> findAreabottoms(@RequestParam(value = "id") long areatwoid){
        JsonResult<List<MyyAreabottom>> jsonResult = null;
        try {
            List<MyyAreabottom> os = myyAreaService.findBottomsByTwoid(areatwoid);
            jsonResult = new JsonResult<>(0,os);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }
    @ApiOperation(value = "删除区域三级信息",notes = "",httpMethod = "POST")
    @PostMapping(value = "/deteleAreabottom")
    public JsonResult deteleAreabottom(@RequestParam(value = "id") long id){
        JsonResult jsonResult = null;
        try{
            myyAreaService.updateStateById_bank(id);
            jsonResult = new JsonResult(0);
        }catch (Exception e){
            jsonResult = new JsonResult(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查询区域三级信息跟orgId")
    @PostMapping(value = "/myyAreabottomsOrgid")
    public JsonResult<List<MyyAreabottom>> myyAreabottomsOrgid(@RequestParam(value = "id") long orgId){
        JsonResult<List<MyyAreabottom>> jsonResult = null;
        try {
            List<MyyAreabottom> os = myyAreaService.myyAreabottomsOrgid(orgId);
            jsonResult = new JsonResult<>(0,os);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage());
        }
        return jsonResult;
    }

    @ApiOperation(value = "查找第一级", notes = "orgId")
    @RequestMapping(value = "/findFirstFloor", method = RequestMethod.POST)
    public JsonResult findFirstFloor(@RequestBody FindAllAreaIn findAllIn){
        JsonResult jsonResult = null;
        try{
            List<MyyAreaone> myyAreaones= myyAreaService.findoneAllByDistr(findAllIn.getOrgId());
            List list = new ArrayList<>();
            for(MyyAreaone a : myyAreaones){
                Map map1 = new HashMap();
                map1.put("name",a.getName());
                map1.put("id",a.getId());
                list.add(map1);
            }
            jsonResult = new JsonResult(0,list);
        }catch (Exception e){
            jsonResult = new JsonResult(1,e.getMessage()+"");
        }
        return jsonResult;
    }
    @ApiOperation(value = "查找第二级", notes = "oneid")
    @RequestMapping(value = "/findSecondFloor", method = RequestMethod.POST)
    public JsonResult findSecondFloor(@RequestBody FindAllAreaIn findAllIn){
        JsonResult jsonResult = null;
        try{
            List<MyyAreatwo> myyAreatwos = myyAreaService.findTwosByOneid(findAllIn.getOneId());
            List list = new ArrayList<>();
            for(MyyAreatwo a : myyAreatwos){
                Map map1 = new HashMap();
                map1.put("name",a.getName());
                map1.put("id",a.getId());
                list.add(map1);
            }
            jsonResult = new JsonResult(0,list);
        }catch (Exception e){
            jsonResult = new JsonResult(1,e.getMessage()+"");
        }
        return jsonResult;
    }
    @ApiOperation(value = "查找第三级", notes = "twoid")
    @RequestMapping(value = "/findThirdFloor", method = RequestMethod.POST)
    public JsonResult findThirdFloor(@RequestBody FindAllAreaIn findAllIn){
        JsonResult jsonResult = null;
        try{
            List<MyyAreabottom> myyAreabottoms= myyAreaService.findBottomsByTwoid(findAllIn.getTwoId());
            List list = new ArrayList<>();
            for(MyyAreabottom a : myyAreabottoms){
                Map map1 = new HashMap();
                map1.put("name",a.getName());
                map1.put("id",a.getId());
                list.add(map1);
            }
            jsonResult = new JsonResult(0,list);
        }catch (Exception e){
            jsonResult = new JsonResult(1,e.getMessage()+"");
        }
        return jsonResult;
    }

    @ApiOperation(value = "根据河道id查找河长信息")
    @RequestMapping(value = "/findPosition", method = RequestMethod.POST)
    public JsonResult<WaterinfoOut> findPosition(@RequestBody FindAllAreaIn findAllIn){
        JsonResult<WaterinfoOut> jsonResult = null;

        WaterinfoOut waterinfoOut = new WaterinfoOut();
        try{
            MyyAreaone myyAreaone = myyAreaService.findById_one(findAllIn.getOneId());
            List<MyyPosition> myyPositions = myyAreaService.findPositions(findAllIn.getOneId());

            PositionOut positionOut = new PositionOut();
            for(MyyPosition a : myyPositions){
                if (a.getPositiontype().equals("1")){
                    positionOut.setRivername(a.getName());
                    positionOut.setRiverphone(a.getContact());
                    positionOut.setRiverposition(a.getPosition());
                    positionOut.setRiverresponsibility(a.getResponsibility());
                }
                if (a.getPositiontype().equals("2")){
                    positionOut.setPelicername(a.getName());
                    positionOut.setPelicerphone(a.getContact());
                    positionOut.setPelicerresponsibility(a.getResponsibility());
                }
            }
            waterinfoOut.setMyyPosition(positionOut);
            waterinfoOut.setMyyAreaone(myyAreaone);
            jsonResult = new JsonResult<>(0,waterinfoOut);
        }catch (Exception e){
            jsonResult = new JsonResult<>(1,e.getMessage()+"");
        }
        return jsonResult;
    }

}
