package com.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.po.Company;
@Service("companyMapper")
public interface ICompanyMapper {
	public List<Company> findAll();
	//��˾���
	public int save(Company company);
	//��ѯ����
	public Company findById(Integer cid);
	//��˾�޸�
	public int update(Company company);
}
