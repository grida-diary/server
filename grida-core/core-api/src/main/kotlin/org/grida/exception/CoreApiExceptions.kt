package org.grida.exception

import org.grida.http.BAD_REQUEST
import org.grida.http.INTERNAL_SERVER_ERROR

class CoreApiExceptions :
    GridaException(INTERNAL_SERVER_ERROR, "CORE_500_1", "알 수 없는 예외가 발생했습니다.")

class LoginFailedException :
    GridaException(BAD_REQUEST, "AUTH_400_1", "아이디 혹은 비밀번호를 확인하세요.")
