package com.moyuaninfo.cloud.feignClient;

import com.moyuaninfo.cloud.VO.*;
import com.moyuaninfo.cloud.pojo.*;
import com.moyuaninfo.cloud.common.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name="MYY-BASESERVER",fallback = ClientFeignHystrix.class)
public interface BaseServerClient {
    @ApiOperation(value = "查询区域信息",notes = "areaid",httpMethod = "POST")
    @PostMapping(value = "/area/findAreaone")
    public JsonResult<MyyAreaone> findAreaone(@RequestParam(value = "id") long id);

    @ApiOperation(value = "查询区域一级信息",notes = "多参数",httpMethod = "POST")
    @PostMapping(value = "/area/findAreaonesParam")
    public JsonResult<List<MyyAreaone>> findAreaonesParam(@RequestBody @Valid FindAllAreaByParams findAllAreaByParams);

    @ApiOperation(value = "查询区域一级信息(根据组织id查)")
    @PostMapping(value = "/area/findAreaones")
    public JsonResult<List<MyyAreaone>> findAreaones(@RequestBody FindAllAreaIn findAllAreaIn);

    @ApiOperation(value = "查询河段信息",notes = "id",httpMethod = "POST")
    @PostMapping(value = "/area/findAreatwo")
    public JsonResult<MyyAreatwo> findAreatwo(@RequestParam(value = "id") long id);

    @ApiOperation(value = "查询河岸信息跟id",notes = "id",httpMethod = "POST")
    @PostMapping(value = "/area/findAreabottom")
    public JsonResult<MyyAreabottom> findAreabottom(@RequestParam(value = "id") long id);



    @ApiOperation(value = "查询人员信息",notes = "id",httpMethod = "POST")
    @PostMapping(value = "/user/findUser")
    public JsonResult<MyyUser> findUser(@RequestParam(value = "id") long id);
//    @ApiOperation(value = "查询人员信息",notes = "多参数",httpMethod = "POST")
//    @PostMapping(value = "/user/findUsers")
//    public JsonResult<List<MyyUser>> findUsersByParams(@RequestBody FindAllUserByParams findAllUserByParams);

    @ApiOperation(value = "查询组织信息",notes = "id",httpMethod = "POST")
    @PostMapping(value = "/district/findDistrict")
    public JsonResult<MyyDistrict> findDistrict(@RequestParam(value = "id") long id);

    @ApiOperation(value = "查找险情")
    @PostMapping(value = "/district/findWarntypeByWarnen")
    public JsonResult<MyyWarntype> findWarntypeByWarnen(@RequestBody FindAllIn findAllIn);

//    @ApiOperation(value = "查找所有险情")
//    @PostMapping(value = "/district/findWarnByWarnens")
//    public JsonResult<List<MyyWarntype>> findWarnByWarnens(@RequestBody FindAllIn findAllIn);

    @ApiOperation(value = "查找组织下所有险情")
    @PostMapping(value = "/district/findWarnsByOrgid")
    public JsonResult<List<WarninfoOut>> findWarnsByOrgid(@RequestBody FindAllIn findAllIn);

    @PostMapping(value = "/district/findWarnById")
    public JsonResult<MyyWarntype> findWarnById(@RequestBody FindAllIn findAllIn);

    @ApiOperation(value = "查询区域三级信息跟orgId")
    @PostMapping(value = "/area/myyAreabottomsOrgid")
    public JsonResult<List<MyyAreabottom>> myyAreabottomsOrgid(@RequestParam(value = "id") long orgId);

}
