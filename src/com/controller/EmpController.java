package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.po.Company;
import com.po.Emp;
import com.po.PageBean;
import com.po.Project;
import com.util.AjAxUtils;
import com.util.BizServiceUtil;
@Controller
public class EmpController {
	@Resource(name="BizService")
	private BizServiceUtil bizutil;

	public BizServiceUtil getBizutil() {
		return bizutil;
	}

	public void setBizutil(BizServiceUtil bizutil) {
		this.bizutil = bizutil;
	}
	//��Ա���
	@RequestMapping(value="save_emp.do")
	 public String save(HttpServletRequest request,HttpServletResponse response,Emp emp){
		//��վ��·��
		String realpath=request.getRealPath("/");
		/*****************�ļ��ϴ�begin*******************/
		//��ȡ�ϴ���Ƭ����
		MultipartFile multipartFile=emp.getPic();
		if(multipartFile!=null&&!multipartFile.isEmpty()){
			//��ȡ�ϴ��ļ�����
			String fname=multipartFile.getOriginalFilename();
			//����
			if(fname.lastIndexOf(".")!=-1){
				String ext=fname.substring(fname.lastIndexOf("."));
				if(ext.equalsIgnoreCase(".jpg")){
					String newfname=new Date().getTime()+ext;
					//�����ļ�����ָ���ļ�·��
					File dostFile=new File(realpath+"/uppic/"+newfname);
					try {
						//�ϴ�(�����󴫵ݵ��ļ����ݸ���һ�ݵ��ղ����ɵ��ļ���)
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),dostFile);
						emp.setPhoto(newfname);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		/*****************�ļ��ϴ�end*******************/
		boolean flag=bizutil.getEmps().save(emp);
		  if(flag){
		  	//��������ִ�н����json�ַ������ص�ǰ̨
		  	AjAxUtils.printString(response, "1");
		  }else{
			AjAxUtils.printString(response, "0"); 
		  }
		return null;
	 }
	//��ѯ������Ŀ����
	@RequestMapping(value="doinit_emp.do")
	private String doinit(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map=new HashMap<String,Object>();
		List<Company> lsct=bizutil.getCom().findAll();
		List<Project> lspr=bizutil.getPro().findAll();
		map.put("lsct", lsct);
		map.put("lspr", lspr);
		//���˲���Ҫ������
		PropertyFilter propertyFilter=AjAxUtils.filterProperts("birthday","pic");
		//����ѯ�������ת��Ϊjson�ַ���  ��������
		String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		//System.out.println("json:"+jsonstr);
		//��������ִ�н����json�ַ������ص�ǰ̨
		AjAxUtils.printString(response, jsonstr);
		return null;
	}
	//��˾���
	@RequestMapping(value="saveCom_emp.do")
	private String saveCom(HttpServletRequest request,HttpServletResponse response,Company company){
		boolean flag =bizutil.getCom().save(company);
		  if(flag){
		  	//��������ִ�н����json�ַ������ص�ǰ̨
		  	AjAxUtils.printString(response, "1");
		  }else{
			AjAxUtils.printString(response, "0"); 
		  }
		return null;
	}
	//��˾�޸�
	@RequestMapping(value="updateCom_emp.do")
	private String updateCom(HttpServletRequest request,HttpServletResponse response,Company company){
		boolean flag =bizutil.getCom().update(company);
		  if(flag){
		  	//��������ִ�н����json�ַ������ص�ǰ̨
		  	AjAxUtils.printString(response, "1");
		  }else{
			AjAxUtils.printString(response, "0"); 
		  }
		return null;
	}
	//��ҳ��ѯ
	@RequestMapping(value="findPageAll_emp.do")
	private String findPageAll(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
		Map<String, Object> map=new HashMap<String,Object>();
		PageBean pb=new PageBean();
		page=page==null||page<1?pb.getPage():page;
		rows=rows==null||rows<1?pb.getRows():rows;
		if(rows>10)rows=10;
		pb.setPage(page);
		pb.setRows(rows);
		List<Emp> lsep=bizutil.getEmps().findPageAll(pb);
		int maxRows=bizutil.getEmps().findMaxRows();
		//ƴ��ǰ̨��easyui����Ҫ������
		map.put("page", page);
		map.put("rows", lsep);
		map.put("total", maxRows);
		//���˲���Ҫ������
		PropertyFilter propertyFilter=AjAxUtils.filterProperts("birthday","pic");
		//����ѯ�������ת��Ϊjson�ַ���  ��������
		String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		//System.out.println("json:"+jsonstr);
		//��������ִ�н����json�ַ������ص�ǰ̨
		AjAxUtils.printString(response, jsonstr);
		return null;
	}
	//ɾ��������Ա
	@RequestMapping(value="delById_emp.do")
	private String delById(HttpServletRequest request,HttpServletResponse response,Integer eid){
		boolean flag =bizutil.getEmps().delById(eid);
		  if(flag){
		  	//��������ִ�н����json�ַ������ص�ǰ̨
		  	AjAxUtils.printString(response, "1");
		  }else{
			AjAxUtils.printString(response, "0"); 
		  }
		return null;
	}
	//��ѯ����
	@RequestMapping(value="findById_emp.do")
	private String findById(HttpServletRequest request,HttpServletResponse response,Integer eid){
		Emp emp=bizutil.getEmps().findById(eid);
		//���˲���Ҫ������
		PropertyFilter propertyFilter=AjAxUtils.filterProperts("birthday","pic");
		//����ѯ�������ת��Ϊjson�ַ���  ��������
		String jsonstr=JSONObject.toJSONString(emp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		//��������ִ�н����json�ַ������ص�ǰ̨
		AjAxUtils.printString(response, jsonstr);
		return null;
	}
	//�޸�
	@RequestMapping(value="update_emp.do")
	private String update(HttpServletRequest request,HttpServletResponse response,Emp emp){
		//�����ϴ��ļ�
		//ԭ������Ƭ������eid��ѯ���������ȡ�ö����ͼƬ���ƣ�
		String oldphoto=bizutil.getEmps().findById(emp.getEid()).getPhoto();
		//��վ��·��
		String realpath=request.getRealPath("/");
		/*****************�ļ��ϴ�begin*******************/
		//��ȡ�ϴ���Ƭ����
		MultipartFile multipartFile=emp.getPic();
		//�п�isEmpty()
		if(multipartFile!=null&&!multipartFile.isEmpty()){
			//��ȡ�ϴ��ļ�����
			//�޸�ʱ������ͼƬ���ҵ�ԭ����ͼƬ��ɾ��ԭͼƬ
			String fname=multipartFile.getOriginalFilename();
			//����
			if(fname.lastIndexOf(".")!=-1){
				//��ȡ
				String ext=fname.substring(fname.lastIndexOf("."));
				//�ж���Ƭ��׺��ͬ
				if(ext.equalsIgnoreCase(".jpg")){
					//getTime()��ǰʱ�������
					String newfname=new Date().getTime()+ext;
					//�����ļ�����ָ���ļ�·��
					File dostFile=new File(realpath+"/uppic/"+newfname);
					try {
						//�ϴ�(�����󴫵ݵ��ļ����ݸ���һ�ݵ��ղ����ɵ��ļ���)
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),dostFile);
						emp.setPhoto(newfname);
						//ɾ��ԭ������Ƭ
						File oldfile=new File(realpath+"/uppic/"+oldphoto);
						if(oldfile!=null||!oldphoto.equals("default.jpg")){
							oldfile.delete();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else{
			//�޸�ʱ,û����ͼƬ���ҵ�ԭ����ͼƬ��ɾ��ԭͼƬ
			emp.setPhoto(oldphoto);
		}
		boolean flag=bizutil.getEmps().update(emp);
		  if(flag){
		  	//��������ִ�н����json�ַ������ص�ǰ̨
		  	AjAxUtils.printString(response, "1");
		  }else{
			AjAxUtils.printString(response, "0"); 
		  }
		return null;
	}
}
