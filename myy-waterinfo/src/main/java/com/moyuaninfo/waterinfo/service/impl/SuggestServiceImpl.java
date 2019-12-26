package com.moyuaninfo.waterinfo.service.impl;

import com.moyuaninfo.waterinfo.dao.MyySuggestMapper;
import com.moyuaninfo.waterinfo.model.MyySuggest;
import com.moyuaninfo.waterinfo.model.MyySuggestExample;
import com.moyuaninfo.waterinfo.service.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SuggestServiceImpl implements SuggestService {

    @Autowired
    private MyySuggestMapper myySuggestMapper;

    /**
     * 通过条件查数据条数
     * */
    @Override
    public Integer countSuggest(MyySuggest myySuggest, Date startsDate ,Date endDate) {
        //初始化参数
        MyySuggestExample suggestExample = new MyySuggestExample();
        MyySuggestExample.Criteria criteria = suggestExample.createCriteria();
        //初始化查询条件
//        criteria.andAreaIdEqualTo(myySuggest.getAreaId());//河流id
        criteria.andSuggestTypeEqualTo(0);//问题类别（0为问题咨询）
        //是否处理

        //时间
        if(null != startsDate && null != endDate){
            criteria.andCreateTimeNotBetween(startsDate, endDate);
        }else {
            if(null!=myySuggest.getStatus() && !myySuggest.getStatus().equals("")){
                criteria.andStatusEqualTo(myySuggest.getStatus());
            }
        }
        //执行查询
        int countByExample = myySuggestMapper.countByExample(suggestExample);
        //返回
        return countByExample;
    }
}
