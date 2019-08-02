package com.smxknife.mybatis.springboot.dao;

import com.smxknife.mybatis.springboot.model.TBString;
import com.smxknife.mybatis.springboot.model.TBStringExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TBStringMapper {
    long countByExample(TBStringExample example);

    int deleteByExample(TBStringExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TBString record);

    int insertSelective(TBString record);

    List<TBString> selectByExample(TBStringExample example);

    TBString selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TBString record, @Param("example") TBStringExample example);

    int updateByExample(@Param("record") TBString record, @Param("example") TBStringExample example);

    int updateByPrimaryKeySelective(TBString record);

    int updateByPrimaryKey(TBString record);
}