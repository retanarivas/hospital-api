package com.retanarivas.doctor_service.dto;

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

    public BaseResponse(int status) {
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

}
