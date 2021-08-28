package com.po;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class Emp implements Serializable {//员工表
	private Integer eid;
	private String ename;
	private String sex;
	private String address;
	private Date birthday;
	private String photo="default.jpg";
	private Integer cid;
	/**与页面相关的临时属性**/
	private String cname;//公司名称
	private Float prmoney;//竞拍价
	//竞拍项目
	private String[] pids;//竞拍项目编号数组
	private List<Project> lspr;//竞拍项目集合
	//文件上传
	private MultipartFile pic;
	//日期转换属性
	private String sdate;
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Emp(Integer eid, String ename, String sex, String address, Date birthday, String photo, Integer cid,
			String cname, Float prmoney, String[] pids, List<Project> lspr, MultipartFile pic, String sdate) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.photo = photo;
		this.cid = cid;
		this.cname = cname;
		this.prmoney = prmoney;
		this.pids = pids;
		this.lspr = lspr;
		this.pic = pic;
		this.sdate = sdate;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setDepname(String cname) {
		this.cname = cname;
	}
	public Float getPrmoney() {
		return prmoney;
	}
	public void setPrmoney(Float prmoney) {
		this.prmoney = prmoney;
	}
	public String[] getPids() {
		return pids;
	}
	public void setPids(String[] pids) {
		this.pids = pids;
	}
	public List<Project> getLspr() {
		return lspr;
	}
	public void setLspr(List<Project> lspr) {
		this.lspr = lspr;
	}
	public MultipartFile getPic() {
		return pic;
	}
	public void setPic(MultipartFile pic) {
		this.pic = pic;
	}
	public String getSdate() {
		if(birthday!=null){
			sdate=new SimpleDateFormat("yyyy-MM-dd").format(birthday);
		}
		return sdate;
	}
	public void setSdate(String sdate) {
			try {
				if(sdate!=null){
				birthday=new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.sdate = sdate;
	}
	@Override
	public String toString() {
		return "Emp [eid=" + eid + ", ename=" + ename + ", sex=" + sex + ", address=" + address + ", birthday="
				+ birthday + ", photo=" + photo + ", cid=" + cid + ", cname=" + cname + ", prmoney=" + prmoney
				+ ", pids=" + Arrays.toString(pids) + ", lspr=" + lspr + ", pic=" + pic + ", sdate=" + sdate + "]";
	}
	

}
