package vn.edu.hcmuaf.vetcaremanagement.exeption;

import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import vn.edu.hcmuaf.vetcaremanagement.dto.request.ApiResponse;

@Slf4j
@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        log.error("Exception: ", exception);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(apiResponse)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    @Provider
    public static class AppExceptionMapper implements ExceptionMapper<AppException> {
        @Override
        public Response toResponse(AppException exception) {
            ErrorCode errorCode = exception.getErrorCode();

            ApiResponse apiResponse = ApiResponse.builder()
                    .code(errorCode.getCode())
                    .message(errorCode.getMessage())
                    .build();

            return Response.status(errorCode.getStatusCode())
                    .entity(apiResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Provider
    public static class AccessDeniedExceptionMapper implements ExceptionMapper<ForbiddenException> {
        @Override
        public Response toResponse(ForbiddenException exception) {
            ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

            ApiResponse apiResponse = ApiResponse.builder()
                    .code(errorCode.getCode())
                    .message(errorCode.getMessage())
                    .build();

            return Response.status(Response.Status.FORBIDDEN)
                    .entity(apiResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
