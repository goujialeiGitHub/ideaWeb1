package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.service.ICompanyService;
import com.service.IEmpService;
import com.service.IProjectService;

@Service("BizService")
public class BizServiceUtil {
	@Resource(name="CompanyBiz")
	private ICompanyService com;
	@Resource(name="ProjectBiz")
	private IProjectService pro;
	@Resource(name="EmpBiz")
	private IEmpService emps;
	public ICompanyService getCom() {
		return com;
	}
	public void setCom(ICompanyService com) {
		this.com = com;
	}
	public IProjectService getPro() {
		return pro;
	}
	public void setPro(IProjectService pro) {
		this.pro = pro;
	}
	public IEmpService getEmps() {
		return emps;
	}
	public void setEmps(IEmpService emps) {
		this.emps = emps;
	}
	
}
