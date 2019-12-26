package com.moyuaninfo.liveplay.services;

import com.moyuaninfo.liveplay.entity.Livelog;
import com.moyuaninfo.liveplay.repository.LivelogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class LivelogService {
    @Autowired
    public LivelogRepository livelogRepository;

    @Transactional(rollbackOn = Exception.class)
    public void saveLivelog(Livelog log) {
        livelogRepository.save(log);
    }

    public Livelog findById(long id) {
        Optional<Livelog> opt = livelogRepository.findById(id);
        return opt.isPresent() ? opt.get() : null;
    }

    public Livelog findByUseridAndPlaystreamid(long userid, long playstreamid) {
        Optional<Livelog> opt = livelogRepository.findByUseridAndPlaystreamid(userid, playstreamid);
        return opt.isPresent() ? opt.get() : null;
    }
}
