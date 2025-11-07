package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface EmpExprMapper {



    public void insertBatch(List<EmpExpr> exprList);

    void deleteByEmpIds(List<Integer> ids);
}
