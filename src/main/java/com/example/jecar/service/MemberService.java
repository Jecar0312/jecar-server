package com.example.jecar.service;

import com.example.jecar.dto.Member;
import com.example.jecar.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Integer id) {
        return memberRepository.findById(id).get();
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }
}
