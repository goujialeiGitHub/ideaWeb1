package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Company;
@Service("companyMapper")
public interface ICompanyMapper {
	public List<Company> findAll();
	//公司添加
	public int save(Company company);
	//查询单个
	public Company findById(Integer cid);
	//公司修改
	public int update(Company company);
}
