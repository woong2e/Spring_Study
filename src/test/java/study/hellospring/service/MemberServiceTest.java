package study.hellospring.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.hellospring.domain.Member;
import study.hellospring.repository.MemberRepository;
import study.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {
    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService  = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("Test1");

        //when
        Long saveId = memberService.join(member);

        //then
        Member testMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(testMember.getName());
    }

    @Test
    void validateDuplicateMember() {
        //given
        Member member1 = new Member();
        member1.setName("Test1");
        Member member2 = new Member();
        member2.setName("Test1");

        //when
        memberService.join(member1);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
        //given
        Member member1 = new Member();
        member1.setName("Test1");
        Member member2 = new Member();
        member2.setName("Test2");

        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        List<Member> testMembers = memberService.findMembers();
        for (Member member : testMembers) {
            assertThat(member.getName());
        }

    }
}