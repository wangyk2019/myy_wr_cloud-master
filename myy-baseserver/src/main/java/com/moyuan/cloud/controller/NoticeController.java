package com.moyuan.cloud.controller;

import com.moyuan.cloud.PageHelper;
import com.moyuan.cloud.VO.FindAllIn;
import com.moyuan.cloud.pojo.JsonResult;
import com.moyuan.cloud.pojo.MyyNotice;
import com.moyuan.cloud.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "NoticeController",tags = "公告接口")
@RestController
@RequestMapping(value = "/notice")
//@CrossOrigin
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @ApiOperation(value="增加、更新", notes="")
    @RequestMapping(value={"/addOne"}, method= RequestMethod.POST)
    public JsonResult addObj(@RequestBody MyyNotice notice) {
        JsonResult lc = null;
        try{
            notice = noticeService.addObj(notice);
            lc = new JsonResult(0);
        }catch (Exception e){
            lc = new JsonResult(1,e.getMessage()+"");
        }
        return lc;
    }

    @ApiOperation(value="删除", notes="根据id")
    @PostMapping(value={"/delOne"})
    public JsonResult updateOneById(@RequestParam(value = "id") long id) {
        JsonResult lc = null;
        try{
            noticeService.deleteObj(id);
            lc = new JsonResult(0);
        }catch (Exception e){
            lc = new JsonResult(1,e.getMessage()+"");
        }
        return lc;
    }

    @ApiOperation(value="根据id查找公告详情")
    @PostMapping(value="/getOne")
    public JsonResult<MyyNotice> getOne(@RequestParam(value = "id") int id) {
        JsonResult<MyyNotice> lc = null;
        try{
            MyyNotice notice = noticeService.getOne(id);
            lc = new JsonResult<>(0,notice);
        }catch (Exception e){
            lc = new JsonResult<>(1,e.getMessage()+"");
        }
        return lc;
    }

    @ApiOperation(value="查找列表", notes="所有")
    @RequestMapping(value={"/getAll"}, method= RequestMethod.POST)
    public JsonResult getAll(@RequestBody FindAllIn findAllIn) {
        JsonResult lc = null;

        Map<String, Object> pageinfo = new HashMap<>();
        try{
            List<MyyNotice> notices = noticeService.getAll();
            pageinfo = PageHelper.SetStartPage(notices, findAllIn.getPagenum(), findAllIn.getPagesize());
            lc = new JsonResult(0,pageinfo);
        }catch (Exception e){
            lc = new JsonResult<>(1,e.getMessage()+"");
        }
        return lc;
    }

}
