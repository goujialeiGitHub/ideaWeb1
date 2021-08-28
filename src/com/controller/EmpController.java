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
	//人员添加
	@RequestMapping(value="save_emp.do")
	 public String save(HttpServletRequest request,HttpServletResponse response,Emp emp){
		//网站根路径
		String realpath=request.getRealPath("/");
		/*****************文件上传begin*******************/
		//获取上传照片对象
		MultipartFile multipartFile=emp.getPic();
		if(multipartFile!=null&&!multipartFile.isEmpty()){
			//获取上传文件名称
			String fname=multipartFile.getOriginalFilename();
			//改名
			if(fname.lastIndexOf(".")!=-1){
				String ext=fname.substring(fname.lastIndexOf("."));
				if(ext.equalsIgnoreCase(".jpg")){
					String newfname=new Date().getTime()+ext;
					//创建文件对象，指定文件路径
					File dostFile=new File(realpath+"/uppic/"+newfname);
					try {
						//上传(将请求传递的文件内容复制一份到刚才生成的文件中)
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),dostFile);
						emp.setPhoto(newfname);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		/*****************文件上传end*******************/
		boolean flag=bizutil.getEmps().save(emp);
		  if(flag){
		  	//将本方法执行结果的json字符串返回到前台
		  	AjAxUtils.printString(response, "1");
		  }else{
			AjAxUtils.printString(response, "0"); 
		  }
		return null;
	 }
	//查询竞标项目所有
	@RequestMapping(value="doinit_emp.do")
	private String doinit(HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map=new HashMap<String,Object>();
		List<Company> lsct=bizutil.getCom().findAll();
		List<Project> lspr=bizutil.getPro().findAll();
		map.put("lsct", lsct);
		map.put("lspr", lspr);
		//过滤不需要的属性
		PropertyFilter propertyFilter=AjAxUtils.filterProperts("birthday","pic");
		//将查询结果集合转化为json字符串  启动过滤
		String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		//System.out.println("json:"+jsonstr);
		//将本方法执行结果的json字符串返回到前台
		AjAxUtils.printString(response, jsonstr);
		return null;
	}
	//公司添加
	@RequestMapping(value="saveCom_emp.do")
	private String saveCom(HttpServletRequest request,HttpServletResponse response,Company company){
		boolean flag =bizutil.getCom().save(company);
		  if(flag){
		  	//将本方法执行结果的json字符串返回到前台
		  	AjAxUtils.printString(response, "1");
		  }else{
			AjAxUtils.printString(response, "0"); 
		  }
		return null;
	}
	//公司修改
	@RequestMapping(value="updateCom_emp.do")
	private String updateCom(HttpServletRequest request,HttpServletResponse response,Company company){
		boolean flag =bizutil.getCom().update(company);
		  if(flag){
		  	//将本方法执行结果的json字符串返回到前台
		  	AjAxUtils.printString(response, "1");
		  }else{
			AjAxUtils.printString(response, "0"); 
		  }
		return null;
	}
	//分页查询
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
		//拼接前台的easyui所需要的属性
		map.put("page", page);
		map.put("rows", lsep);
		map.put("total", maxRows);
		//过滤不需要的属性
		PropertyFilter propertyFilter=AjAxUtils.filterProperts("birthday","pic");
		//将查询结果集合转化为json字符串  启动过滤
		String jsonstr=JSONObject.toJSONString(map,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		//System.out.println("json:"+jsonstr);
		//将本方法执行结果的json字符串返回到前台
		AjAxUtils.printString(response, jsonstr);
		return null;
	}
	//删除竞拍人员
	@RequestMapping(value="delById_emp.do")
	private String delById(HttpServletRequest request,HttpServletResponse response,Integer eid){
		boolean flag =bizutil.getEmps().delById(eid);
		  if(flag){
		  	//将本方法执行结果的json字符串返回到前台
		  	AjAxUtils.printString(response, "1");
		  }else{
			AjAxUtils.printString(response, "0"); 
		  }
		return null;
	}
	//查询单个
	@RequestMapping(value="findById_emp.do")
	private String findById(HttpServletRequest request,HttpServletResponse response,Integer eid){
		Emp emp=bizutil.getEmps().findById(eid);
		//过滤不需要的属性
		PropertyFilter propertyFilter=AjAxUtils.filterProperts("birthday","pic");
		//将查询结果集合转化为json字符串  启动过滤
		String jsonstr=JSONObject.toJSONString(emp,propertyFilter,SerializerFeature.DisableCircularReferenceDetect);
		//将本方法执行结果的json字符串返回到前台
		AjAxUtils.printString(response, jsonstr);
		return null;
	}
	//修改
	@RequestMapping(value="update_emp.do")
	private String update(HttpServletRequest request,HttpServletResponse response,Emp emp){
		//处理上传文件
		//原来的照片（根据eid查询单个对象获取该对象的图片名称）
		String oldphoto=bizutil.getEmps().findById(emp.getEid()).getPhoto();
		//网站根路径
		String realpath=request.getRealPath("/");
		/*****************文件上传begin*******************/
		//获取上传照片对象
		MultipartFile multipartFile=emp.getPic();
		//判空isEmpty()
		if(multipartFile!=null&&!multipartFile.isEmpty()){
			//获取上传文件名称
			//修改时，有新图片，找到原来的图片，删除原图片
			String fname=multipartFile.getOriginalFilename();
			//改名
			if(fname.lastIndexOf(".")!=-1){
				//截取
				String ext=fname.substring(fname.lastIndexOf("."));
				//判断照片后缀相同
				if(ext.equalsIgnoreCase(".jpg")){
					//getTime()当前时间毫秒数
					String newfname=new Date().getTime()+ext;
					//创建文件对象，指定文件路径
					File dostFile=new File(realpath+"/uppic/"+newfname);
					try {
						//上传(将请求传递的文件内容复制一份到刚才生成的文件中)
						FileUtils.copyInputStreamToFile(multipartFile.getInputStream(),dostFile);
						emp.setPhoto(newfname);
						//删除原来的照片
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
			//修改时,没有新图片，找到原来的图片，删除原图片
			emp.setPhoto(oldphoto);
		}
		boolean flag=bizutil.getEmps().update(emp);
		  if(flag){
		  	//将本方法执行结果的json字符串返回到前台
		  	AjAxUtils.printString(response, "1");
		  }else{
			AjAxUtils.printString(response, "0"); 
		  }
		return null;
	}
}
