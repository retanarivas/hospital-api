package com.retanarivas.common.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class BaseResponse {

    private int status;
    private LocalDateTime timestamp;
    private boolean success;

    public BaseResponse(int status, boolean success) {
        this.status = status;
        this.success = success;
        this.timestamp = LocalDateTime.now();
    }

}
