package org.grida.exception

import org.grida.http.INTERNAL_SERVER_ERROR

class FileUploadFailException :
    GridaException(
        INTERNAL_SERVER_ERROR,
        "STORAGE_CLIENT_500_1",
        "파일 업로드에 실패하였습니다.",
    )
