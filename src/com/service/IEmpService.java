package com.service;

import java.util.List;

import com.po.Emp;
import com.po.PageBean;

public interface IEmpService {
	  //��Ա���
	  public boolean save(Emp emp);
	  //��ҳ��ѯ
	  public List<Emp> findPageAll(PageBean pb);
	  //�ܼ�¼��
	  public Integer findMaxRows();
	  //ɾ��
	  public boolean delById(Integer eid); 
	  //��ѯ����
	  public Emp findById(Integer eid);
	  //�޸�
	  public boolean update(Emp emp);
}
