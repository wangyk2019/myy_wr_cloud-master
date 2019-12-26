package com.moyuan.cloud.service;

import com.moyuan.cloud.dao.NoticeDao;
import com.moyuan.cloud.pojo.JsonResult;
import com.moyuan.cloud.pojo.MyyNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {

    @Autowired
    NoticeDao noticeDao;

    public MyyNotice addObj(MyyNotice notice) {
        return noticeDao.save(notice);
    }

    public void deleteObj(long id) {
        noticeDao.updateOneById(id);
    }

    public MyyNotice getOne(long id) {
        MyyNotice a = noticeDao.findById(id);
        return a;
    }

    public List<MyyNotice> getListByUsers(String[] users) {
        List<MyyNotice> r = noticeDao.findAllByUsersIn(users);
        return r;
    }

    public List<MyyNotice> getAll() {
        List<MyyNotice> r = noticeDao.findAll();
        return r;
    }

//    public JsonResult<List<Notice>> getAllGG(int colleage) {
//        List r = noticeDao.findAllNoticeAndColleageDanger(colleage);
//        return new JsonResult<List<Notice>>(0,r);
//    }


}
