package com.moyuaninfo.waterinfo.service;

import com.moyuaninfo.waterinfo.model.MyySuggest;
import io.swagger.models.auth.In;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

public interface SuggestService {
    //条件查询问题咨询个数
    Integer countSuggest(MyySuggest myySuggest, Date startsDate, Date endDate);
}
