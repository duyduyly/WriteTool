package com.alan.tool.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

/**
 * @author Bryant.Mai
 * @Date Sep 21, 2022
 */
@Getter
@Setter
@Builder
public class CommonResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @Builder.Default
    private Object data = Collections.emptyList();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object errors;
}
