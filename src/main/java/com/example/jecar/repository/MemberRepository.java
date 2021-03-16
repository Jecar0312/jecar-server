package com.example.jecar.repository;

import com.example.jecar.dto.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
}
