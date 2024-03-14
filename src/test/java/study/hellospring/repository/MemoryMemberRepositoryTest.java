package study.hellospring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import study.hellospring.domain.Member;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("saveTest");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("test1");
        member2.setName("test2");
        repository.save(member1);
        repository.save(member2);

        Member result = repository.findByName("test2").get();

        assertThat(member2).isEqualTo(result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("test1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(new ArrayList<>(repository.getStore().values())).isEqualTo(result);
    }
}
