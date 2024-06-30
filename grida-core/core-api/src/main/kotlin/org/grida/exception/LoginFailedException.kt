package org.grida.exception

import org.grida.http.BAD_REQUEST

class LoginFailedException :
    GridaException(BAD_REQUEST, "AUTH_500_1", "아이디 혹은 비밀번호를 확인하세요.")
