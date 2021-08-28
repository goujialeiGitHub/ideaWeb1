package com.po;

import java.io.Serializable;
//竞拍价
public class Price implements Serializable{
	private int prid;
	private int eid;
	private Float prmoney;
	public Price(int prid, int eid, Float prmoney) {
		super();
		this.prid = prid;
		this.eid = eid;
		this.prmoney = prmoney;
	}
	public Price() {
		super();
	}
	//添加竞拍价专用
	public Price(int eid, Float prmoney) {
		super();
		this.eid = eid;
		this.prmoney = prmoney;
	}
	public int getPrid() {
		return prid;
	}
	public void setPrid(int prid) {
		this.prid = prid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public Float getPrmoney() {
		return prmoney;
	}
	public void setPrmoney(Float prmoney) {
		this.prmoney = prmoney;
	}
	@Override
	public String toString() {
		return "Price [prid=" + prid + ", eid=" + eid + ", prmoney=" + prmoney + "]";
	}
}
