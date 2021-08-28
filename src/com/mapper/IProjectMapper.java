package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Project;
@Service("projectMapper")
public interface IProjectMapper {
	public List<Project> findAll();
}
