package study.datajpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    void testMember() {
        final Member member = new Member("memberA");
        final Member savedMember = memberJpaRepository.save(member);

        final Member findMember = memberJpaRepository.find(savedMember.getId());

        assertThat(findMember)
                .extracting("id", "username")
                .containsExactly(member.getId(), member.getUsername());
        assertThat(findMember).isEqualTo(savedMember);
    }
}
