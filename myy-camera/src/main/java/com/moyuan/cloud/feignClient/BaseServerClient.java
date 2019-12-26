package com.moyuan.cloud.feignClient;

import com.moyuan.cloud.pojo.JsonResult;
import com.moyuan.cloud.pojo.MyyAreabottom;
import com.moyuan.cloud.pojo.MyyAreaone;
import com.moyuan.cloud.pojo.MyyAreatwo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="myy-baseserver",fallback = ClientFeignHystrix.class)
public interface BaseServerClient {
    @ApiOperation(value = "查询区域三层信息跟id",notes = "id",httpMethod = "POST")
    @PostMapping(value = "/area/findAreabottom")
    public JsonResult<MyyAreabottom> findAreabottom(@RequestParam(value = "id") long id);

    @ApiOperation(value = "查询区域二层信息",notes = "id",httpMethod = "POST")
    @PostMapping(value = "/area/findAreatwo")
    public JsonResult<MyyAreatwo> findAreatwo(@RequestParam(value = "id") long id);

    @ApiOperation(value = "查询区域一层信息",notes = "areaoneid",httpMethod = "POST")
    @PostMapping(value = "/area/findAreaone")
    public JsonResult<MyyAreaone> findAreaone(@RequestParam(value = "id") long id);

}
