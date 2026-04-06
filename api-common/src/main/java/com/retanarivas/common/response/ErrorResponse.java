package com.retanarivas.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
