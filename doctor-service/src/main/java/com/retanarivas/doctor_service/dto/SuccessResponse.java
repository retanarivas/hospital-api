package com.retanarivas.doctor_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SuccessResponse<T> extends BaseResponse {

    private String message;
    private T data;

    public SuccessResponse(int status, String message, T data) {
        super(status);
        this.message = message;
        this.data = data;
    }

    public SuccessResponse(int status, String message) {
        super(status);
        this.message = message;
    }

}
