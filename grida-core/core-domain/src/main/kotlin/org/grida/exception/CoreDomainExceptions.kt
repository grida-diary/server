package org.grida.exception

import org.grida.http.BAD_REQUEST
import org.grida.http.FORBIDDEN

class NoSuchDataException :
    GridaException(BAD_REQUEST, "CORE_400_1", "찾으려는 데이터가 없습니다.")

class AccessFailedException :
    GridaException(FORBIDDEN, "ACCESS_403_1", "접근 권한이 없습니다.")

class UnusableUsernameException :
    GridaException(BAD_REQUEST, "USER_400_3", "사용할 수 없는 username 입니다.")

class AtcivateProfileImageAlreadyExistsException :
    GridaException(BAD_REQUEST, "PROFILE_IMAGE_400_2", "이미 활성화된 프로필 이미지가 존재합니다.")
