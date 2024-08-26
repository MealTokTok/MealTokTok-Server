package core.startup.mealtoktok.infra.gcs.exception;

import core.startup.mealtoktok.common.exception.InfraException;
import core.startup.mealtoktok.domain.global.exception.FileUploadErrorCode;

public class FileUploadFailException extends InfraException {

    public static final InfraException EXCEPTION = new FileUploadFailException();

    private FileUploadFailException() {
        super(FileUploadErrorCode.FILE_UPLOAD_FAIL_ERROR);
    }
}
