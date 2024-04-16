package com.example.springbasic2.repository;

import com.example.springbasic2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

//extends JpaRepository<사용할 엔티티 클래스, 해당 엔티티의 PK 타입>
public interface MemberRepository extends JpaRepository<Member, Long> {
}
