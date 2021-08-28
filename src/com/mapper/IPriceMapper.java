package com.mapper;

import org.springframework.stereotype.Service;

import com.po.Price;
@Service("priceMapper")
public interface IPriceMapper {
	//��Ӿ��ļ�
	public int save(Price price);
	//ͨ��Ա�����ɾ����Ա�����ļ�
	public int delByEid(Integer eid);
	//ͨ��Ա����Ų�ѯ��Ա�����ļ�
	public Price findByEid(Integer eid);
	//ͨ��Ա������޸ĸ�Ա�����ļ�
	public int updateByEid(Price price);
}
