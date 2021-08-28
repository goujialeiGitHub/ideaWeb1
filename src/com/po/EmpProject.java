package com.po;

import java.io.Serializable;

//Ա���뾺����Ŀ��ϵ��
public class EmpProject implements Serializable{
	private int epid; //Ա���뾺����Ŀ��ϵ���
	private int eid;   //Ա�����
	private int pid;   //������Ŀ���
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
