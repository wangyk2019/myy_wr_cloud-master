package com.moyuaninfo.liveplay.services;

import com.moyuaninfo.liveplay.entity.Livestream;
import com.moyuaninfo.liveplay.repository.LiveStreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LiveStreamService {

    @Autowired
    private LiveStreamRepository livestreamrepository;

    @Transactional(rollbackOn = Exception.class)
    public void saveLiveStream(Livestream entity) {
        livestreamrepository.save(entity);
    }

    public List<Livestream> findByAreaAndStatus(Long areaid, String status) {
        List<Livestream> list = livestreamrepository.findByAreaAndStatus(areaid, status);
        return list.size() > 0 ? list : null;
    }

    public List<Livestream> findLiveStream(String streamname) {
        List<Livestream> list = livestreamrepository.findByStreamname(streamname);
        return list.size() > 0 ? list : null;
    }

    public List<Livestream> findByStreamnameAndStatusOrderByStreamname(String streamname, String status) {
        return livestreamrepository.findByStreamnameAndStatusOrderByStreamname(streamname, status);
    }

    @Transactional(rollbackOn = Exception.class)
    public void updateStatusByPusherid(String status, String pusherid) {
        livestreamrepository.updateStatusByPusherid(status, pusherid);
    }

    public Livestream findByCameraid( Long cameraid){
        return livestreamrepository.findByCameraid(cameraid);
    }
}
