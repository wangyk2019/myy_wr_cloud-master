package com.moyuaninfo.liveplay.services;

import com.moyuaninfo.liveplay.entity.Camera;
import com.moyuaninfo.liveplay.entity.JsonResult;
import com.moyuaninfo.liveplay.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
public class CameraService {

    @Autowired
    private CameraRepository cameraRep;

    @Transactional(rollbackOn = Exception.class)
    public JsonResult<Camera> saveCamera(Camera camera) {
        return new JsonResult<Camera>(0, cameraRep.save(camera));
    }

    public Camera findByAreaAndIpAndChannel(Long area, String ip, String channel) {
        return cameraRep.findByAreaidAndIpAndChannel(area, ip, channel);
    }

    public Camera findByIp(String ip) {
        return cameraRep.findByIp(ip);
    }

    public Camera findByLink(String link) {
        return cameraRep.findByLink(link);
    }

    public Camera findByIpAndLinkAndArea(String ip,String link,long areaid) {
        return cameraRep.findByIpAndLinkAndAreaid(ip,link,areaid);
    }

    public List<Camera> findAll() {
        Iterable<Camera> cameraite = cameraRep.findAll();
        Iterator<Camera> iterator = cameraite.iterator();
        List<Camera> cameralist = new ArrayList<Camera>();
        while (iterator.hasNext()) {
            cameralist.add(iterator.next());
        }
        return cameralist;
    }

    public List<Camera> findByAreaId(Long areaid) {
        return cameraRep.findAllByAreaid(areaid);
    }

    public List<Camera> findByAreaIds(ArrayList<Long> areaids) {
        return cameraRep.findAllByAreaidIn(areaids);
    }

    public Camera findOne(Long id) {
        Optional<Camera> opt = cameraRep.findById(id);
        return opt.isPresent() ? opt.get() : null;
    }

//    public List<Object> findCountOfCameraValidByColleage(Long colleageid) {
//        return cameraRep.findCountOfCameraValidByColleage(colleageid);
//    }

//    public List<Camera> findAllByAreaAndStatus(int colleageid, String status) {
//        return cameraRep.findAllByAreaAndStatus(colleageid, status);
//    }

//    public List<Camera> findByAreaAndStatusAndLinkstatus(long labid, String status, String linkstatus) {
//        return cameraRep.findByAreaAndStatusAndLinkstatus(labid, status, linkstatus);
//    }

//    public Camera deleteOne(long id) {
//        return cameraRep.deleteById(id);
//    }

//    public List<Camera> findAllByLab(int colleageid, String buildname) {
//        return cameraRep.findAllByLab(colleageid, buildname);
//    }
}
