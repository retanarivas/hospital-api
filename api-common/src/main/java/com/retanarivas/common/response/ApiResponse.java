package com.retanarivas.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> extends BaseResponse {

    private String message;
    private T data;
    private String errors;


    private ApiResponse(int status, String message, T data, String errors, boolean success) {
        super(status, success);
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    // Static method for Success
    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(status, message, data, null, true);
    }

    // Static method for Errors
    public static <T> ApiResponse<T> error(int status, String message, String errors) {
        return new ApiResponse<>(status, message, null, errors, false);
    }

}
