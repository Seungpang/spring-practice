package me.seungpang.querydsl;

import static org.assertj.core.api.Assertions.assertThat;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import me.seungpang.querydsl.entity.Member;
import me.seungpang.querydsl.entity.QMember;
import me.seungpang.querydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @BeforeEach
    void setUp() {
        queryFactory = new JPAQueryFactory(em);
        final Team teamA = new Team("teamA");
        final Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        final Member member1 = new Member("member1", 10, teamA);
        final Member member2 = new Member("member2", 20, teamA);
        final Member member3 = new Member("member3", 30, teamB);
        final Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }

    @Test
    void startJPQL() { //런타임시 오류를 알 수 있다...
        //member1을 찾아라
        final String qlString = "select m from Member m where m.username = :username";

        final Member findMember = em.createQuery(qlString, Member.class)
                .setParameter("username", "member1")
                .getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    void startQuerydsl() { //컴파일 시점에서 오류를 알 수 있다.
        QMember m = new QMember("m");

        final Member findMember = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("member1")) //preparestatement의 파라미터 바인딩 방식을 사용
                .fetchOne();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }
}
