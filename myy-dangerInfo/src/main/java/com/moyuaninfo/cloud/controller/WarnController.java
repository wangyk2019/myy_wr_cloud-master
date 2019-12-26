package com.moyuaninfo.cloud.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.moyuaninfo.cloud.PageHelper;
import com.moyuaninfo.cloud.VO.*;
import com.moyuaninfo.cloud.feignClient.LivePlayClient;
import com.moyuaninfo.cloud.pojo.*;
import com.moyuaninfo.cloud.common.FindByParams;
import com.moyuaninfo.cloud.common.JsonResult;
import com.moyuaninfo.cloud.feignClient.BaseServerClient;
import com.moyuaninfo.cloud.feignClient.CameraClient;
import com.moyuaninfo.cloud.service.XianqingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(value = "险情查询",tags = "险情查询接口")
@RequestMapping(value = "/warn")
public class WarnController {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

    @Autowired
    XianqingService xianqingService;
    @Autowired
    BaseServerClient baseServerClient;
    @Autowired
    CameraClient cameraClient;
    @Autowired
    LivePlayClient livePlayClient;

    /**
     * app险情主页
     * 险情汇总  最新险情
     * orgId，
     * @param findAllIn
     * @return
     */
    @ApiOperation(value="险情主页")
    @PostMapping(value = "/infolists")
    public JsonResult<WarnReferByTypeOut> infolists(@RequestBody FindAllIn findAllIn){
        JsonResult<WarnReferByTypeOut> jsrt = null;

        WarnReferByTypeOut warnReferByTypeOut =new WarnReferByTypeOut();
        try {
            //根据组织id查询3条最新险情
            List<MyyDangerInfo> myyDangerInfos2 = xianqingService.findByDistAndNum3(findAllIn.getOrgId());

            List<WarnType> warnList = new ArrayList<>();
            findAllIn.setBelongs(1);
            //根据组织id，belongs查询组织首页展示的险情
            JsonResult<List<WarninfoOut>> warninfOuts = baseServerClient.findWarnsByOrgid(findAllIn);
            if (warninfOuts.getCode() == 0 && warninfOuts.getData() != null){
                for(int j=0;j<warninfOuts.getData().size();j++){
                    //险情首页汇总信息返回类
                    WarnType warnType = new WarnType();
                    //根据组织id,险情类型查询险情量
                    long num = xianqingService.countAllByDistrictidAndWarntypeAndResult(findAllIn.getOrgId(),warninfOuts.getData().get(j).getWarnen());
                    warnType.setWarnname(warninfOuts.getData().get(j).getWarnname());
                    warnType.setWarntypeid(warninfOuts.getData().get(j).getWarntypeid());
                    warnType.setWarncorn(warninfOuts.getData().get(j).getWarncorn());
                    warnType.setNum(num);
                    warnType.setActive(warninfOuts.getData().get(j).getActive());
                    warnType.setWarnen(warninfOuts.getData().get(j).getWarnen());
                    warnType.setSort(warninfOuts.getData().get(j).getSort());

                    warnList.add(warnType);
                    warnReferByTypeOut.setDangerList(warnList);
                }
            }

            List<NewWarn> newWarns = new ArrayList<>();
            for(MyyDangerInfo dangerInfo : myyDangerInfos2){
                //查询摄像头信息
                JsonResult<MyyCamera> camera = cameraClient.findById(dangerInfo.getCameraid());
                //险情首页最新险情信息返回类
                NewWarn newWarn = new NewWarn();
                newWarn.setId(dangerInfo.getId());
                newWarn.setAreaName(camera.getData().getLocation());
                newWarn.setCameraName(camera.getData().getName());
                newWarn.setPic(dangerInfo.getPicpath());
                newWarn.setWarnName(dangerInfo.getName());
                newWarn.setTime(dangerInfo.getTime());
                newWarns.add(newWarn);
                warnReferByTypeOut.setNewdangerList(newWarns);
            }
            jsrt = new JsonResult<>(0,warnReferByTypeOut);
        } catch (Exception e) {
            jsrt = new JsonResult<>(1,e.getClass().getName() + ":" + e.getMessage());
        }
        return jsrt;
    }

    /**
     * app险情汇总下的更多功能
     * orgId
     * @param findAllIn
     * @return
     */
    @ApiOperation(value="更多功能")
    @PostMapping(value = "/moreWarns")
    public JsonResult<WarnReferByTypeOut> moreWarns(@RequestBody FindAllIn findAllIn){
        JsonResult<WarnReferByTypeOut> jsrt = null;

        WarnReferByTypeOut warnReferByTypeOut =new WarnReferByTypeOut();
        try {
            List<WarnType> warnList = new ArrayList<>();
            List<WarnType> morelist = new ArrayList<>();

            //首页
            findAllIn.setBelongs(1);
            JsonResult<List<WarninfoOut>> warninfOuts1 = baseServerClient.findWarnsByOrgid(findAllIn);
            if (warninfOuts1.getCode() == 0 && warninfOuts1.getData() != null){
                for(int j=0;j<warninfOuts1.getData().size();j++){
                    WarnType warnType = new WarnType();
                    long num = xianqingService.countAllByDistrictidAndWarntypeAndResult(findAllIn.getOrgId(),warninfOuts1.getData().get(j).getWarnen());
                    warnType.setWarnname(warninfOuts1.getData().get(j).getWarnname());
                    warnType.setWarntypeid(warninfOuts1.getData().get(j).getWarntypeid());
                    warnType.setWarncorn(warninfOuts1.getData().get(j).getWarncorn());
                    warnType.setNum(num);
                    warnType.setActive(warninfOuts1.getData().get(j).getActive());
                    warnType.setWarnen(warninfOuts1.getData().get(j).getWarnen());
                    warnType.setSort(warninfOuts1.getData().get(j).getSort());
                    warnList.add(warnType);
                }
            }

            //更多
            findAllIn.setBelongs(2);
            JsonResult<List<WarninfoOut>> warninfOuts2 = baseServerClient.findWarnsByOrgid(findAllIn);
            if (warninfOuts2.getCode() == 0 && warninfOuts2.getData() != null){
                for(int j=0;j<warninfOuts2.getData().size();j++){
                    WarnType warnType = new WarnType();
                    long num = xianqingService.countAllByDistrictidAndWarntypeAndResult(findAllIn.getOrgId(),warninfOuts2.getData().get(j).getWarnen());
                    warnType.setWarnname(warninfOuts2.getData().get(j).getWarnname());
                    warnType.setWarntypeid(warninfOuts2.getData().get(j).getWarntypeid());
                    warnType.setWarncorn(warninfOuts2.getData().get(j).getWarncorn());
                    warnType.setNum(num);
                    warnType.setActive(warninfOuts2.getData().get(j).getActive());
                    warnType.setWarnen(warninfOuts2.getData().get(j).getWarnen());
                    warnType.setSort(warninfOuts2.getData().get(j).getSort());
                    morelist.add(warnType);
                }
            }
            warnReferByTypeOut.setDangerList(warnList);
            warnReferByTypeOut.setMoredanger(morelist);
            jsrt = new JsonResult<>(0,warnReferByTypeOut);
        } catch (Exception e) {
            jsrt = new JsonResult<>(1,e.getClass().getName() + ":" + e.getMessage());
        }
        return jsrt;
    }


    /**
     * app险情汇总下的险情分类查询
     * orgId，warnen
     * 分页
     * @param findByParams
     * @return
     */
    @ApiOperation(value="险情分类查询")
    @PostMapping(value = "/ReferByType")
    public JsonResult<DangersOut> warnReferByType(@RequestBody FindByParams findByParams){
        JsonResult jsrt = null;
        DangersOut dangersOut = new DangersOut();

        Map<String, Object> pageinfo = new HashMap<>();
        try {
            //根据组织id,warnen,result为1 查询险情，时间倒序
            List<MyyDangerInfo> myyDangerInfos = xianqingService.findByDistAndWarntype(findByParams.getOrgId(),findByParams.getWarnen());

            //根据组织id,warnen,result为1，ischeck=0 查询未查看险情数量
            Long myyDangerInfos_state0 = xianqingService.countAllByDistrictidAndWarntypeAndResultAndIscheck(findByParams.getOrgId(),findByParams.getWarnen());

            //封装返回信息
            List<WarnMsg> warnList =new ArrayList<WarnMsg>();
            for (MyyDangerInfo a : myyDangerInfos){
                WarnMsg warnMsg = new WarnMsg();
                warnMsg.setId(a.getId());
                warnMsg.setEventType(a.getName());
                warnMsg.setImg(a.getPicpath());
                warnMsg.setCtime(a.getTime());
                warnMsg.setState(a.getState());

                JsonResult<MyyCamera> myyCameraJsonResult = cameraClient.findById(a.getCameraid());
                warnMsg.setCamName(myyCameraJsonResult.getData().getName());
                warnMsg.setAddr(myyCameraJsonResult.getData().getLocation());

                warnList.add(warnMsg);
            }
            //分页
            pageinfo = PageHelper.SetStartPage(warnList, findByParams.getPagenum(), findByParams.getPagesize());
            dangersOut.setWarnMsgs(pageinfo);
            dangersOut.setUncheck(myyDangerInfos_state0.intValue());
            jsrt = new JsonResult(0,dangersOut);
        } catch (Exception e) {
            jsrt = new JsonResult(1,e.getClass().getName() + ":" + e.getMessage());
        }
        return jsrt;
    }

    /**
     * app险情主页的搜索
     * orgId，warnname
     * 分页
     * @param findByParams
     * @return
     */
    @ApiOperation(value="险情分类搜索")
    @PostMapping(value = "/ReferByWarnname")
    public JsonResult<DangersOut> ReferByWarnname(@RequestBody FindByParams findByParams){
        JsonResult jsrt = null;
        DangersOut dangersOut = new DangersOut();

        Map<String, Object> pageinfo = null;
        try {
            //根据组织id,warnname,result为1 查询险情，时间倒序
            List<MyyDangerInfo> myyDangerInfos = xianqingService.findByDistAndWarnname(findByParams.getOrgId(),findByParams.getWarnname());
            //根据组织id,warnname,result为1，ischeck=0 查询未查看险情数量
            Long myyDangerInfos_state0 = xianqingService.countAllByDistrictidAndNameAndResultAndIscheck(findByParams.getOrgId(),findByParams.getWarnen());

            //封装返回信息
            List<WarnMsg> warnList =new ArrayList<WarnMsg>();
            for (MyyDangerInfo a : myyDangerInfos){
                WarnMsg warnMsg = new WarnMsg();
                warnMsg.setId(a.getId());
                warnMsg.setEventType(a.getName());
                warnMsg.setImg(a.getPicpath());
                warnMsg.setCtime(a.getTime());
                warnMsg.setState(a.getState());

                JsonResult<MyyCamera> myyCameraJsonResult = cameraClient.findById(a.getCameraid());
                warnMsg.setCamName(myyCameraJsonResult.getData().getName());
                warnMsg.setAddr(myyCameraJsonResult.getData().getLocation());

                warnList.add(warnMsg);
            }
            //分页
            pageinfo = PageHelper.SetStartPage(warnList, findByParams.getPagenum(), findByParams.getPagesize());
            dangersOut.setWarnMsgs(pageinfo);
            dangersOut.setUncheck(myyDangerInfos_state0.intValue());
            jsrt = new JsonResult(0,dangersOut);
        } catch (Exception e) {
            jsrt = new JsonResult(1,e.getClass().getName() + ":" + e.getMessage());
        }
        return jsrt;
    }

    /**
     * app险情分类的标为已查看
     * orgId，warnen
     * @param findByParams
     * @return
     */
    @ApiOperation(value="全部标为已查看")
    @PostMapping(value = "/to_check")
    public JsonResult to_check(@RequestBody FindByParams findByParams){
        JsonResult jsrt = null;
        try {
            //根据组织id,warnen更新uncheck=1
            xianqingService.updateStateByDistrictidAndwarntype(findByParams.getOrgId(),findByParams.getWarnen());
            jsrt = new JsonResult(0);
        } catch (Exception e) {
            jsrt = new JsonResult(1,e.getClass().getName() + ":" + e.getMessage());
        }
        return jsrt;
    }

    @ApiOperation(value="险情详情查询")
    @PostMapping(value = "/warnDetailRefer")
    public JsonResult<WarnDetailReferOut> warnDetailRefer_river(@RequestBody WarnDetailReferIn warnDetailReferIn){
        JsonResult<WarnDetailReferOut> jsrt = null;
        MyyDangerInfo myyDangerInfo = xianqingService.getObj(warnDetailReferIn.getDangerId());
        if (myyDangerInfo!= null){
            //返回信息类
            WarnDetailReferOut warnDetailReferOut = new WarnDetailReferOut();

            JsonResult<MyyCamera> jsrtC =cameraClient.findById(myyDangerInfo.getCameraid());
            if (jsrtC.getCode() == 0 && jsrtC.getData()!=null){
                warnDetailReferOut.setDcam(jsrtC.getData().getName());
                warnDetailReferOut.setDaddr(jsrtC.getData().getLocation());
            }else{
                warnDetailReferOut.setDcam("");
                warnDetailReferOut.setDaddr("");
            }
            warnDetailReferOut.setDtime(myyDangerInfo.getTime());
            warnDetailReferOut.setDtype(myyDangerInfo.getName());
            warnDetailReferOut.setVedioAddr(myyDangerInfo.getFilepath());
            warnDetailReferOut.setVedioImg(myyDangerInfo.getPicpath());

            jsrt = new JsonResult<>(0,warnDetailReferOut);

            //更新已查看
            myyDangerInfo.setIscheck(1);
            xianqingService.addObj(myyDangerInfo);
        }else{
            jsrt = new JsonResult<>(1,"未找到该信息！");
        }
        return jsrt;
    }


    @ApiOperation(value="在线摄像头信息查询")
    @PostMapping(value = "/online")
    public JsonResult findFloor1AndFloor2AndRoomAndCameraByColleage1(@RequestBody FindAllIn findAllIn, @RequestHeader("userid") int userid){
        JsonResult jsrt = null;
        Map map = new HashMap();
        try {
            JsonResult<MyyUser> userjr = baseServerClient.findUser(userid);
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray1 = new JSONArray();
            if (userjr.getCode() == 0 && userjr.getData()!= null){
                if(userjr.getData().getLivepower()!=null && userjr.getData().getLivepower().equals("1")){
                    MyyCamera myyCamera = null;
                    if (findAllIn.getCamId() == 0){
                        JsonResult<List<MyyCamera>> cameras= cameraClient.findAllByDistrict(userjr.getData().getDistrict_id());
                        myyCamera = cameras.getData().get(0);
                    }else {
                        JsonResult<MyyCamera> camera= cameraClient.findById(findAllIn.getCamId());
                        myyCamera = camera.getData();
                    }

                    if(myyCamera != null){
                        JsonResult<MyyAreabottom> areabottom = baseServerClient.findAreabottom(myyCamera.getAreabottomid());
                        jsonObject.put("mpAddr",myyCamera.getLocation());
                        jsonObject.put("vedioImg","");

                        if (areabottom.getData().getManager()!=0){
                            JsonResult<MyyUser> userJsonResult = baseServerClient.findUser(areabottom.getData().getManager());
                            jsonObject.put("mpName",userJsonResult.getData().getUsername());
                            jsonObject.put("mpMobile",userJsonResult.getData().getPhonenumber());
                        }else{
                            jsonObject.put("mpName","");
                            jsonObject.put("mpMobile","");
                        }

                        CameraVO cameraVO = new CameraVO();
                        cameraVO.setAreaid(myyCamera.getAreabottomid());
                        cameraVO.setCamIp(myyCamera.getIp());
                        cameraVO.setDistrictid(findAllIn.getOrgId());
                        cameraVO.setChannel(myyCamera.getChannel());
                        cameraVO.setId(myyCamera.getId());
                        cameraVO.setUserid(String.valueOf(userid));
                        JsonResult<String> stringJsonResult = livePlayClient.wsplay(cameraVO);
                        if (stringJsonResult.getCode()==0){
                            jsonObject.put("vedioAddr",stringJsonResult.getData());
                        }else {
                            jsonObject.put("vedioAddr","");
                            jsonObject.put("vedioImg","");
                        }
                        map.put("onlinedata",jsonArray1);

                        jsrt = new JsonResult(0,"ok", jsonObject);
                    }else {
                        jsrt = new JsonResult(1,"摄像头查询出错！", jsonObject);
                    }

                }else{
                    jsonObject.put("vedioAddr","");
                    jsonObject.put("vedioImg","https://pycafffe-1258601646.cos.ap-shanghai.myqcloud.com/test/noright.png");
                    map.put("onlinedata",jsonArray1);
                    jsrt = new JsonResult(2,"没有直播权限...", map);
                }
            }else {
                jsrt = new JsonResult(1,"人员信息错误...", map);
            }

        } catch (Exception e) {
            jsrt = new JsonResult(1,e.getClass().getName() + ":" + e.getMessage());
        }
        return jsrt;
    }

    @ApiOperation(value="险情信息筛选查询")
    @PostMapping(value = "/refer/page")
    public JsonResult<MessageReferOut> messageRefer_page(@RequestBody MessageReferIn messageReferIn){
        JsonResult<MessageReferOut> jsrt = null;
        MessageReferOut messageReferOut = new MessageReferOut();

        Map<String, Object> pageinfo = new HashMap<>();
        try {

            long unchecknum = xianqingService.countAllByDistrictidAndWarntypeAndResultAndIscheck(messageReferIn.getOrgId(),messageReferIn.getEventtpye());
            List<MyyDangerInfo> myyDangerInfos = xianqingService.findAllByColleageandAndFloor1AndLabidAndTypeaAndTime_page(messageReferIn.getOrgId(),
                    messageReferIn.getAreaone(),messageReferIn.getAreatwo(),messageReferIn.getAreabottom(),
                    messageReferIn.getCameraid(),messageReferIn.getEventtpye(),
                    messageReferIn.getBegintime(),messageReferIn.getEndtime());

            List<WarnMsg> warnList =new ArrayList<>();
            for (MyyDangerInfo a : myyDangerInfos){
                WarnMsg warnMsg = new WarnMsg();
                warnMsg.setId(a.getId());
                warnMsg.setEventType(a.getName());
                warnMsg.setImg(a.getPicpath());
                warnMsg.setCtime(a.getTime());
                warnMsg.setState(a.getState());

                JsonResult<MyyCamera> camera = cameraClient.findById(a.getCameraid());
                warnMsg.setAddr(camera.getData().getLocation());

                warnList.add(warnMsg);
            }
            pageinfo = PageHelper.SetStartPage(warnList, messageReferIn.getPagenum(), messageReferIn.getPagesize());
            messageReferOut.setMsginfo(pageinfo);
            messageReferOut.setUncheck(unchecknum);
            jsrt = new JsonResult<>(0,messageReferOut);
        } catch (Exception e) {
            jsrt = new JsonResult<>(1,"失败！" + e.getMessage());
        }
        return jsrt;
    }

}
