package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.EmpProject;
import com.po.Project;
@Service("empProjectMapper")
public interface IEmpProjectMapper {
	//员工竞拍项目添加
	public int save(EmpProject empProject);
	//通过员工编号删该员工的竞拍项目
	public int delByEid(Integer eid);
	//通过员工编号查询该员工的竞拍项目
	public List<Project> findByEid(Integer eid);
}
