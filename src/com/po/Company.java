package com.po;

import java.io.Serializable;
//¹«Ë¾±í
public class Company implements Serializable{
	private int cid;
	private String cname;
	public Company(int cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	public Company() {
		super();
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Company [cid=" + cid + ", cname=" + cname + "]";
	}
	
}
