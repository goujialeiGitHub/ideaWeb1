package com.mapper;

import org.springframework.stereotype.Service;

import com.po.Price;
@Service("priceMapper")
public interface IPriceMapper {
	//添加竞拍价
	public int save(Price price);
	//通过员工编号删除该员工竞拍价
	public int delByEid(Integer eid);
	//通过员工编号查询该员工竞拍价
	public Price findByEid(Integer eid);
	//通过员工编号修改该员工竞拍价
	public int updateByEid(Price price);
}
