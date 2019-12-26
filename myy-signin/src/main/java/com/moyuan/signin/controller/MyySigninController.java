package com.moyuan.signin.controller;

import com.alibaba.fastjson.JSONObject;
import com.moyuan.signin.PageHelper;
import com.moyuan.signin.Tools;
import com.moyuan.signin.feign.BaseServerClient;
import com.moyuan.signin.pojo.*;
import com.moyuan.signin.service.MyySigninPlanService;
import com.moyuan.signin.service.MyySigninPointService;
import com.moyuan.signin.service.MyySigninService;
import com.moyuan.signin.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

@Api(value = "巡检打卡管理", tags = "巡检打卡管理接口")
@RestController
public class MyySigninController {

    @Autowired
    private MyySigninService myySigninService;
    @Autowired
    private MyySigninPointService myySigninPointService;
    @Autowired
    private MyySigninPlanService myySigninPlanService;

    @Autowired
    BaseServerClient baseServerClient;

    @Value("${signin.distance}")
    public Double SigninDistance;

    @ApiOperation(value = "查询巡检打卡点", notes = "查询巡检打卡点", httpMethod = "POST")
//    @ApiImplicitParams({})
    @PostMapping(value = "/listSigninPoints")
    public JsonResult<String> listSigninPoints(@RequestBody MyySigninPointVo vo) {
        List<MyySigninPoint> points = myySigninPointService.findByParentArea(vo.getAreaid());
        return new JsonResult(0,"OK", points);
    }

    @ApiOperation(value = "添加或更新巡检打卡点", notes = "添加或更新巡检打卡点", httpMethod = "POST")
//    @ApiImplicitParams({})
    @PostMapping(value = "/saveOrUpdateSigninPoint")
    public JsonResult<String> saveOrUpdateSigninPoint(@RequestBody MyySigninPointVo vo) {
        MyySigninPoint point = new MyySigninPoint();
        if (vo.getId() != null && vo.getId() != 0) {
            point = myySigninPointService.findById(vo.getId());
        }
        point.setParent(vo.getAreaid());
        point.setLatitude(vo.getLatitude());
        point.setLongitude(vo.getLongitude());
        point.setName(vo.getName());
        boolean flag = myySigninPointService.saveOrUpdate(point);
        List<MyySigninPoint> points = myySigninPointService.findByParentArea(vo.getAreaid());
        return new JsonResult(0, flag ? "更新成功" : "更新失败", points);
    }

    @ApiOperation(value = "更新巡检计划", notes = "更新巡检计划，传ID及userid", httpMethod = "POST")
//    @ApiImplicitParams({})
    @PostMapping(value = "/updateSigninPlan")
    public JsonResult<String> updateSigninPlan(@RequestBody MyySigninPlanVo vo) {
        MyySigninPlan plan = myySigninPlanService.findById(vo.getId());
        boolean flag = false;
        if (plan != null) {
            plan.setUserida(vo.getUserida());
            plan.setUseridb(vo.getUseridb());
            flag = myySigninPlanService.saveOrUpdate(plan);
        }
        return new JsonResult(0, flag ? "更新成功" : "更新失败");
    }

    @ApiOperation(value = "下周巡检计划", notes = "获取下个星期的巡检计划", httpMethod = "POST")
//    @ApiImplicitParams({})
    @PostMapping(value = "/getNextWeekSigninPlan")
    public JsonResult<List<MyySigninPlanVo>> getNextWeekSigninPlan(@RequestBody MyySigninPlanVo vo) {
        //1.获取下周的开始日期和结束日期
        String begindate = Tools.getTimeStringByDate(Tools.getFirstDayOfWeek(Calendar.getInstance(), 1).getTime(), "yyyy-MM-dd");
        String enddate = Tools.getTimeStringByDate(Tools.getLastDayOfWeek(Calendar.getInstance(), 1).getTime(), "yyyy-MM-dd");
        //2.数据库获取下周的巡检计划，巡检计划为空则生成空缺人员的巡检计划
        List<MyySigninPlan> planlist = myySigninPlanService.findAllBetweenDate(begindate, enddate, vo.getAreaid());
        if ((planlist != null && planlist.size() != 7) || planlist == null) {
            //巡检计划不为7，将巡检计划全部标记为逻辑删除，并生成新的7天计划
            if (planlist != null && planlist.size() != 7) {
                for (MyySigninPlan plan : planlist) {
                    plan.setState("0");
                    myySigninPlanService.saveOrUpdate(plan);
                }
            }
            Calendar calendar = Tools.getFirstDayOfWeek(Calendar.getInstance(), 1);
            for (int i = 0; i < 7; i++) {
                MyySigninPlan plan = new MyySigninPlan();
                plan.setAreaid(vo.getAreaid());
                plan.setDate(Tools.getTimeStringByDate(calendar.getTime(), "yyyy-MM-dd"));
                plan.setWeek(Tools.getDayForWeek(calendar));
                plan.setWeeknum(calendar.get(Calendar.WEEK_OF_YEAR));
                myySigninPlanService.saveOrUpdate(plan);
                calendar.add(Calendar.DATE, 1);
            }
        }
        //3.再次获取巡检计划，并格式化为页面显示
        List<MyySigninPlan> realplanlist = myySigninPlanService.findAllBetweenDate(begindate, enddate, vo.getAreaid());
        List<MyySigninPlanVo> outputlist = null;
        if (realplanlist != null) {
            outputlist = new ArrayList<>();
            for (MyySigninPlan plan : realplanlist) {
                MyySigninPlanVo out = new MyySigninPlanVo();
                out.setAreaid(plan.getAreaid());
                out.setDate(plan.getDate());
                Calendar cld = Calendar.getInstance();
                try {
                    cld.setTime(Tools.getDateByTimeString(plan.getDate(), "yyyy-MM-dd"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                out.setWeek(Tools.getDayOfWeek(cld));
                if (plan.getUserida() != null && plan.getUserida() != 0) {
                    JsonResult<MyyUser> user1 = baseServerClient.findUser(plan.getUserida());
                    if (user1 != null && user1.getCode() == 0 && user1.getData() != null) {
                        out.setUsernamea(user1.getData().getUsername());
                        out.setPhonea(user1.getData().getPhonenumber());
                    }
                }
                if (plan.getUserida() != null && plan.getUserida() != 0) {
                    JsonResult<MyyUser> user2 = baseServerClient.findUser(plan.getUseridb());
                    if (user2 != null && user2.getCode() == 0 && user2.getData() != null) {
                        out.setUsernameb(user2.getData().getUsername());
                        out.setPhoneb(user2.getData().getPhonenumber());
                    }
                }
                out.setUserida(plan.getUserida());
                out.setUseridb(plan.getUseridb());
                out.setId(plan.getId());
                outputlist.add(out);
            }
        }
        return new JsonResult(0, outputlist);
    }

    @ApiOperation(value = "测试接口", notes = "测试接口", httpMethod = "POST")
//    @ApiImplicitParams({})
    @PostMapping(value = "/testPage")
    public JsonResult<String> testPage() {
        List<Long> userids = new ArrayList<>();
        userids.add(980l);
        userids.add(922l);
        return new JsonResult(0, myySigninService.findByAreaidAndUseridsOrderBySignintimePageable(67l, userids, 1, 3));
    }

    @ApiOperation(value = "搜索打卡记录", notes = "搜索打卡记录", httpMethod = "POST")
//    @ApiImplicitParams({@ApiImplicitParam(name = "areaid", value = "区域ID", required = false, dataType = "long", example = "0"),
//            @ApiImplicitParam(name = "userid", value = "用户ID", required = false, dataType = "long", example = "0"),
//            @ApiImplicitParam(name = "begindate", value = "开始日期", required = false, dataType = "String"),
//            @ApiImplicitParam(name = "enddate", value = "开始日期", required = false, dataType = "String")})
    @PostMapping(value = "/search")
    public JsonResult<List> search(@RequestBody MyySigninPageVo pageVo) {
        List<MyySigninPageOutVo> signinPageOutVoList = null;
        Map<String, Object> pageinfo = null;
        FindAllUserByParams params = new FindAllUserByParams(pageVo.getParam(), pageVo.getAreaid());
        JsonResult<List<MyyUser>> jssjsj = baseServerClient.findUsersByParams(params);
        if (jssjsj != null && jssjsj.getCode() == 0 && jssjsj.getData() != null) {
            List<Long> userids = new ArrayList<>();
            HashMap<String, String> namemap = new HashMap<String, String>();
            for (MyyUser user : jssjsj.getData()) {
                userids.add(user.getId());
                namemap.put(String.valueOf(user.getId()), user.getUsername());
            }
            if (userids.size() > 0) {
                List<MyySignin> signinList = myySigninService.findByAreaidAndUseridsOrderBySignintime(pageVo.getAreaid(), userids, pageVo.getPagenum(), pageVo.getPagesize());
                if (signinList != null) {
                    signinPageOutVoList = new ArrayList<>();
                    for (MyySignin signin : signinList) {
                        MyySigninPageOutVo vo = new MyySigninPageOutVo();
                        vo.setLatitude(signin.getLatitude());
                        vo.setLongitude(signin.getLongitude());
                        vo.setPlanid(signin.getPlanid());
                        vo.setPointid(signin.getPointid());
                        vo.setResult(signin.getResult());
                        vo.setSignintime(signin.getSignintime());
                        vo.setUserid(signin.getUserid());
                        if (namemap.get(String.valueOf(vo.getUserid())) != null) {
                            vo.setUsername(namemap.get(String.valueOf(vo.getUserid())));
                        } else {
                            vo.setUsername("");
                        }
                        signinPageOutVoList.add(vo);
                    }
                    pageinfo = PageHelper.SetStartPage(signinPageOutVoList, pageVo.getPagenum(), pageVo.getPagesize());
                }
            }
        }
        if (pageinfo != null) {
            return new JsonResult(0, pageinfo);
        } else {
            return new JsonResult(0, "没有找到数据");
        }
    }

    @ApiOperation(value = "查询打卡记录数量", notes = "查询打卡记录数据", httpMethod = "POST")
    @ApiImplicitParams({})
    @PostMapping(value = "/countOfAll")
    public JsonResult<String> countOfAll() {
        return new JsonResult(0, myySigninService.countOfAll());
    }

    @ApiOperation(value = "查询打卡记录", notes = "分页查询打卡记录", httpMethod = "POST")
//    @ApiImplicitParams({@ApiImplicitParam(name = "areaid", value = "区域ID", required = true, dataType = "long"),
//            @ApiImplicitParam(name = "pagenum", value = "页码", required = true, dataType = "int"),
//            @ApiImplicitParam(name = "pagesize", value = "每页数据量", required = true, dataType = "int")})
    @PostMapping(value = "/findAll")
    public JsonResult<List> findAll(@RequestBody MyySigninPageVo myySigninPageVo) {
        String startdate = null, enddate = null;
        Map<String, Object> pageinfo = null;
        if ("week".equalsIgnoreCase(myySigninPageVo.getTimelength())) {
            startdate = Tools.getTimeStringByDate(Tools.getFirstDayOfWeek(Calendar.getInstance(), -1).getTime(), "yyyy-MM-dd");
            enddate = Tools.getTimeStringByDate(Tools.getLastDayOfWeek(Calendar.getInstance(), -1).getTime(), "yyyy-MM-dd");
        } else if ("weeks".equalsIgnoreCase(myySigninPageVo.getTimelength())) {
            startdate = Tools.getTimeStringByDate(Tools.getFirstDayOfWeek(Calendar.getInstance(), -2).getTime(), "yyyy-MM-dd");
            enddate = Tools.getTimeStringByDate(Tools.getLastDayOfWeek(Calendar.getInstance(), -2).getTime(), "yyyy-MM-dd");
        } else if ("month".equalsIgnoreCase(myySigninPageVo.getTimelength())) {
            startdate = Tools.getTimeStringByDate(Tools.getFirstDayOfMounth(Calendar.getInstance()).getTime(), "yyyy-MM-dd");
            enddate = Tools.getTimeStringByDate(Tools.getLastDayOfMounth(Calendar.getInstance()).getTime(), "yyyy-MM-dd");
        } else if ("season".equalsIgnoreCase(myySigninPageVo.getTimelength())) {
            startdate = Tools.getTimeStringByDate(Tools.getFirstDayOfQuarter(Calendar.getInstance()).getTime(), "yyyy-MM-dd");
            enddate = Tools.getTimeStringByDate(Tools.getLastDayOfQuarter(Calendar.getInstance()).getTime(), "yyyy-MM-dd");
        }
        List<MyySignin> signinList = myySigninService.findByAreaidAndSignintimeOrderBySignintime(myySigninPageVo.getAreaid(),
                startdate, enddate, myySigninPageVo.getPagenum(), myySigninPageVo.getPagesize());
        if (signinList != null) {
            List<MyySigninPageOutVo> signinPageOutVoList = new ArrayList<>();
            JsonResult<MyyUser> jrrrr = null;
            for (MyySignin signin : signinList) {
                MyySigninPageOutVo vo = new MyySigninPageOutVo();
                vo.setLatitude(signin.getLatitude());
                vo.setLongitude(signin.getLongitude());
                vo.setPlanid(signin.getPlanid());
                vo.setPointid(signin.getPointid());
                vo.setResult(signin.getResult());
                vo.setSignintime(signin.getSignintime());
                vo.setUserid(signin.getUserid());
                jrrrr = baseServerClient.findUser(signin.getUserid());
                if (jrrrr != null && jrrrr.getCode() == 0 && jrrrr.getData() != null) {
                    vo.setUsername(jrrrr.getData().getUsername());
                } else {
                    vo.setUsername("");
                }
                signinPageOutVoList.add(vo);
            }
            pageinfo = PageHelper.SetStartPage(signinPageOutVoList, myySigninPageVo.getPagenum(), myySigninPageVo.getPagesize());
        }
        return new JsonResult(0, pageinfo);
    }

    @ApiOperation(value = "查询巡检打卡点及打卡记录", notes = "查询巡检打卡点及打卡记录", httpMethod = "POST")
//    @ApiImplicitParams({@ApiImplicitParam(name = "areaid", value = "区域ID", required = true, dataType = "long"),
//            @ApiImplicitParam(name = "userid", value = "用户ID", required = true, dataType = "long")})
    @PostMapping(value = "/findUserPointList")
    public JsonResult<String> findUserPointList(@RequestBody MyySigninVo myySigninVo, @RequestHeader(value = "userid") long userid) {
        List<MyySigninVo> list = null;
        MyySigninPlan singplan = myySigninPlanService.findByDateAndUserid(Tools.getTimeString("yyyy-MM-dd"), userid);
        if (singplan != null) {
            List<MyySignin> signinList = myySigninService.findAllByPlanidAndUserid(singplan.getId(), userid);
            List<MyySigninPoint> pointList = myySigninPointService.findByParentArea(myySigninVo.getAreaid());
            if (pointList != null) {
                list = new ArrayList<MyySigninVo>();
                for (int i = 0; i < pointList.size(); i++) {
                    MyySigninVo vo = new MyySigninVo();
                    vo.setName(pointList.get(i).getName());
                    vo.setLatitude(pointList.get(i).getLatitude());
                    vo.setLongitude(pointList.get(i).getLongitude());
                    vo.setAreaid(pointList.get(i).getParent());
                    vo.setState("0");
                    if (signinList != null) {
                        for (int j = 0; j < signinList.size(); j++) {
                            if (signinList.get(j).getPointid() == pointList.get(i).getId() &&
                                    signinList.get(j).getResult().equalsIgnoreCase("1")) {
                                vo.setState("1");
                            }
                        }
                    }
                    list.add(vo);
                }
            }
        }
        return new JsonResult(0, list);
    }

    @ApiOperation(value = "巡检打卡", notes = "打卡", httpMethod = "POST")
//    @ApiImplicitParams({@ApiImplicitParam(name = "userid", value = "用户ID", required = true, dataType = "long"),
//            @ApiImplicitParam(name = "longitude", value = "经度", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "latitude", value = "纬度", required = true, dataType = "String")})
    @PostMapping(value = "/signin")
    public JsonResult<JSONObject> signin(@RequestHeader(value = "userid") long userid,
                                         @RequestBody MyySigninVo myySigninVo) {
        JsonResult<JSONObject> jrst = new JsonResult<>(0, "超出打卡范围");
        JSONObject outdata = new JSONObject();
        outdata.put("result", false);
        //先查用户是否有巡检计划
        MyySigninPlan plan = myySigninPlanService.findByDateAndUserid(Tools.getTimeString("yyyy-MM-dd"), userid);
        if (plan == null) {
            return new JsonResult<>(0, "无打卡计划");
        }
        List<MyySigninPoint> pointList = myySigninPointService.findByParentArea(plan.getAreaid());
        if (pointList == null) {
            return new JsonResult<>(0, "未设置打卡点");
        }
        //再用经纬度计算距离哪个打卡点最近
        MyySigninPoint signinArea = null;
        double distance = 0;
        double long1, long2 = 0;
        double lat1, lat2 = 0;
        ArrayList<MyySigninPoint> signinAreas = new ArrayList<MyySigninPoint>();
        for (int i = 0; i < pointList.size(); i++) {
            signinArea = pointList.get(i);
            long1 = Double.parseDouble(signinArea.getLongitude());
            lat1 = Double.parseDouble(signinArea.getLatitude());
            long2 = Double.parseDouble(myySigninVo.getLongitude());
            lat2 = Double.parseDouble(myySigninVo.getLatitude());
            distance = Tools.GetDistance(long1, lat1, long2, lat2);
            if (distance <= SigninDistance) {
                signinAreas.add(signinArea);
            }
        }
        if (signinAreas.size() <= 0) {
            jrst.setMsg("超出打卡范围");
        } else {
            //打卡
            Collections.sort(signinAreas, new Comparator<MyySigninPoint>() {
                @Override
                public int compare(MyySigninPoint area1, MyySigninPoint area2) {
                    double longO = Double.parseDouble(myySigninVo.getLongitude());
                    double latO = Double.parseDouble(myySigninVo.getLatitude());
                    double long1 = Double.parseDouble(area1.getLongitude());
                    double lat1 = Double.parseDouble(area1.getLatitude());
                    double long2 = Double.parseDouble(area2.getLongitude());
                    double lat2 = Double.parseDouble(area2.getLatitude());
                    double distance1 = Tools.GetDistance(longO, latO, long1, lat1);
                    double distance2 = Tools.GetDistance(longO, latO, long2, lat2);
                    return Double.compare(distance1, distance2);
                }
            });
            MyySigninPoint point = null;
            MyySignin signin = null;
            for (int i = 0; i < signinAreas.size(); i++) {
                point = signinAreas.get(i);
                signin = myySigninService.findByPlanidAndPointid(plan.getId(), point.getId());
                if (signin == null) {
                    signin = new MyySignin();
                    signin.setLatitude(myySigninVo.getLatitude());
                    signin.setLongitude(myySigninVo.getLatitude());
                    signin.setPlanid(plan.getId());
                    signin.setPointid(point.getId());
                    signin.setResult("1");
                    signin.setUserid(userid);
                    signin.setSignintime(new Timestamp(new Date().getTime()));
                    if (myySigninService.saveOrUpdate(signin)) {
                        jrst.setMsg("打卡成功！");
                        outdata.put("result", true);
                        break;
                    }
                } else {
                    jrst.setMsg("此区域已打卡！");
                }
            }
        }

        List<MyySigninVo> list = null;
        if (pointList != null) {
            List<MyySignin> signinList = myySigninService.findAllByPlanidAndUserid(plan.getId(), userid);
            list = new ArrayList<MyySigninVo>();
            for (int i = 0; i < pointList.size(); i++) {
                MyySigninVo vo = new MyySigninVo();
                vo.setName(pointList.get(i).getName());
                vo.setLatitude(pointList.get(i).getLatitude());
                vo.setLongitude(pointList.get(i).getLongitude());
                vo.setAreaid(pointList.get(i).getParent());
                vo.setState("0");
                if (signinList != null) {
                    for (int j = 0; j < signinList.size(); j++) {
                        if (signinList.get(j).getPointid() == pointList.get(i).getId() &&
                                signinList.get(j).getResult().equalsIgnoreCase("1")) {
                            vo.setState("1");
                        }
                    }
                }
                list.add(vo);
            }
            outdata.put("pointlist", list);
        }
        jrst.setData(outdata);
        return jrst;
    }
}
