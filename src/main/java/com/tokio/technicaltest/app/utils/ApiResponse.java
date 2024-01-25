package com.tokio.technicaltest.app.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {

    private String message;
    private List<T> data;

}
