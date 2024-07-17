package org.example.service;

import org.example.dao.MemberDao;
import org.example.dto.Member;

import java.sql.Connection;

public class MemberService {

    private MemberDao memberDao;

    public MemberService(Connection conn) {
        this.memberDao = new MemberDao(conn);
    }

    public boolean isLoginIdDup(Connection conn, String loginId) {
        return memberDao.isLoginIdDup(conn, loginId);
    }

    public int doJoin(String loginId, String loginPw, String name) {
        return memberDao.doJoin(loginId,loginPw,name);
    }

    public Member getMemberByLoginId(String loginId) {
        return memberDao.getMemberByLoginId(loginId);
    }
}