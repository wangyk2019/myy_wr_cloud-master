package com.moyuaninfo.waterinfo.dao;

import com.moyuaninfo.waterinfo.model.MyySuggest;
import com.moyuaninfo.waterinfo.model.MyySuggestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyySuggestMapper {
    int countByExample(MyySuggestExample example);

    int deleteByExample(MyySuggestExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MyySuggest record);

    int insertSelective(MyySuggest record);

    List<MyySuggest> selectByExampleWithBLOBs(MyySuggestExample example);

    List<MyySuggest> selectByExample(MyySuggestExample example);

    MyySuggest selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MyySuggest record, @Param("example") MyySuggestExample example);

    int updateByExampleWithBLOBs(@Param("record") MyySuggest record, @Param("example") MyySuggestExample example);

    int updateByExample(@Param("record") MyySuggest record, @Param("example") MyySuggestExample example);

    int updateByPrimaryKeySelective(MyySuggest record);

    int updateByPrimaryKeyWithBLOBs(MyySuggest record);

    int updateByPrimaryKey(MyySuggest record);
}