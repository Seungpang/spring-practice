package study.datajpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testMember() {
        final Member member = new Member("memberA");
        final Member savedMember = memberRepository.save(member);

        final Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember)
                .extracting("id", "username")
                .containsExactly(savedMember.getId(), savedMember.getUsername());

    }
}
