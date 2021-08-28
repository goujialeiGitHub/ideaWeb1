package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.EmpProject;
import com.po.Project;
@Service("empProjectMapper")
public interface IEmpProjectMapper {
	//Ա��������Ŀ���
	public int save(EmpProject empProject);
	//ͨ��Ա�����ɾ��Ա���ľ�����Ŀ
	public int delByEid(Integer eid);
	//ͨ��Ա����Ų�ѯ��Ա���ľ�����Ŀ
	public List<Project> findByEid(Integer eid);
}
