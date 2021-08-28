package com.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mapper.ICompanyMapper;
import com.mapper.IEmpMapper;
import com.mapper.IEmpProjectMapper;
import com.mapper.IPriceMapper;
import com.mapper.IProjectMapper;
@Service("MapperService")
public class MapperServiceUtil {
	@Resource(name="companyMapper")
	private ICompanyMapper commapper;
	@Resource(name="projectMapper")
	private IProjectMapper promapper;
	@Resource(name="empMapper")
	private IEmpMapper empmapper;
	@Resource(name="empProjectMapper")
	private IEmpProjectMapper emppromapper;
	@Resource(name="priceMapper")
	private IPriceMapper pricemapper;
	public ICompanyMapper getCommapper() {
		return commapper;
	}
	public void setCommapper(ICompanyMapper commapper) {
		this.commapper = commapper;
	}
	public IProjectMapper getPromapper() {
		return promapper;
	}
	public void setPromapper(IProjectMapper promapper) {
		this.promapper = promapper;
	}
	public IEmpMapper getEmpmapper() {
		return empmapper;
	}
	public void setEmpmapper(IEmpMapper empmapper) {
		this.empmapper = empmapper;
	}
	public IEmpProjectMapper getEmppromapper() {
		return emppromapper;
	}
	public void setEmppromapper(IEmpProjectMapper emppromapper) {
		this.emppromapper = emppromapper;
	}
	public IPriceMapper getPricemapper() {
		return pricemapper;
	}
	public void setPricemapper(IPriceMapper pricemapper) {
		this.pricemapper = pricemapper;
	}
	
}
