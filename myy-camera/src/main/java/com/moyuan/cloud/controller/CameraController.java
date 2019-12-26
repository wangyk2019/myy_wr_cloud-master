package com.moyuan.cloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.moyuan.cloud.Tools;
import com.moyuan.cloud.VO.MapCameras;
import com.moyuan.cloud.VO.MyyCameraIn;
import com.moyuan.cloud.VO.ShowCameras;
import com.moyuan.cloud.feignClient.BaseServerClient;
import com.moyuan.cloud.feignClient.CloudNettyClient;
import com.moyuan.cloud.feignClient.DangerClient;
import com.moyuan.cloud.pojo.FindCameraByParams;
import com.moyuan.cloud.pojo.JsonResult;
import com.moyuan.cloud.pojo.MyyAreabottom;
import com.moyuan.cloud.pojo.MyyAreaone;
import com.moyuan.cloud.pojo.MyyAreatwo;
import com.moyuan.cloud.pojo.MyyCamera;
import com.moyuan.cloud.service.MyyCameraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "摄像头管理", tags = "摄像头管理接口")
@EnableScheduling
public class CameraController {
    @Autowired
    MyyCameraService myyCameraService;
    @Autowired
    BaseServerClient baseServerClient;
    @Autowired
    DangerClient dangerClient;
    @Autowired
    CloudNettyClient cloudNettyClient;

    @ApiOperation(value = "添加摄像头信息", notes = "", httpMethod = "POST")
    @PostMapping(value = "/addOne")
    public JsonResult<MyyCamera> save(@RequestBody @Valid MyyCameraIn myyCameraIn) {

        String link = Tools.getRtspUrl(myyCameraIn.getManufacture(), myyCameraIn.getLinksource(), myyCameraIn.getUsername(), myyCameraIn.getPassword(),
                myyCameraIn.getIp(), "", "", "", myyCameraIn.getChannel() + "");

        JsonResult<MyyCamera> jsrt = null;
        MyyCamera myyCamera = null;
        try {
            if (myyCameraIn.getId() != 0) {
                myyCamera = myyCameraService.findById(myyCameraIn.getId());
                myyCamera.setName(myyCameraIn.getName());
                myyCamera.setIp(myyCameraIn.getIp());
                myyCamera.setPassword(myyCameraIn.getPassword());
                myyCamera.setUsername(myyCameraIn.getUsername());
                myyCamera.setLatitude(myyCameraIn.getLatitude());
                myyCamera.setLongitude(myyCameraIn.getLongitude());
                myyCamera.setDeviceid(myyCameraIn.getDeviceid());
                myyCamera.setCameratype(myyCameraIn.getCameratype());

                myyCamera.setStatus("1");

                myyCamera.setLink(link);
                myyCamera.setChannel((myyCameraIn.getChannel() + 32) + "");
            } else {
                ArrayList<Long> al = new ArrayList<>();
                al.add(myyCameraIn.getAreabottomid());

                List<MyyCamera> camexist = myyCameraService.findAllByParams(al, "", "", link);
                if (camexist.size() > 0) {
                    return new JsonResult<MyyCamera>(1, "发现重复设置的摄像头，无法保存数据");
                }

                myyCamera = new MyyCamera();
                myyCamera.setLink(link);
                myyCamera.setChannel((myyCameraIn.getChannel() + 32) + "");
                myyCamera.setLinktype("rtsp");
                myyCamera.setLinkstatus("2");
                myyCamera.setState("1");

                myyCamera.setAreabottomid(myyCameraIn.getAreabottomid());
                myyCamera.setName(myyCameraIn.getName());
                myyCamera.setIp(myyCameraIn.getIp());
                myyCamera.setPassword(myyCameraIn.getPassword());
                myyCamera.setUsername(myyCameraIn.getUsername());
                myyCamera.setLatitude(myyCameraIn.getLatitude());
                myyCamera.setLongitude(myyCameraIn.getLongitude());
                myyCamera.setDeviceid(myyCameraIn.getDeviceid());
                myyCamera.setCameratype(myyCameraIn.getCameratype());

                myyCamera.setStatus("1");


            }
            String streamname = null;
            if ("DVR".equalsIgnoreCase(myyCameraIn.getLinksource())) {
                streamname = "cam" + myyCameraIn.getIp().replace(".", "") + myyCamera.getChannel();
            } else {
                streamname = "cam" + myyCameraIn.getIp().replace(".", "");
            }
            myyCamera.setStreamname(streamname);

            JsonResult<MyyAreabottom> myyAreabottomJsonResult = baseServerClient.findAreabottom(myyCameraIn.getAreabottomid());
            if (myyAreabottomJsonResult.getCode()==0 && myyAreabottomJsonResult.getData() != null){
                return new JsonResult(1, "底层区域信息有误");
            }
            JsonResult<MyyAreatwo> myyAreatwoJsonResult = baseServerClient.findAreatwo(myyAreabottomJsonResult.getData().getAreatwoid());
            JsonResult<MyyAreaone> myyAreaoneJsonResult = baseServerClient.findAreaone(myyAreatwoJsonResult.getData().getAreaoneid());
            myyCamera.setLocation(myyAreaoneJsonResult.getData().getName()+"_"+myyAreatwoJsonResult.getData().getName()+"_"+myyAreabottomJsonResult.getData().getName());

            MyyCamera cam = myyCameraService.saveCa(myyCamera);

//            testCamera(cam);
            if (myyAreaoneJsonResult.getCode() == 0) {
                dangerClient.addCamWarn_cam(myyAreaoneJsonResult.getData().getDistrictid(), cam.getId());
            }

            jsrt = new JsonResult(0, cam);
        } catch (Exception e) {
            jsrt = new JsonResult(1, e.getClass().getName() + ":" + e.getMessage());
        }

        return jsrt;
    }

    @ApiOperation(value = "查询摄像头信息", notes = "跟id查", httpMethod = "POST")
    @PostMapping(value = "/findById")
    public JsonResult<MyyCamera> findById(@RequestParam(value = "id") long id) {
        MyyCamera myyCamera = myyCameraService.findById(id);
        return new JsonResult(0, myyCamera);
    }

    @ApiOperation(value = "查询摄像头信息", notes = "多条件查询", httpMethod = "POST")
    @PostMapping(value = "/findByParams")
    public JsonResult<List<MyyCamera>> findByParams(@RequestBody FindCameraByParams findCameraByParams) {
        if (findCameraByParams.getStatus() == null) {
            findCameraByParams.setStatus("");
        }
        if (findCameraByParams.getLink() == null) {
            findCameraByParams.setLink("");
        }
        if (findCameraByParams.getIp() == null) {
            findCameraByParams.setIp("");
        }

        ArrayList<Long> longs = new ArrayList<>();
        if (findCameraByParams.getAreabottom() == null) {
            findCameraByParams.setAreabottom(longs);
        } else {
            longs = findCameraByParams.getAreabottom();
        }
        longs.add(0L);
        List<MyyCamera> myyCamera = myyCameraService.findAllByParams(findCameraByParams.getAreabottom(), findCameraByParams.getStatus(),
                findCameraByParams.getIp(), findCameraByParams.getLink());
        return new JsonResult(0, myyCamera);
    }

    @ApiOperation(value = "组织id查询摄像头信息")
    @PostMapping(value = "/findAllByDistrict")
    public JsonResult<List<MyyCamera>> findAllByDistrict(@RequestParam(value = "id") long id) {
        JsonResult<List<MyyCamera>> jsonResult = null;
        List<MyyCamera> myyCamera = myyCameraService.findAllByDistrict(id);
        if (myyCamera.size() == 0) {
            jsonResult = new JsonResult<>(0, JsonResult.NoDataMSG, myyCamera);
        } else {
            jsonResult = new JsonResult<>(0, myyCamera);
        }
        return jsonResult;
    }

    @ApiOperation(value = "区域3级id查询摄像头信息")
    @PostMapping(value = "/findAllByAreabottomid")
    public JsonResult<List<MyyCamera>> findAllByAreabottomid(@RequestParam(value = "id") long id) {
        JsonResult<List<MyyCamera>> jsonResult = null;
        List<MyyCamera> myyCamera = myyCameraService.findAllByAreabottomid(id);
        if (myyCamera.size() == 0) {
            jsonResult = new JsonResult<>(0, JsonResult.NoDataMSG, myyCamera);
        } else {
            jsonResult = new JsonResult<>(0, myyCamera);
        }
        return jsonResult;
    }


    @ApiOperation(value = "测试摄像头", notes = "测试摄像头链接，需要组织ID，区域ID和摄像头IP及摄像头通道", httpMethod = "POST")
    @PostMapping(value = "/testCamera")
    public JsonResult<String> testCamera(@RequestBody MyyCamera cameravo) {
        JsonResult<String> jsrt = null;
        try {

            MyyCamera myyCamera = cameravo;
            JsonResult<MyyAreabottom> myyAreabottomJsonResult = baseServerClient.findAreabottom(myyCamera.getAreabottomid());
            JsonResult<MyyAreatwo> myyAreatwoJsonResult = baseServerClient.findAreatwo(myyAreabottomJsonResult.getData().getAreatwoid());
            JsonResult<MyyAreaone> myyAreaoneJsonResult = baseServerClient.findAreaone(myyAreatwoJsonResult.getData().getAreaoneid());

            JSONObject taskobj = new JSONObject();
            taskobj.put("client", "org-" + myyAreaoneJsonResult.getData().getDistrictid());
            taskobj.put("task", "testcamera");
            taskobj.put("api", "/camera/testCamera");
            taskobj.put("apiclient", "localservice");
            taskobj.put("msgtype", "request1");
            taskobj.put("ip", myyCamera.getIp());
            taskobj.put("camid", myyCamera.getId());
            taskobj.put("channel", myyCamera.getChannel());

            //TODO 通过netty发送测试摄像头请求
//            RestTemplate restTemplate = new RestTemplate();
//            jsrt = restTemplate.postForObject(MyConstants.NETTY_SEND, taskobj, JsonResult.class);
            cloudNettyClient.nettySend(taskobj);

            jsrt = new JsonResult<>(0);

        } catch (Exception e) {
            jsrt = new JsonResult<>(1, e.getClass().getName() + ":" + e.getMessage(), "");
        }
        return jsrt;
    }

    @Scheduled(cron = "0 0 0/6 * * ?")
    public void autoTestCam() {
        JsonResult<String> jsrt = null;
        try {
            ArrayList<Long> arrayList = new ArrayList<>();
            arrayList.add(0L);

            List<MyyCamera> cameras = myyCameraService.findAllByParams(arrayList, "", "", "");
            for (MyyCamera myyCamera : cameras) {
                JsonResult<MyyAreabottom> myyAreabottomJsonResult = baseServerClient.findAreabottom(myyCamera.getAreabottomid());
                JsonResult<MyyAreatwo> myyAreatwoJsonResult = baseServerClient.findAreatwo(myyAreabottomJsonResult.getData().getAreatwoid());
                JsonResult<MyyAreaone> myyAreaoneJsonResult = baseServerClient.findAreaone(myyAreatwoJsonResult.getData().getAreaoneid());

                JSONObject taskobj = new JSONObject();
                taskobj.put("client", "org-" + myyAreaoneJsonResult.getData().getDistrictid());
                taskobj.put("task", "testcamera");
                taskobj.put("api", "/camera/testCamera");
                taskobj.put("apiclient", "localservice");
                taskobj.put("msgtype", "request1");
                taskobj.put("ip", myyCamera.getIp());
                taskobj.put("camid", myyCamera.getId());
                taskobj.put("channel", myyCamera.getChannel());

                System.out.println(taskobj);

                //TODO 通过netty发送测试摄像头请求
//                RestTemplate restTemplate = new RestTemplate();
//                jsrt = restTemplate.postForObject(MyConstants.NETTY_SEND, taskobj, JsonResult.class);
                cloudNettyClient.nettySend(taskobj);
                jsrt = new JsonResult<>(0);

                System.out.println(jsrt);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @ApiOperation(value = "删除摄像头", notes = "通过id", httpMethod = "POST")
    @PostMapping(value = "/deleteOne")
    @Transactional
    public JsonResult deleteOne(@RequestParam(value = "id") long id) {
        JsonResult jsrt = null;
        try {
            MyyCamera camera = myyCameraService.findById(id);
            camera.setState("0");
            myyCameraService.saveCa(camera);

            JsonResult<MyyAreabottom> myyAreabottomJsonResult = baseServerClient.findAreabottom(camera.getAreabottomid());
            JsonResult<MyyAreatwo> myyAreatwoJsonResult = baseServerClient.findAreatwo(myyAreabottomJsonResult.getData().getAreatwoid());
            JsonResult<MyyAreaone> myyAreaoneJsonResult = baseServerClient.findAreaone(myyAreatwoJsonResult.getData().getAreaoneid());
            if (myyAreaoneJsonResult.getCode() != 1) {
                dangerClient.delayCheck(myyAreaoneJsonResult.getData().getDistrictid());
            }
            jsrt = new JsonResult(0, "操作成功！");
        } catch (Exception e) {
            jsrt = new JsonResult(1, e.getClass().getName() + ":" + e.getMessage());
        }
        return jsrt;
    }


    @ApiOperation(value = "测试摄像头的回传", notes = "", httpMethod = "POST")
    @PostMapping(value = "/testCamResult")
    @Transactional
    public JsonResult testCamResult(@RequestBody String msg) {
        JSONObject rceobj = (JSONObject) JSONObject.parse(msg);
        System.out.println(rceobj);

        JsonResult jsonResult = null;
        try {
            System.out.println(rceobj.getLong("camid"));
            System.out.println(rceobj.getString("result"));//"true"
            MyyCamera myyCamera = myyCameraService.findById(rceobj.getLong("camid"));
            if (myyCamera != null) {
                if ("true".equals(rceobj.getString("result"))) {
                    myyCamera.setStatus("1");
                } else {
                    myyCamera.setStatus("0");
                }
                myyCameraService.saveCa(myyCamera);
            }
            jsonResult = new JsonResult(0, myyCamera);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            jsonResult = new JsonResult(1, e.getMessage());
        }
        return jsonResult;
    }




////////////////////////////////////////////////////////////////////////////////////////////////
//    @ApiOperation(value = "统计摄像头", notes = "通过传入的colleageID统计有效摄像头和故障摄像头")
//    @RequestMapping(value = "/findCountByColleageid", method = RequestMethod.POST)
//    public JsonResult<HashMap> findCountByColleageid(@RequestBody CameraVO cameravo) {
//        JsonResult<HashMap> jsrt = null;
//        try {
//            String temp = null;
//            HashMap<String,String> cameramap = new HashMap<>();
//            List<Object> list = myyCameraService.findCountOfCameraValidByColleage(cameravo.getColleageid());
//            for (int i = 0; i < list.size(); i++) {
//                Object[] datamap = (Object[]) list.get(i);
//                for(int j = 0; j < datamap.length; j++) {
//                    if(j%2==0) {
//                        temp = (String.valueOf(datamap[j])).equalsIgnoreCase("1")?"正常":"故障";
//                        cameramap.put(temp, "0");
//                    }else {
//                        cameramap.put(temp,String.valueOf(datamap[j]));
//                    }
//                }
//            }
//            jsrt = new JsonResult<HashMap>(0,"ok",cameramap);
//        } catch (Exception e) {
//            jsrt =new JsonResult<HashMap>(1,e.getClass().getName() + ":" + e.getMessage());
//        }
//        return jsrt;
//    }

    @ApiOperation(value = "查找好坏摄像头", notes = "高校ID")
    @RequestMapping(value = "/findAllByCameraAndStatus", method = RequestMethod.POST)
    public JsonResult<List<MyyCamera>> findAllByCameraAndStatus(@RequestParam(value = "colleageid") long colleageid,
                                                                    @RequestParam(value = "status") String status) {
        JsonResult<List<MyyCamera>> jsrt = null;
        try {
            List<MyyCamera> camera = myyCameraService.findAllByCameraAndStatus(colleageid,status);
            jsrt = new JsonResult<>(0,"ok", camera);
        } catch (Exception e) {
            jsrt = new JsonResult<>(1,e.getClass().getName() + ":" + e.getMessage());
        }
        return jsrt;
    }



    /////////////////////////////////////////////////////////////////////////////////////////////
    @ApiOperation(value = "摄像头列表")
    @RequestMapping(value = "/findCameras_show", method = RequestMethod.POST)
    public JsonResult<List<ShowCameras>> findCameras_show(@RequestParam(value = "orgid") int orgid) {
        JsonResult<List<ShowCameras>> jsrt = null;
        try {
            List<ShowCameras> showCameras = new ArrayList<>();

            List list = myyCameraService.findAllByOrgid(orgid);
            for (Object o : list){
                int length = Array.getLength(o);
                System.out.println(length);
                System.out.println(String.valueOf(Array.get(o, 1)));
                ShowCameras sc = new ShowCameras();
                sc.setCamname(String.valueOf(Array.get(o, 0)));
                sc.setCamid(Long.parseLong(String.valueOf(Array.get(o, 1))));
                sc.setAreaonename(String.valueOf(Array.get(o, 2)));
                sc.setAreatwoname(String.valueOf(Array.get(o, 3)));
                sc.setDangernum(Long.parseLong(String.valueOf(Array.get(o, 4))));
                showCameras.add(sc);
            }
            jsrt = new JsonResult<>(0,"ok",showCameras);
        } catch (Exception e) {
            jsrt = new JsonResult<>(1,e.getClass().getName() + ":" + e.getMessage());
        }
        return jsrt;
    }
    @ApiOperation(value = "地图摄像头列表")
    @RequestMapping(value = "/mapCameras_show", method = RequestMethod.POST)
    public JsonResult<List<MapCameras>> mapCameras_show(@RequestParam(value = "orgid") long orgid) {
        JsonResult<List<MapCameras>> jsrt = null;
        try {
            List<MapCameras> mapCameras = new ArrayList<>();
            List<MyyCamera> myyCameras = myyCameraService.findAllByDistrict(orgid);
            for (MyyCamera camera : myyCameras){
                MapCameras mapCamera = new MapCameras();
                mapCamera.setCamid(camera.getId());
                mapCamera.setLatitude(camera.getLatitude());
                mapCamera.setLongitude(camera.getLongitude());
                mapCameras.add(mapCamera);
            }
            jsrt = new JsonResult<>(0,"ok",mapCameras);
        } catch (Exception e) {
            jsrt = new JsonResult<>(1,e.getClass().getName() + ":" + e.getMessage());
        }
        return jsrt;
    }

    /**
     * 数据驾驶舱接口
     * 查询摄像头列表
     * @param areaoneid  河道id
     */
    @ApiOperation(value = "根据河道id查询摄像头列表（数据驾驶舱接口）")
    @RequestMapping(value = "/show_camerasByAreaid", method = RequestMethod.POST)
    public JsonResult<List<ShowCameras>> show_camerasByAreaid(long areaoneid) {
        JsonResult<List<ShowCameras>> jsrt = null;
        try {
            //封装返回信息
            List<ShowCameras> showCameras = new ArrayList<>();

            //查询摄像头列表
            List list = myyCameraService.findAllByAreaoneid(areaoneid);
            for (Object o : list){
                int length = Array.getLength(o);
                System.out.println(length);

                //数据转换放进封装类中
                ShowCameras sc = new ShowCameras();
                sc.setCamname(String.valueOf(Array.get(o, 0)));
                sc.setCamid(Long.parseLong(String.valueOf(Array.get(o, 1))));
                sc.setAreaonename(String.valueOf(Array.get(o, 2)));
                sc.setAreatwoname(String.valueOf(Array.get(o, 3)));
                sc.setLongitude(String.valueOf(Array.get(o, 4)));
                sc.setLatitude(String.valueOf(Array.get(o, 5)));
                sc.setDangernum(Long.parseLong(String.valueOf(Array.get(o, 6))));
                showCameras.add(sc);
            }
            jsrt = new JsonResult<>(0,"ok",showCameras);
        } catch (Exception e) {
            jsrt = new JsonResult<>(1,e.getClass().getName() + ":" + e.getMessage());
        }
        return jsrt;
    }
}
