package com.user.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
@AllArgsConstructor
public class ResponseRest<T> {

    String id;
    Integer status;
    String resource;
    T data;
    Map<String, String> metadata;
}
