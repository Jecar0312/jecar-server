package com.example.jecar.controller;

import com.example.jecar.dto.Member;
import com.example.jecar.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    // Member CRUD

    private final MemberRepository memberRepository;
    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/")
    public List<Member> list() {
        return memberRepository.findAll();
    }

    @GetMapping("/{id}")
    public Member get(@PathVariable Integer id) {
        return memberRepository.findById(id).get();
    }

    @PostMapping("/")
    public Member create(@ModelAttribute Member member) {
        return memberRepository.save(member);
    }

    @PutMapping("/{id}")
    public Member modify(@PathVariable Integer id, @RequestBody Member updates) {
        Member member = memberRepository.findById(id).get();
        if (updates.getName()!=null) member.setName(updates.getName());
        if (updates.getPassword()!=null) member.setPassword(updates.getPassword());
        if (updates.getMail()!=null) member.setMail(updates.getMail());
        return memberRepository.save(member);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        memberRepository.delete(memberRepository.getOne(id));
    }

}
