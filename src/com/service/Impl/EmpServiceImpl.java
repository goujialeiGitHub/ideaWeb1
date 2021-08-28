package com.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.po.Emp;
import com.po.EmpProject;
import com.po.PageBean;
import com.po.Price;
import com.po.Project;
import com.service.IEmpService;
import com.util.MapperServiceUtil;
@Service("EmpBiz")
public class EmpServiceImpl implements IEmpService {
	@Resource(name="MapperService")
	private MapperServiceUtil mapputil;
	
	public MapperServiceUtil getMapputil() {
		return mapputil;
	}

	public void setMapputil(MapperServiceUtil mapputil) {
		this.mapputil = mapputil;
	}
	//���
	@Override
	public boolean save(Emp emp) {
		//1.����Ա�����
		int code=mapputil.getEmpmapper().save(emp);
		//2.�����Ա�����ļۺ�Ա��������Ŀ�����
		if(code>0){
			//�����ļۺ�Ա��������Ŀ�������ȡ��Ա���ı��
			Integer eid=mapputil.getEmpmapper().findMaxEid();
			/******���ļ۱���begin******/
			Price pc=new Price(eid,emp.getPrmoney());
			mapputil.getPricemapper().save(pc);
			/******���ļ۱���end******/
			/******Ա��������Ŀ����begin******/
			  //��ȡԱ��������Ŀ�������
			String[] pids=emp.getPids();
			if(pids!=null&&pids.length>0){
				for(int i=0;i<pids.length;i++){
					EmpProject ept=new EmpProject(eid,Integer.parseInt(pids[i]));
					mapputil.getEmppromapper().save(ept);
				}
			}
			/******Ա��������Ŀ����end******/
			return true;
		}
		return false;
	}
	//��ҳ��ѯ
	@Override
	public List<Emp> findPageAll(PageBean pb) {
		// TODO Auto-generated method stub
		return mapputil.getEmpmapper().findPageAll(pb.getPage(),pb.getRows());
	}
	//�ܼ�¼��
	@Override
	public Integer findMaxRows() {
		// TODO Auto-generated method stub
		return mapputil.getEmpmapper().findMaxRows();
	}
	//ɾ��
	@Override
	public boolean delById(Integer eid) {
		// TODO Auto-generated method stub
		//Ҫɾ������ɾ�ӱ�
		//ɾ�����ļ�
		mapputil.getPricemapper().delByEid(eid);
		//ɾ��������Ŀ
		mapputil.getEmppromapper().delByEid(eid);
		//ɾ��������Ա
		int code=mapputil.getEmpmapper().delById(eid);
			if(code>0){
				return true;
			}
		return false;
	}
	//��ѯ����(�༭�����鹲��)
	@Override
	public Emp findById(Integer eid) {
		// TODO Auto-generated method stub
		//��ȡ������Ա
		Emp emp=mapputil.getEmpmapper().findById(eid);
		//��ȡ���ļ�
		Price price=mapputil.getPricemapper().findByEid(eid);
		if(price!=null&&price.getPrmoney()!=null){
			emp.setPrmoney(price.getPrmoney());
		}
		//��ȡ������Ŀ����
		List<Project> lspr=mapputil.getEmppromapper().findByEid(eid);
		//������Ŀ���ϱ��
		String[] pids=new String[lspr.size()];
			for(int i=0;i<pids.length;i++){
			Project pt=lspr.get(i);
			//IntegerתString
			pids[i]=pt.getPid().toString();
		}
			emp.setPids(pids);
			emp.setLspr(lspr);
			return emp;
	}
	//�޸�
	@Override
	public boolean update(Emp emp) {
		// TODO Auto-generated method stub
		int code=mapputil.getEmpmapper().update(emp);
		if(code>0){
		//��ȡԭ���ľ��ļ�
		Price price=mapputil.getPricemapper().findByEid(emp.getEid());
		if(price!=null&&price.getPrmoney()!=null){
			price.setPrmoney(emp.getPrmoney());
			//�޸ľ��ļ�
			mapputil.getPricemapper().updateByEid(price);
		}else{
			/******���ļ۱���begin******/
			Price pc=new Price(emp.getEid(),emp.getPrmoney());
			mapputil.getPricemapper().save(pc);
			/******���ļ۱���end******/
		}
		//��ȡԭ������Ŀ����
		List<Project> lspr=mapputil.getEmppromapper().findByEid(emp.getEid());
		if(lspr!=null&&lspr.size()>0){
			//ɾ��ԭ����Ŀ
			mapputil.getEmppromapper().delByEid(emp.getEid());
		}
		/******Ա��������Ŀ����begin******/
		  //��ȡԱ��������Ŀ�������
		String[] pids=emp.getPids();
		if(pids!=null&&pids.length>0){
			for(int i=0;i<pids.length;i++){
				EmpProject ept=new EmpProject(emp.getEid(),Integer.parseInt(pids[i]));
				mapputil.getEmppromapper().save(ept);
			}
		}
		/******Ա��������Ŀ����end******/
		return true;
	}
		return false;
}
}





