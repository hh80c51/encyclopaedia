package com.athongkun.dao;

import com.athongkun.bean.Cert;
import com.athongkun.bean.Member;
import com.athongkun.bean.MemberCert;
import com.athongkun.bean.Ticket;

import java.util.List;

public interface MemberDao {
    Member queryMemberByLogin(Member formMember);

    void updateAccttype(Member member);

    void insertTicket(Ticket t);

    void updateTicketStep(Ticket t);

    Ticket queryTicketByMemberid(Integer id);

    void updateBasicInfo(Member loginMember);

    List<Cert> queryCertsByMemberAccttype(String accttype);

    void insertMemberCerts(MemberCert mc);

    void updateEmail(Member loginMember);

    void updateTicketInfo(Ticket t);

    Ticket queryTicketByPiid(String piid);

    Member queryMemberById(Integer memberid);

    List<MemberCert> queryCertsByMemberId(Integer memberid);

    void updateAuthStatus(Integer memberid);
}
