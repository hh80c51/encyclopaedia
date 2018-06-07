package com.athongkun.service;

import com.athongkun.bean.Cert;
import com.athongkun.bean.Member;
import com.athongkun.bean.MemberCert;
import com.athongkun.bean.Ticket;

import java.util.List;

public interface MemberService {
    Member queryMemberByLogin(Member formMember);

    void updateAccttype(Member member);

    Ticket queryTicketByMemberid(Integer id);

    void insertTicket(Ticket t);

    void updateTicketStep(Ticket t);

    void updateBasicInfo(Member loginMember);

    List<Cert> queryCertsByMemberAccttype(String accttype);

    void insertMemberCerts(List<MemberCert> mcs);

    void updateEmail(Member loginMember);

    void updateTicketInfo(Ticket t);

    Ticket queryTicketByPiid(String piid);

    Member queryMemberById(Integer memberid);

    List<MemberCert> queryCertsByMemberId(Integer memberid);

    void updateAuthStatus(Integer memberid);
}
