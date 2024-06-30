package org.grida.exception

import org.grida.http.BAD_REQUEST
import org.grida.http.INTERNAL_SERVER_ERROR

class CoreApiException :
    GridaException(INTERNAL_SERVER_ERROR, "CORE_API_0", "에러가 발생했습니다.")

class TransactionException :
    GridaException(INTERNAL_SERVER_ERROR, "TRANSACTION_500_1", "트랜젝션 처리에 문제가 발생 하였습니다.")

class UserNotExistsException :
    GridaException(BAD_REQUEST, "USER_400_1", "해당하는 유저를 찾을 수 없습니다.")

class UnusableUsernameException :
    GridaException(BAD_REQUEST, "USER_400_3", "사용할 수 없는 username 입니다.")
