package study.hellospring.service;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import study.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService  = new MemberService();

    @AfterEach
    public void afterEach() {

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
        Member member = new Member();
        member.setName("Test1");


    }

    @Test
    void findMembers() {
        Member member1 = new Member();
        member1.setName("Test1");
        Member member2 = new Member();
        member2.setName("Test2");

        memberService.join(member1);
        memberService.join(member2);

        List<Member> testMembers = memberService.findMembers();
        for (Member member : testMembers) {
            assertThat(member.getName())
        }

    }

    @Test
    void findOne() {
    }
}