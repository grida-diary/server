package org.grida.study;

import org.grida.entity.user.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestEntityManager
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("쿼리 성능 테스트")
public class QueryPerformanceTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    @Transactional
    void 읽으려는_열이_적어지면_읽기_성능이_향상된다() {
        // given
        EntityManager entityManager = testEntityManager.getEntityManager();
        UserEntity userEntity = UserEntity.builder().build();
        entityManager.persist(userEntity);
        entityManager.flush();

        // when
        // 전체 열
        long allColumnTakesTime = TestTimeChecker.execute(() ->
            IntStream.range(0, 100)
                    .forEach(i -> {
                        entityManager.createQuery(
                                        "select u from UserEntity u " +
                                                "where u.id = :id ",
                                        UserEntity.class
                                )
                                .setParameter("id", 1L)
                                .getResultList();
                    })
        );

        // 하나의 열
        long oneColumnTakesTime = TestTimeChecker.execute(() ->
            IntStream.range(0, 100)
                    .forEach(i -> {
                        entityManager.createQuery(
                                        "select u.id from UserEntity u " +
                                                "where u.id = :id ",
                                        Long.class
                                )
                                .setParameter("id", 1L)
                                .getResultList();
                    })
        );

        // then
        System.out.println("allColumnTakesTime = " + allColumnTakesTime);
        System.out.println("oneColumnTakesTime = " + oneColumnTakesTime);
        assertThat(allColumnTakesTime).isGreaterThan(oneColumnTakesTime);
    }

    @Test
    @Transactional
    void maxResult_를_제한하면_읽기_성능이_향상된다() {
        // given
        EntityManager entityManager = testEntityManager.getEntityManager();
        List<Long> ids = generateTestData();
        Long targetId = ids.get(5);

        // when
        // maxResult X
        long didntSetMaxResultTakesTime = TestTimeChecker.execute(() ->
            IntStream.range(0, 100)
                    .forEach(i -> {
                        entityManager.createQuery(
                                        "select u from UserEntity u " +
                                                "where u.id = :id",
                                        UserEntity.class
                                )
                                .setParameter("id", targetId)
                                .getResultList();
                    })
        );

        // maxResult O
        long setMaxResultTakesTime = TestTimeChecker.execute(() ->
            IntStream.range(0, 100)
                    .forEach(i -> {
                        entityManager.createQuery(
                                        "select u from UserEntity u " +
                                                "where u.id = :id",
                                        UserEntity.class
                                )
                                .setParameter("id", targetId)
                                .setMaxResults(1)
                                .getResultList();
                    })
        );

        // then
        System.out.println("didntSetMaxResultTakesTime = " + didntSetMaxResultTakesTime);
        System.out.println("setMaxResultTakesTime = " + setMaxResultTakesTime);
        assertThat(didntSetMaxResultTakesTime).isGreaterThan(setMaxResultTakesTime);
    }

    private List<Long> generateTestData() {
        List<Long> ids = new ArrayList<>();
        IntStream.range(0, 50)
                .forEach(i -> {
                    EntityManager entityManager = testEntityManager.getEntityManager();
                    UserEntity userEntity = UserEntity.builder().build();
                    entityManager.persist(userEntity);
                    entityManager.flush();
                    ids.add(userEntity.getId());
                });

        return ids;
    }
}
