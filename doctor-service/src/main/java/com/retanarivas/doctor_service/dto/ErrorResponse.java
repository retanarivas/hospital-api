package com.retanarivas.doctor_service.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse extends BaseResponse {

    private String errorMessage;

    public ErrorResponse(int status, String errorMessage) {
        super(status);
        this.errorMessage = errorMessage;
    }

}
