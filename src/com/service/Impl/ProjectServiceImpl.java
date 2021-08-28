package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Project;
import com.service.IProjectService;
import com.util.MapperServiceUtil;
@Service("ProjectBiz")
@Transactional
public class ProjectServiceImpl implements IProjectService{
	@Resource(name="MapperService")
	private MapperServiceUtil mapputil;
	
	public MapperServiceUtil getMapputil() {
		return mapputil;
	}

	public void setMapputil(MapperServiceUtil mapputil) {
		this.mapputil = mapputil;
	}
	public List<Project> findAll() {
		// TODO Auto-generated method stub
		return mapputil.getPromapper().findAll();
	}

}
