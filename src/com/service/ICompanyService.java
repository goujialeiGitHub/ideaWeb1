package com.service;

import java.util.List;

import com.po.Company;

public interface ICompanyService {
	public List<Company> findAll();
	//��˾���
	public boolean save(Company company);
	//��ѯ����
	public Company findById(Integer cid);
	//��˾�޸�
	public boolean update(Company company);
}
