package com.example.jecar.controller;

import com.example.jecar.dto.Member;
import com.example.jecar.repository.MemberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. Member"})
@RestController
@RequestMapping("/members")
public class MemberController {

    // Member CRUD

    private final MemberRepository memberRepository;
    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @ApiOperation(value = "회원 조회", notes = "모든 회원 정보를 조회한다.")
    @GetMapping("/")
    public List<Member> list() {
        return memberRepository.findAll();
    }

    @ApiOperation(value = "단일 회원 조회", notes = "회원 정보 한 건을 조회한다.")
    @GetMapping("/{id}")
    public Member get(@PathVariable Integer id) {
        return memberRepository.findById(id).get();
    }

    @ApiOperation(value = "회원 생성", notes = "회원 정보를 생성한다.")
    @PostMapping("/")
    public Member create(@ModelAttribute Member member) {
        return memberRepository.save(member);
    }

    @ApiOperation(value = "회원 수정", notes = "해당하는 회원 정보를 수정한다.")
    @PutMapping("/{id}")
    public Member modify(@PathVariable Integer id, @RequestBody Member updates) {
        Member member = memberRepository.findById(id).get();
        if (updates.getName()!=null) member.setName(updates.getName());
        if (updates.getPassword()!=null) member.setPassword(updates.getPassword());
        if (updates.getMail()!=null) member.setMail(updates.getMail());
        return memberRepository.save(member);
    }

    @ApiOperation(value = "회원 삭제", notes = "해당하는 회원 정보를 삭제한다.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        memberRepository.delete(memberRepository.getOne(id));
    }

}
