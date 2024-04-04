package com.example.myblog.dao;

import com.example.myblog.dto.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper //@Mapper를 해야 Bean 객체로 만들 수 있다.
public interface MemberDao {
    public Member loginMember(Member member)  throws Exception; //memberMapper select id와 메소드명이.. 리턴타입은 resultType과 일치해야한다.
}
