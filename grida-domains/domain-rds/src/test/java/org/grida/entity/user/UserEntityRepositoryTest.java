package org.grida.entity.user;

import org.grida.domain.user.*;
import org.grida.exception.DomainRdsException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("UserEntityRepository 는")
@ActiveProfiles("test")
class UserEntityRepositoryTest {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Test
    void 유저_한_명을_저장할_수_있다() {
        // given
        generateTestData();

        UserAccount account = new UserAccount(
                UserRole.ROLE_BASIC_USER,
                "email_saved@gmail.com",
                "password_saved",
                "nickname"
        );

        // when
        String createdUserEmail = userEntityRepository.saveAccount(
                account,
                LocalDateTime.of(2024, 1, 1, 1, 1)
        );

        // then
        assertThat(createdUserEmail).isEqualTo("email_saved@gmail.com");
    }

    @Test
    void 요청자의_정보를_불러올_수_있다() {
        // given
        List<String> emails = generateTestData();
        String email = emails.get(0);

        // when
        User user = userEntityRepository.findByEmail(email);

        // then
        assertThat(user.account().email()).isEqualTo(email);
    }

    @Test
    void 이메일의_기존_등록_여부를_알려준다() {
        // given
        List<String> emails = generateTestData();
        String existEmail = emails.get(0);
        String nonExistEmail = "email_notExist@gmail.com";

        // when
        boolean exist = userEntityRepository.existByEmail(existEmail);
        boolean nonExist = userEntityRepository.existByEmail(nonExistEmail);

        // then
        assertThat(exist).isTrue();
        assertThat(nonExist).isFalse();
    }

    @Test
    void 요청자의_권한_정보를_제공한다() {
        // given
        List<String> emails = generateTestData();
        String email = emails.get(0);

        // when
        UserRole role = userEntityRepository.findRoleByEmail(email);

        // then
        assertThat(role).isEqualTo(UserRole.ROLE_BASIC_USER);
    }

    @Test
    void 이메일을_사용해_계정_정보를_제공한다() {
        // given
        List<String> emails = generateTestData();
        String email = emails.get(0);

        // when
        UserAccount account = userEntityRepository.findAccountByEmail(email);

        // then
        assertThat(account.email()).isEqualTo(email);
    }

    @Nested
    @DisplayName("다음과 같은 경우에 예외가 발생한다")
    class ExceptionTest {
        @Test
        void 존재하지_않는_요청자의_정보를_불러오려고_하는_경우() {
            // given
            generateTestData();
            String email = "email@gmail.com";

            // when, then
            assertThatThrownBy(() -> userEntityRepository.findByEmail(email))
                    .isInstanceOf(DomainRdsException.class);
        }

        @Test
        void 존재하지_않는_이메일의_계정_정보를_불러오려는_경우() {
            // given
            String email = "email_notExist@gmail.com";

            // when, then
            assertThatThrownBy(() -> userEntityRepository.findAccountByEmail(email))
                    .isInstanceOf(DomainRdsException.class);
        }
    }

    private List<String> generateTestData() {
        List<String> ids = new ArrayList<>();

        IntStream.range(1, 11)
                .forEach(i -> {
                    UserAccount account = new UserAccount(
                            UserRole.ROLE_BASIC_USER,
                            String.format("%s@gmail.com", UUID.randomUUID().toString()),
                            String.format("password_%d", i),
                            String.format("nickname_%d", i)
                    );

                    String email = userEntityRepository.saveAccount(
                            account,
                            LocalDateTime.of(2024, 1, 1, 1, 1)
                    );

                    ids.add(email);
                });

        return ids;
    }
}