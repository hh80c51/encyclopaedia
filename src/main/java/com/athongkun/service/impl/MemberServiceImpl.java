package com.athongkun.service.impl;

import com.athongkun.bean.Cert;
import com.athongkun.bean.Member;
import com.athongkun.bean.MemberCert;
import com.athongkun.bean.Ticket;
import com.athongkun.dao.MemberDao;
import com.athongkun.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: demo
 * @description: 资质账户操作
 * @author: hehang
 * @create: 2018-06-06 17:17
 **/
@Service
public class MemberServiceImpl implements MemberService{

    /**
     *
     */
    @Autowired
    private MemberDao memberDao;

    public Member queryMemberByLogin(Member formMember) {
        return memberDao.queryMemberByLogin(formMember);
    }

    public void updateAccttype(Member member) {
        memberDao.updateAccttype(member);
    }

    public Ticket queryTicketByMemberid(Integer id) {
        return memberDao.queryTicketByMemberid(id);
    }

    public void insertTicket(Ticket t) {
        memberDao.insertTicket(t);
    }

    public void updateTicketStep(Ticket t) {
        memberDao.updateTicketStep(t);
    }

    public void updateBasicInfo(Member loginMember) {
        memberDao.updateBasicInfo(loginMember);
    }

    public List<Cert> queryCertsByMemberAccttype() {
        return queryCertsByMemberAccttype();
    }

    public List<Cert> queryCertsByMemberAccttype(String accttype) {
        return memberDao.queryCertsByMemberAccttype(accttype);
    }

    public void insertMemberCerts(List<MemberCert> mcs) {
        for ( MemberCert mc : mcs ) {
            memberDao.insertMemberCerts(mc);
        }
    }

    public void updateEmail(Member loginMember) {
        memberDao.updateEmail(loginMember);
    }

    public void updateTicketInfo(Ticket t) {
        memberDao.updateTicketInfo(t);
    }

    public Ticket queryTicketByPiid(String piid) {
        return memberDao.queryTicketByPiid(piid);
    }

    public Member queryMemberById(Integer memberid) {
        return memberDao.queryMemberById(memberid);
    }

    public List<MemberCert> queryCertsByMemberId(Integer memberid) {
        return memberDao.queryCertsByMemberId(memberid);
    }

    public void updateAuthStatus(Integer memberid) {
        memberDao.updateAuthStatus(memberid);
    }

}
