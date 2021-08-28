package com.po;

import java.io.Serializable;

//¾ºÅÄÏîÄ¿±í
public class Project implements Serializable{
	private Integer pid;
	private String pname;
	
	public Project() {
		super();
	}
	public Project(Integer pid, String pname) {
		super();
		this.pid = pid;
		this.pname = pname;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	@Override
	public String toString() {
		return "Project [pid=" + pid + ", pname=" + pname + "]";
	}
}
