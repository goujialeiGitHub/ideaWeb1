package com.service;

import java.util.List;

import com.po.Company;

public interface ICompanyService {
	public List<Company> findAll();
	//公司添加
	public boolean save(Company company);
	//查询单个
	public Company findById(Integer cid);
	//公司修改
	public boolean update(Company company);
}
