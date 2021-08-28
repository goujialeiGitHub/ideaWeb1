package com.po;

import java.io.Serializable;

//员工与竞拍项目关系表
public class EmpProject implements Serializable{
	private int epid; //员工与竞拍项目关系编号
	private int eid;   //员工编号
	private int pid;   //竞拍项目编号
	public EmpProject(int epid, int eid, int pid) {
		super();
		this.epid = epid;
		this.eid = eid;
		this.pid = pid;
	}
	
	public EmpProject(int eid, int pid) {
		super();
		this.eid = eid;
		this.pid = pid;
	}

	public EmpProject() {
		super();
	}
	public int getEpid() {
		return epid;
	}
	public void setEpid(int epid) {
		this.epid = epid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "EmpProject [epid=" + epid + ", eid=" + eid + ", pid=" + pid + "]";
	}
	
}
