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
	//添加
	@Override
	public boolean save(Emp emp) {
		//1.处理员工添加
		int code=mapputil.getEmpmapper().save(emp);
		//2.处理该员工竞拍价和员工竞拍项目的添加
		if(code>0){
			//处理竞拍价和员工竞拍项目，必须获取该员工的编号
			Integer eid=mapputil.getEmpmapper().findMaxEid();
			/******竞拍价保存begin******/
			Price pc=new Price(eid,emp.getPrmoney());
			mapputil.getPricemapper().save(pc);
			/******竞拍价保存end******/
			/******员工竞拍项目保存begin******/
			  //获取员工竞拍项目编号数组
			String[] pids=emp.getPids();
			if(pids!=null&&pids.length>0){
				for(int i=0;i<pids.length;i++){
					EmpProject ept=new EmpProject(eid,Integer.parseInt(pids[i]));
					mapputil.getEmppromapper().save(ept);
				}
			}
			/******员工竞拍项目保存end******/
			return true;
		}
		return false;
	}
	//分页查询
	@Override
	public List<Emp> findPageAll(PageBean pb) {
		// TODO Auto-generated method stub
		return mapputil.getEmpmapper().findPageAll(pb.getPage(),pb.getRows());
	}
	//总记录数
	@Override
	public Integer findMaxRows() {
		// TODO Auto-generated method stub
		return mapputil.getEmpmapper().findMaxRows();
	}
	//删除
	@Override
	public boolean delById(Integer eid) {
		// TODO Auto-generated method stub
		//要删主表先删从表
		//删除竞拍价
		mapputil.getPricemapper().delByEid(eid);
		//删除竞拍项目
		mapputil.getEmppromapper().delByEid(eid);
		//删除竞拍人员
		int code=mapputil.getEmpmapper().delById(eid);
			if(code>0){
				return true;
			}
		return false;
	}
	//查询单个(编辑和详情共用)
	@Override
	public Emp findById(Integer eid) {
		// TODO Auto-generated method stub
		//获取竞拍人员
		Emp emp=mapputil.getEmpmapper().findById(eid);
		//获取竞拍价
		Price price=mapputil.getPricemapper().findByEid(eid);
		if(price!=null&&price.getPrmoney()!=null){
			emp.setPrmoney(price.getPrmoney());
		}
		//获取竞拍项目集合
		List<Project> lspr=mapputil.getEmppromapper().findByEid(eid);
		//处理项目集合编号
		String[] pids=new String[lspr.size()];
			for(int i=0;i<pids.length;i++){
			Project pt=lspr.get(i);
			//Integer转String
			pids[i]=pt.getPid().toString();
		}
			emp.setPids(pids);
			emp.setLspr(lspr);
			return emp;
	}
	//修改
	@Override
	public boolean update(Emp emp) {
		// TODO Auto-generated method stub
		int code=mapputil.getEmpmapper().update(emp);
		if(code>0){
		//获取原来的竞拍价
		Price price=mapputil.getPricemapper().findByEid(emp.getEid());
		if(price!=null&&price.getPrmoney()!=null){
			price.setPrmoney(emp.getPrmoney());
			//修改竞拍价
			mapputil.getPricemapper().updateByEid(price);
		}else{
			/******竞拍价保存begin******/
			Price pc=new Price(emp.getEid(),emp.getPrmoney());
			mapputil.getPricemapper().save(pc);
			/******竞拍价保存end******/
		}
		//获取原来的项目集合
		List<Project> lspr=mapputil.getEmppromapper().findByEid(emp.getEid());
		if(lspr!=null&&lspr.size()>0){
			//删除原有项目
			mapputil.getEmppromapper().delByEid(emp.getEid());
		}
		/******员工竞拍项目保存begin******/
		  //获取员工竞拍项目编号数组
		String[] pids=emp.getPids();
		if(pids!=null&&pids.length>0){
			for(int i=0;i<pids.length;i++){
				EmpProject ept=new EmpProject(emp.getEid(),Integer.parseInt(pids[i]));
				mapputil.getEmppromapper().save(ept);
			}
		}
		/******员工竞拍项目保存end******/
		return true;
	}
		return false;
}
}





