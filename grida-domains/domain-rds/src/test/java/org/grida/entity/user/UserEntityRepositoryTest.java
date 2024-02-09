package org.grida.entity.user;

import org.grida.domain.core.Target;
import org.grida.domain.user.*;
import org.grida.exception.DomainRdsException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("UserEntityRepository 는")
class UserEntityRepositoryTest {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Test
    void 유저_한_명을_저장할_수_있다() {
        // given
        List<Long> ids = generateTestData();
        Long expectedId = ids.get(ids.size() - 1) + 1L;

        UserAccount account = new UserAccount(
                String.format("email_%d@gmail.com", expectedId),
                String.format("password_%d", expectedId)
        );
        UserProfile profile = new UserProfile(
                String.format("nickname_%d", expectedId),
                20,
                Gender.MAN
        );

        // when
        Long createdUserId = userEntityRepository.save(
                account,
                UserRole.ROLE_BASIC_USER,
                profile,
                LocalDateTime.of(2024, 1, 1, 1, 1)
        );

        // then
        assertThat(createdUserId).isEqualTo(expectedId);
    }

    @Test
    void 유저_한_명을_불러올_수_있다() {
        // given
        List<Long> ids = generateTestData();
        Target target = new Target(1L);

        // when
        User user = userEntityRepository.find(target);

        // then
        assertThat(user.id()).isEqualTo(target.id());
        assertThat(user.account().email()).isEqualTo(String.format("email_%d@gmail.com", target.id()));
        assertThat(user.account().password()).isEqualTo(String.format("password_%d", target.id()));
        assertThat(user.profile().nickname()).isEqualTo(String.format("nickname_%d", target.id()));
    }

    @Test
    void 유저_한_명을_삭제할_수_있다() {
        // given
        generateTestData();
        Target target = new Target(2L);

        // when
        userEntityRepository.delete(target);

        // then
        assertThatThrownBy(() -> userEntityRepository.find(target))
                .isInstanceOf(DomainRdsException.class);
    }

    @Test
    void 이메일의_기존_등록_여부를_알려준다() {
        // given
        generateTestData();
        Target target = new Target(3L);
        String email = String.format("email_%d@gmail.com", target.id());

        // when
        boolean result = userEntityRepository.existByEmail(email);

        // then
        assertThat(result).isFalse();
    }

    @Test
    void 이메일을_바탕으로_유저의_계정_정보를_제공한다() {
        // given
        generateTestData();
        Target target = new Target(4L);
        String email = String.format("email_%d@gmail.com", target.id());

        // when
        UserAccount account = userEntityRepository.findAccountByEmail(email);

        // then
        assertThat(account.email()).isEqualTo(String.format("email_%d@gmail.com", target.id()));
        assertThat(account.password()).isEqualTo(String.format("password_%d", target.id()));
    }

    @Test
    void 유저의_권한_정보를_제공한다() {
        // given
        generateTestData();
        Target target = new Target(5L);

        // when
        UserRole role = userEntityRepository.findRole(target);

        // then
        assertThat(role).isEqualTo(UserRole.ROLE_BASIC_USER);
    }

    @Nested
    @DisplayName("다음과 같은 경우에 예외가 발생한다")
    class ExceptionTest {

        @Test
        void 존재하지_않는_유저를_불러오려고_하는_경우() {
            // given
            generateTestData();
            Target target = new Target(-1L);

            // when, then
            assertThatThrownBy(() -> userEntityRepository.find(target))
                    .isInstanceOf(DomainRdsException.class);
        }

        @Test
        void 존재하지_않는_유저를_삭제하려고_하는_경우() {
            // given
            generateTestData();
            Target target = new Target(-1L);

            // when, then
            assertThatThrownBy(() -> userEntityRepository.delete(target))
                    .isInstanceOf(DomainRdsException.class);
        }

        @Test
        void 존재하지_않는_이메일_에서_계정_정보를_얻으려고_하는_경우() {
            // given
            generateTestData();
            Target target = new Target(-1L);
            String email = String.format("email_%d@gmail.com", target.id());

            // when, then
            assertThatThrownBy(() -> userEntityRepository.findAccountByEmail(email))
                    .isInstanceOf(DomainRdsException.class);
        }
    }

    private List<Long> generateTestData() {
        List<Long> ids = new ArrayList<>();

        IntStream.range(1, 11)
                .forEach(i -> {
                    UserAccount account = new UserAccount(
                            String.format("email_%d@gmail.com", i),
                            String.format("password_%d", i)
                    );
                    UserProfile profile = new UserProfile(
                            String.format("nickname_%d", i),
                            20,
                            Gender.MAN
                    );

                    long id = userEntityRepository.save(
                            account,
                            UserRole.ROLE_BASIC_USER,
                            profile,
                            LocalDateTime.of(2024, 1, 1, 1, 1)
                    );

                    ids.add(id);
                });

        return ids;
    }
}