package org.grida.domain.user;

import org.grida.domain.base.StubDateTimePicker;
import org.grida.exception.DomainException;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("UserService 는")
class UserServiceTest {

    UserService userService() {
        return new UserService(
                new StubDateTimePicker(),
                new StubUserRepository()
        );
    }

    @Test
    void 유저_한_명을_추가할_수_있다() {
        // given
        UserService userService = userService();
        UserAccount account = new UserAccount(
                StubUserRepository.USER_ROLE,
                StubUserRepository.NON_EXIST_USER_EMAIL,
                StubUserRepository.USER_PASSWORD,
                StubUserRepository.USER_NICKNAME
        );

        // when
        String email = userService.join(account);

        // then
        assertThat(email).isEqualTo(StubUserRepository.NON_EXIST_USER_EMAIL);
    }

    @Test
    void 이메일로_유저의_정보를_찾을_수_있다() {
        // given
        UserService userService = userService();
        String email = StubUserRepository.USER_EMAIL;

        // when
        User user = userService.read(email);

        // then
        assertThat(user.id()).isEqualTo(StubUserRepository.USER_ID);
        assertThat(user.account().email()).isEqualTo(StubUserRepository.USER_EMAIL);
        assertThat(user.account().password()).isEqualTo(StubUserRepository.USER_PASSWORD);
        assertThat(user.account().role()).isEqualTo(StubUserRepository.USER_ROLE);
        assertThat(user.account().nickname()).isEqualTo(StubUserRepository.USER_NICKNAME);
    }

    @Test
    void 이메일로_유저의_계정_정보를_찾을_수_있다() {
        // given
        UserService userService = userService();
        String email = StubUserRepository.USER_EMAIL;

        // when
        UserAccount account = userService.readAccount(email);

        // then
        assertThat(account.email()).isEqualTo(email);
        assertThat(account.password()).isEqualTo(StubUserRepository.USER_PASSWORD);
    }

    @Test
    void 이메일로_유저의_권한을_찾을_수_있다() {
        // given
        UserService userService = userService();
        String email = StubUserRepository.USER_EMAIL;

        // when
        UserRole role = userService.readRole(email);

        // then
        assertThat(role).isEqualTo(StubUserRepository.USER_ROLE);
    }

    @Test
    void 사용_가능한_이메일인지_알려준다() {
        // given
        UserService userService = userService();
        String usableEmail = StubUserRepository.NON_EXIST_USER_EMAIL;
        String unusableEmail = StubUserRepository.USER_EMAIL;

        // when
        boolean isUsable = userService.isUsableEmail(usableEmail);
        boolean isUnusable = userService.isUsableEmail(unusableEmail);

        // then
        assertThat(isUsable).isTrue();
        assertThat(isUnusable).isFalse();
    }

    @Nested
    @DisplayName("다음과 같은 경우에 예외가 발생한다")
    class ExceptionTest {

        @Test
        void 이미_등록된_유저를_추가하려는_경우() {
            // given
            UserService userService = userService();
            UserAccount account = new UserAccount(
                    StubUserRepository.USER_ROLE,
                    StubUserRepository.USER_EMAIL,
                    StubUserRepository.USER_PASSWORD,
                    StubUserRepository.USER_NICKNAME
            );

            // when, then
            assertThatThrownBy(() -> userService.join(account))
                    .isInstanceOf(DomainException.class);
        }
    }
}