package vn.edu.hcmuaf.vetcaremanagement.exeption;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", Response.Status.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid data.", Response.Status.BAD_REQUEST),
    UNAUTHORIZED(1002, "You do not have permission to access this resource.", Response.Status.FORBIDDEN),
    RESOURCE_NOT_FOUND(1003, "Resource not found.", Response.Status.NOT_FOUND),
    VALIDATION_FAILED(1004, "Input data is invalid.", Response.Status.BAD_REQUEST),
    USER_NOT_FOUND(1005, "User not found.", Response.Status.NOT_FOUND),
    USER_EXISTED(1006, "User existed.", Response.Status.BAD_REQUEST),;

    ErrorCode(int code, String message, Response.Status statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final Response.Status statusCode;
}
