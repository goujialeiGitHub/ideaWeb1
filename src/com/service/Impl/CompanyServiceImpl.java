package com.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.po.Company;
import com.service.ICompanyService;
import com.util.MapperServiceUtil;
@Service("CompanyBiz")
@Transactional
public class CompanyServiceImpl implements ICompanyService {
	@Resource(name="MapperService")
	private MapperServiceUtil mapputil;
	
	public MapperServiceUtil getMapputil() {
		return mapputil;
	}

	public void setMapputil(MapperServiceUtil mapputil) {
		this.mapputil = mapputil;
	}
	
	@Override
	public List<Company> findAll() {
		// TODO Auto-generated method stub
		return mapputil.getCommapper().findAll();
	}
	@Override
	public boolean save(Company company) {
		// TODO Auto-generated method stub
		int code=mapputil.getCommapper().save(company);
		if(code>0&&company!=null){
			return true;
		}
		return false;
		
	}

	@Override
	public boolean update(Company company) {
		// TODO Auto-generated method stub
		int code=mapputil.getCommapper().update(company);
		if(code>0&&company!=null){
			return true;
		}
		return false;
	}

	@Override
	public Company findById(Integer cid) {
		// TODO Auto-generated method stub
		Company company=mapputil.getCommapper().findById(cid);
		
		return company;
	}
}
