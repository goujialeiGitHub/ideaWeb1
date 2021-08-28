package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.po.Emp;

@Service("empMapper")
public interface IEmpMapper {
	//人员添加
	public int save(Emp emp); 
	//获取id最大的编号
	public Integer findMaxEid();
	//分页查询
	public List<Emp> findPageAll(@Param(value = "page") Integer page, @Param(value = "rows") Integer rows);
	//总记录数
	public Integer findMaxRows();
	//删除
	public int delById(Integer eid);
	//查询单个
	public Emp findById(Integer eid);
	//修改
	public int update(Emp emp);
}
