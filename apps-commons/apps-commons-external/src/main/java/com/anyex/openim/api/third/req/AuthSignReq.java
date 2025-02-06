package com.anyex.openim.api.third.req;

import lombok.Data;

import java.util.List;

@Data
public class AuthSignReq {
    private String uploadID;
    private List<Integer> partNumbers;
}
