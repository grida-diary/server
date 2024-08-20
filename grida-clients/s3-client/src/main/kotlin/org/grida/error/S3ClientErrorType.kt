package org.grida.error

import org.grida.http.INTERNAL_SERVER_ERROR

sealed interface StorageClientErrorType : ErrorType

data object FileUploadFail : StorageClientErrorType {
    override val httpStatusCode: Int = INTERNAL_SERVER_ERROR
    override val errorCode: String = "STORAGE_CLIENT_500_1"
    override val message: String = "파일 업로드에 실패하였습니다."
    override val logLevel: LogLevel = ERROR
}
