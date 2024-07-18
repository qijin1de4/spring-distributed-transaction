package io.hqj.spring.dtx.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessResponse<T> {

    private Integer code = 200;
    private String message = "success";
    private T body;

    public static <T> BusinessResponse<T> getResponse(T body) {
        return new BusinessResponse<>(200, "success", body);
    }

    public static <T> BusinessResponse<T> getResponse( Integer code, String message, T body) {
        return new BusinessResponse<>(code, message, body);
    }

}
