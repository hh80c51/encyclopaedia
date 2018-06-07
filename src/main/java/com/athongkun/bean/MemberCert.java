package com.athongkun.bean;

public class MemberCert {

	private Integer id,certid,memberid;
	private String iconpath,certname;
	
	public String getCertname() {
		return certname;
	}
	public void setCertname(String certname) {
		this.certname = certname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCertid() {
		return certid;
	}
	public void setCertid(Integer certid) {
		this.certid = certid;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public String getIconpath() {
		return iconpath;
	}
	public void setIconpath(String iconpath) {
		this.iconpath = iconpath;
	}
	
}
