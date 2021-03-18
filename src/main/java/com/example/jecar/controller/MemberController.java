package com.example.jecar.controller;

import com.example.jecar.dto.Member;
import com.example.jecar.service.MemberService;
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

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ApiOperation(value = "회원 조회", notes = "모든 회원 정보를 조회한다.")
    @GetMapping("/")
    public List<Member> list() {
        return memberService.findAll();
    }

    @ApiOperation(value = "단일 회원 조회", notes = "회원 정보 한 건을 조회한다.")
    @GetMapping("/{id}")
    public Member get(@PathVariable Integer id) {
        return memberService.findById(id);
    }

    @ApiOperation(value = "회원 생성", notes = "회원 정보를 생성한다.")
    @PostMapping("/")
    public Member create(@ModelAttribute Member member) {
        return memberService.save(member);
    }

    @ApiOperation(value = "회원 수정", notes = "해당하는 회원 정보를 수정한다.")
    @PutMapping("/{id}")
    public Member modify(@PathVariable Integer id, @RequestBody Member updates) {
        Member member = memberService.findById(id);
        if (updates.getName()!=null) member.setName(updates.getName());
        if (updates.getPassword()!=null) member.setPassword(updates.getPassword());
        if (updates.getMail()!=null) member.setMail(updates.getMail());
        return memberService.save(member);
    }

    @ApiOperation(value = "회원 삭제", notes = "해당하는 회원 정보를 삭제한다.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        memberService.delete(memberService.findById(id));
    }

}
