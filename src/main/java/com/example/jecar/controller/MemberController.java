package com.example.jecar.controller;

import com.example.jecar.configuration.security.JwtTokenProvider;
import com.example.jecar.entity.Member;
import com.example.jecar.repository.MemberRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(tags = {"1. Member"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    // Member CRUD
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "회원 조회", notes = "모든 회원 정보를 조회한다.")
    @GetMapping("/")
    public List<Member> list() {
        return memberRepository.findAll();
    }

    @ApiOperation(value = "단일 회원 조회", notes = "회원 정보 한 건을 조회한다.")
    @GetMapping("/{id}")
    public Optional<Member> get(@PathVariable Integer id) {
        return memberRepository.findById(id);
    }

    @ApiOperation(value = "회원 생성", notes = "회원 정보를 생성한다.")
    @PostMapping("/")
    public Member create(@RequestBody Map<String,String> newMember) {
        return memberRepository.save(
                Member.builder()
                        .account(newMember.get("account"))
                        .password(passwordEncoder.encode(newMember.get("password")))
                        .roles(Collections.singletonList("ROLE_USER"))
                        .build()
        );
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> input) {
        Member member = memberRepository.findByAccount(input.get("account"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 회원입니다."));
        if (!passwordEncoder.matches(input.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(),member.getRoles());
    }

    @ApiOperation(value = "회원 수정", notes = "해당하는 회원 정보를 수정한다.")
    @PutMapping("/{id}")
    public Member modify(@PathVariable Integer id, @ModelAttribute final Member updates) {
        Member member = memberRepository.findById(id).get();
        if (updates.getName()!=null) member.setName(updates.getName());
        if (updates.getPassword()!=null) member.setPassword(updates.getPassword());
        if (updates.getMail()!=null) member.setMail(updates.getMail());
        return memberRepository.save(member);
    }

    @ApiOperation(value = "회원 삭제", notes = "해당하는 회원 정보를 삭제한다.")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        memberRepository.delete(memberRepository.findById(id).get());
    }

}
