package org.grida.exception

import org.grida.http.BAD_REQUEST

class LoginFailedException :
    GridaException(BAD_REQUEST, "AUTH_500_1", "아이디 혹은 비밀번호를 확인하세요.")

class PasswordConfirmNotMatchedException :
    GridaException(BAD_REQUEST, "USER_400_2", "비밀번호와 비밀번호 확인이 일치하지 않습니다.")
