package com.athongkun.bean;

/**
 * @program: demo
 * @description: 资质
 * @author: hehang
 * @create: 2018-06-06 17:10
 **/
public class Ticket {
    private Integer id,memberid;
    private String piid,authcode,processstep;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getPiid() {
        return piid;
    }

    public void setPiid(String piid) {
        this.piid = piid;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public String getProcessstep() {
        return processstep;
    }

    public void setProcessstep(String processstep) {
        this.processstep = processstep;
    }
}
